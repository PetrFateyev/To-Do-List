package ru.fateyev.todo.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.fateyev.todo.entity.Task;
import ru.fateyev.todo.web.dto.TaskDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
            componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    Task toEntity(TaskDTO taskDTO);
    TaskDTO toDto(Task task);
}
