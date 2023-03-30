package ru.fateyev.ToDoList.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private LocalDate createdDate;

    private LocalDate modifiedDate;

    private String description;

    private int priority;

    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "to_do_list_id", referencedColumnName = "id")
    private ToDoList toDoList;

}
