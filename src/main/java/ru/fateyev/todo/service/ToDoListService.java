package ru.fateyev.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fateyev.todo.web.controller.ListSort;
import ru.fateyev.todo.entity.ToDoList;
import ru.fateyev.todo.repository.ToDoListRepository;
import ru.fateyev.todo.exception.NotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;

    @Transactional
    public void save(ToDoList toDoList){
        toDoListRepository.save(toDoList);
    }

    public Page<ToDoList> findAll(Integer offset, Integer limit, ListSort sort){
        return toDoListRepository.findAll(PageRequest.of(offset,limit,sort.getSortValue()));
    }

    public ToDoList findOne(int id){
        return toDoListRepository.findById(id).orElseThrow(()-> new NotFoundException("List with this id was not found"));
    }

    @Transactional
    public void update(int id, ToDoList updatedToDoList){
        if (!toDoListRepository.existsById(id)) {
            throw new NotFoundException("List with this id was not found");
        }
        updatedToDoList.setId(id);
        toDoListRepository.save(updatedToDoList);
    }

    @Transactional
    public void delete(int id){
        toDoListRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        toDoListRepository.deleteAll();
    }

}
