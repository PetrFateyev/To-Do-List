package ru.fateyev.ToDoList.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fateyev.ToDoList.models.Task;
import ru.fateyev.ToDoList.models.ToDoList;
import ru.fateyev.ToDoList.repositories.ToDoListRepository;
import ru.fateyev.ToDoList.util.NotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    @Transactional
    public void save(ToDoList toDoList){
        toDoListRepository.save(toDoList);
    }

    public List<ToDoList> findAll(){
        return toDoListRepository.findAll();
    }

    public ToDoList findOne(int id){
        return toDoListRepository.findById(id).orElseThrow(()-> new NotFoundException("List with this id was not found"));
    }

    @Transactional
    public void update(int id, ToDoList updatedToDoList){
        updatedToDoList.setId(id);
        toDoListRepository.save(updatedToDoList);
    }

    @Transactional
    public void delete(int id){
        toDoListRepository.deleteById(id);
    }

    public List<Task> getTasksByListId(int id) {
        Optional<ToDoList> list = toDoListRepository.findById(id);

        if (!list.isPresent()) {
            //throw new ResourceNotFoundException();
        }
        Hibernate.initialize(list.get().getTaskList());
        return list.get().getTaskList();
    }
}
