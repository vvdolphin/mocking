package com.thoughtworks.service.impl;

import com.thoughtworks.Entity.TodoEntity;
import com.thoughtworks.dto.TodoDto;
import com.thoughtworks.mapper.MapperUtil;
import com.thoughtworks.responsity.TodoRepository;
import com.thoughtworks.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TodoServiceImpl implements TodoService {

  @Autowired private TodoRepository todoRepository;

  @Override
  public List<TodoEntity> getAllTodo() {
    return todoRepository.findAll();
  }

  @Override
  public void deleteTodoById(int id) {
    todoRepository.deleteById(id);
  }

  @Override
  public void addTodo(TodoDto todoDto) {
    TodoEntity todoEntity = MapperUtil.fromTodoDtoToEntity(todoDto);
    todoRepository.save(todoEntity);
  }

  @Override
  public void updateTodo(TodoEntity todoEntity) {
    todoRepository.save(todoEntity);
  }
}
