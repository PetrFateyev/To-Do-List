package ru.fateyev.ToDoList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fateyev.ToDoList.models.ToDoList;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
}
