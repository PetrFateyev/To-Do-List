package ru.fateyev.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fateyev.todo.entity.ToDoList;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
}
