package ru.fateyev.ToDoList.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fateyev.ToDoList.models.Task;
import ru.fateyev.ToDoList.repositories.TaskRepository;
import ru.fateyev.ToDoList.util.NotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void save(Task task){
        taskRepository.save(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findOne(int id){
        return taskRepository.findById(id).orElseThrow(()-> new NotFoundException("Task with this id was not found"));
    }

    @Transactional
    public void update(int id, Task updatedTask){
        updatedTask.setId(id);
        taskRepository.save(updatedTask);
    }

    @Transactional
    public void delete(int id){
        taskRepository.deleteById(id);
    }

}
