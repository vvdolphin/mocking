package com.thoughtworks.mapper;

import com.thoughtworks.Entity.TodoEntity;
import com.thoughtworks.dto.TodoDto;

public class MapperUtil {
    public static TodoEntity fromTodoDtoToEntity(TodoDto todoDto){
        TodoEntity todoEntity = new TodoEntity(todoDto.getContent());
        return todoEntity;
    }
}
