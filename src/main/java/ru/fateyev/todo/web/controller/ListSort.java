package ru.fateyev.todo.web.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum ListSort {

    ID_ASC(Sort.by(Sort.Direction.ASC, "id")),
    NAME_ASC(Sort.by(Sort.Direction.ASC, "name")),
    NAME_DESC(Sort.by(Sort.Direction.DESC, "name")),
    CREATED_DATE_ASC(Sort.by(Sort.Direction.ASC, "created_date")),
    CREATED_DATE_DESC(Sort.by(Sort.Direction.DESC, "created_date")),
    MODIFIED_DATE_ASC(Sort.by(Sort.Direction.ASC, "modified_date")),
    MODIFIED_DATE_DESC(Sort.by(Sort.Direction.DESC, "modified_date"));

    private final Sort sortValue;
}
