package ru.fateyev.ToDoList.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fateyev.ToDoList.models.ToDoList;
import ru.fateyev.ToDoList.services.ToDoListService;

@RestController
@RequestMapping("/api")
public class ToDoListController {

    private final ToDoListService toDoListService;
    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    //Метод для получения всех списков
    @GetMapping("/lists")
    public Page<ToDoList> getLists(
            @RequestParam(value = "offset", defaultValue = "0", required = false)@Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10", required = false)@Min(1) @Max(100) Integer limit,
            @RequestParam(value = "sort", defaultValue = "ID_ASC", required = false) ListSort sort
    ) {
        return toDoListService.findAll(offset, limit,sort);
    }

    //Метод для получения списка по id
    @GetMapping("/lists/{id}")
    public ToDoList getList(@PathVariable("id") int id){
        return toDoListService.findOne(id);
    }


    //Метод для добавления списка дел
    @PostMapping("/lists")
    public ResponseEntity<HttpStatus> createList(@RequestBody @Valid ToDoList toDoList){
        toDoListService.save(toDoList);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    //Метод для измениния списка
    @PutMapping("/lists/{id}")
    public ResponseEntity<HttpStatus> updateList(@PathVariable("id") int id, @RequestBody @Valid ToDoList toDoList) {
        toDoListService.update(id, toDoList);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Метод для удаления списка
    @DeleteMapping("/lists/{id}")
    public ResponseEntity<HttpStatus> deleteList(@PathVariable("id") int id) {
        toDoListService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Метод для удаления всех списков
    @DeleteMapping("/lists")
    public ResponseEntity<HttpStatus> deleteAllLists() {
        toDoListService.deleteAll();
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
