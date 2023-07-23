package ru.fateyev.ToDoList.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fateyev.ToDoList.models.Task;
import ru.fateyev.ToDoList.repositories.TaskRepository;
import ru.fateyev.ToDoList.repositories.ToDoListRepository;
import ru.fateyev.ToDoList.util.NotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;
    private final ToDoListRepository toDoListRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, ToDoListRepository toDoListRepository) {
        this.taskRepository = taskRepository;
        this.toDoListRepository = toDoListRepository;
    }

    @Transactional
    public void create(int listId, Task task) {
        toDoListRepository.findById(listId).map(list -> {
            task.setToDoList(list);
            return taskRepository.save(task);
        }).orElseThrow(() -> new NotFoundException("List with this id was not found"));
    }

    public List<Task> findByToDoListId(int listId) {
        if (!toDoListRepository.existsById(listId)) {
            throw new NotFoundException("List with this id was not found");
        }
        return taskRepository.findByToDoListId(listId);
    }

    public Task findOne(int id){
        return taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task with this id was not found"));
    }

    @Transactional
    public void update(int id, Task updatedTask){
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException("Task with this id was not found");
        }
        updatedTask.setId(id);
        taskRepository.save(updatedTask);
    }

    @Transactional
    public void markDone(int id, Task task) {
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException("Task with this id was not found");
        }
        task.setId(id);
        task.setDone(true);
        taskRepository.save(task);
    }

    @Transactional
    public void delete(int id){
        taskRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(int listId) {
        if (!toDoListRepository.existsById(listId)) {
            throw new NotFoundException("List with this id was not found");
        }
        taskRepository.deleteByToDoListId(listId);
    }
}
