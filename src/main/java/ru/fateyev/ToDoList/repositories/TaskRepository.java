package ru.fateyev.ToDoList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fateyev.ToDoList.models.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByToDoListId(int listId);
    void deleteByToDoListId(int listId);
}
