package ru.fateyev.ToDoList.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fateyev.ToDoList.models.Task;
import ru.fateyev.ToDoList.services.TaskService;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //Метод для получения всех дел
    @GetMapping
    public List<Task> getTasks(){
        return taskService.findAll();
    }

    //Метод для получения дела по id
    @GetMapping("/{id}")
    public Task getTask(@PathVariable("id") int id){
        return taskService.findOne(id);
    }

    //Метод для добавления дела
    @PostMapping
    public ResponseEntity<HttpStatus> createTask(@RequestBody @Valid Task task){
        taskService.save(task);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    //Метод для измениния дела
    @PutMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> updateTask(@PathVariable("id") int id, @RequestBody @Valid Task task) {
        taskService.update(id, task);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Метод для удаления дела
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") int id){
        taskService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
