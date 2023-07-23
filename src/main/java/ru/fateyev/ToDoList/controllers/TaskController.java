package ru.fateyev.ToDoList.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fateyev.ToDoList.models.Task;
import ru.fateyev.ToDoList.services.TaskService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //Метод для получения всех дел в списке
    @GetMapping("/lists/{listId}/tasks")
    public ResponseEntity<List<Task>> getAllTaskByToDoListId(@PathVariable(value = "listId") int id) {
        return new ResponseEntity<>(taskService.findByToDoListId(id), HttpStatus.OK);
    }

    //Метод для получения дела по id
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") int id){
        return new ResponseEntity<>(taskService.findOne(id), HttpStatus.OK);
    }


    //Метод для добавления дела в список
    @PostMapping("/lists/{listId}/tasks")
    public ResponseEntity<HttpStatus> createTask(@PathVariable(value = "listId") int listId, @RequestBody @Valid Task task){
        taskService.create(listId,task);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    //Метод для изменения дела
    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity<HttpStatus> updateTask(@PathVariable("id") int id, @RequestBody @Valid Task task) {
        taskService.update(id, task);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Метод чтобы пометить дело, как сделанное
    @PutMapping("/tasks/mark-done/{id}")
    public ResponseEntity<HttpStatus> markDone(@PathVariable("id") int id, @RequestBody @Valid Task task) {
        taskService.markDone(id, task);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Метод для удаления дела
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") int id){
        taskService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Метод для удаления всех дел в списке
    @DeleteMapping("/lists/listId/tasks")
    public ResponseEntity<HttpStatus> deleteAll(@PathVariable("listId") int id){
        taskService.deleteAll(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
