package ru.fateyev.todo.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fateyev.todo.entity.ToDoList;
import ru.fateyev.todo.service.ToDoListService;

@Tag(name="To Do List", description="To Do List API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ToDoListController {

    private final ToDoListService toDoListService;


    @Operation(
            summary = "получение списков дел",
            description = "позволяет получить все списки дел")
    @GetMapping("/lists")
    public Page<ToDoList> getLists(
            @RequestParam(value = "offset", defaultValue = "0", required = false)@Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10", required = false)@Min(1) @Max(100) Integer limit,
            @RequestParam(value = "sort", defaultValue = "ID_ASC", required = false) ListSort sort
    ) {
        return toDoListService.findAll(offset, limit,sort);
    }

    @Operation(
            summary = "получение списка дел",
            description = "позволяет получить список дел по id")
    @GetMapping("/lists/{id}")
    public ToDoList getList(@PathVariable("id") int id){
        return toDoListService.findOne(id);
    }

    @Operation(
            summary = "добавление списка дел",
            description = "позволяет создать валидный список дел")
    @PostMapping("/lists")
    public ResponseEntity<HttpStatus> createList(@RequestBody @Valid ToDoList toDoList){
        toDoListService.save(toDoList);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Operation(
            summary = "изменение списка дел",
            description = "позволяет изменить список дел по id")
    @PutMapping("/lists/{id}")
    public ResponseEntity<HttpStatus> updateList(@PathVariable("id") int id, @RequestBody @Valid ToDoList toDoList) {
        toDoListService.update(id, toDoList);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "удаление списка дел",
            description = "позволяет удалить список дел по id")
    @DeleteMapping("/lists/{id}")
    public ResponseEntity<HttpStatus> deleteList(@PathVariable("id") int id) {
        toDoListService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "удаление списков дел",
            description = "позволяет удалить все списки дел")
    @DeleteMapping("/lists")
    public ResponseEntity<HttpStatus> deleteAllLists() {
        toDoListService.deleteAll();
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
