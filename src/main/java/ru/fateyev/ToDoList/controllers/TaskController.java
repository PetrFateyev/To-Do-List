package ru.fateyev.ToDoList.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fateyev.ToDoList.models.Task;
import ru.fateyev.ToDoList.services.TaskService;
import java.util.List;

@Tag(name="Task", description="The Task API")
@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(
            summary = "получение всех дел",
            description = "позволяет получить все дела в списке по заданному id списка")
    @GetMapping("/lists/{listId}/tasks")
    public ResponseEntity<List<Task>> getAllTaskByToDoListId(@PathVariable(value = "listId") int id) {
        return new ResponseEntity<>(taskService.findByToDoListId(id), HttpStatus.OK);
    }

    @Operation(
            summary = "получение дела",
            description = "позволяет получить дело по заданному id")
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") int id){
        return new ResponseEntity<>(taskService.findOne(id), HttpStatus.OK);
    }


    @Operation(
            summary = "создание дела",
            description = "позволяет добавить дело в список дел по id списка")
    @PostMapping("/lists/{listId}/tasks")
    public ResponseEntity<HttpStatus> createTask(@PathVariable(value = "listId") int listId, @RequestBody @Valid Task task){
        taskService.create(listId,task);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Operation(
            summary = "изменение дела",
            description = "позволяет изменить дело по id")
    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity<HttpStatus> updateTask(@PathVariable("id") int id, @RequestBody @Valid Task task) {
        taskService.update(id, task);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "пометить сделанным",
            description = "позволяет пометить дело, как сделанное")
    @PutMapping("/tasks/mark-done/{id}")
    public ResponseEntity<HttpStatus> markDone(@PathVariable("id") int id, @RequestBody @Valid Task task) {
        taskService.markDone(id, task);
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
