package ru.fateyev.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fateyev.todo.entity.Task;
import ru.fateyev.todo.repository.TaskRepository;
import ru.fateyev.todo.repository.ToDoListRepository;
import ru.fateyev.todo.exception.NotFoundException;
import ru.fateyev.todo.util.mapper.TaskMapper;
import ru.fateyev.todo.web.dto.TaskDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ToDoListRepository toDoListRepository;
    private final TaskMapper taskMapper;


    @Transactional
    public void create(int listId, TaskDTO taskDTO) {
        toDoListRepository.findById(listId).map(list -> {
            Task task = taskMapper.toEntity(taskDTO);
            task.setToDoList(list);
            return taskRepository.save(task);
        }).orElseThrow(() -> new NotFoundException("List with this id was not found"));
    }

    public List<TaskDTO> findByToDoListId(int listId) {
        if (!toDoListRepository.existsById(listId)) {
            throw new NotFoundException("List with this id was not found");
        }
        return taskRepository.findByToDoListId(listId)
                                .stream()
                                .map(taskMapper::toDto)
                                .collect(Collectors.toList());
    }

    public TaskDTO findOne(int id){
        return taskRepository.findById(id).map(taskMapper::toDto)
                .orElseThrow(()-> new NotFoundException("Task with this id was not found"));
    }

    @Transactional
    public void update(int id, TaskDTO taskDTO){
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException("Task with this id was not found");
        }
        Task task = taskMapper.toEntity(taskDTO);
        task.setId(id);
        taskRepository.save(task);
    }

    @Transactional
    public void markDone(int id, TaskDTO taskDTO) {
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException("Task with this id was not found");
        }
        Task task = taskMapper.toEntity(taskDTO);
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
