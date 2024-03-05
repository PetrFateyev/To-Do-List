package ru.fateyev.todo.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fateyev.todo.service.TaskService;
import ru.fateyev.todo.web.dto.TaskDTO;

import java.util.List;

@Tag(name="Task", description="The Task API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @Operation(
            summary = "получение всех дел",
            description = "позволяет получить все дела в списке по заданному id списка")
    @GetMapping("/lists/{listId}/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTaskByToDoListId(@PathVariable(value = "listId") int id) {
        return new ResponseEntity<>(taskService.findByToDoListId(id), HttpStatus.OK);
    }

    @Operation(
            summary = "получение дела",
            description = "позволяет получить дело по заданному id")
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("id") int id){
        return new ResponseEntity<>(taskService.findOne(id), HttpStatus.OK);
    }


    @Operation(
            summary = "создание дела",
            description = "позволяет добавить дело в список дел по id списка")
    @PostMapping("/lists/{listId}/tasks")
    public ResponseEntity<HttpStatus> createTask(@PathVariable(value = "listId") int listId, @RequestBody @Valid TaskDTO taskDTO){
        taskService.create(listId,taskDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Operation(
            summary = "изменение дела",
            description = "позволяет изменить дело по id")
    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity<HttpStatus> updateTask(@PathVariable("id") int id, @RequestBody @Valid TaskDTO taskDTO) {
        taskService.update(id, taskDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "пометить сделанным",
            description = "позволяет пометить дело, как сделанное")
    @PutMapping("/tasks/mark-done/{id}")
    public ResponseEntity<HttpStatus> markDone(@PathVariable("id") int id, @RequestBody @Valid TaskDTO taskDTO) {
        taskService.markDone(id, taskDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "удаление дела",
            description = "позволяет удалить дело по id")
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") int id){
        taskService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "удаление всех дел",
            description = "позволяет удалить все дела в списке по заданному id")
    @DeleteMapping("/lists/listId/tasks")
    public ResponseEntity<HttpStatus> deleteAll(@PathVariable("listId") int id){
        taskService.deleteAll(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
