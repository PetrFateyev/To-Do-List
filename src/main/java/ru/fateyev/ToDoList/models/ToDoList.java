package ru.fateyev.ToDoList.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "todolist")
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate createdDate;

    private LocalDate modifiedDate;

    @OneToMany(mappedBy = "toDoList")
    private List<Task> taskList;
}
