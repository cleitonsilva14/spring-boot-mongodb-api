package io.springboot.mongodb.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.springboot.mongodb.api.exception.TodoCollectionException;
import io.springboot.mongodb.api.model.TodoDTO;
import jakarta.validation.ConstraintViolationException;

@Service
public interface TodoService {
	
	public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException;
	
	
	public List<TodoDTO> getAllTodos();
	
	
	public TodoDTO getSingleTodo(String id) throws TodoCollectionException;

	
	public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;
	
	
	public void deleteTodoById(String id) throws TodoCollectionException;
	
}
