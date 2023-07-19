package ru.fateyev.ToDoList.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fateyev.ToDoList.models.Task;
import ru.fateyev.ToDoList.models.ToDoList;
import ru.fateyev.ToDoList.services.ToDoListService;
import java.util.List;

@RestController
@RequestMapping("/api/list")
public class ToDoListController {

    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    //Метод для добавления списка дел
    @PostMapping
    public ResponseEntity<HttpStatus> createList(@RequestBody @Valid ToDoList toDoList){
        toDoListService.save(toDoList);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    //Метод для измениния списка
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateTask(@PathVariable("id") int id, @RequestBody @Valid ToDoList toDoList) {
        toDoListService.update(id, toDoList);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Метод для получения всех списков
    @GetMapping
    public List<ToDoList> getLists(){
        return toDoListService.findAll();
    }

    //Метод для получения списка по id
    @GetMapping("/{id}")
    public ToDoList getList(@PathVariable("id") int id){
        return toDoListService.findOne(id);
    }

    //Метод для получения всех дел в списке по id
    @GetMapping("/{id}/tasks")
    public List<Task> getTasksByLists(@PathVariable("id") int id) {
        return toDoListService.getTasksByListId(id);
    }

    //Метод для удаления списка
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteList(@PathVariable("id") int id) {
        toDoListService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
