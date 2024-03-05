package ru.fateyev.todo.web.dto;

public record TaskDTO(int id, String name,
                      String description, int priority, boolean isDone)  {
}
