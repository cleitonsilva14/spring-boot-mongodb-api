package io.springboot.mongodb.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.springboot.mongodb.api.exception.TodoCollectionException;
import io.springboot.mongodb.api.model.TodoDTO;
import io.springboot.mongodb.api.repository.TodoRepository;
import jakarta.validation.ConstraintViolationException;

@Service
public class TodoServiceImpl implements TodoService {


	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException {
		Optional<TodoDTO> todoOptional = todoRepository.findByTodo(todo.getTodo());
		if(todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.todoAlreadyExists());
		}else {
			todo.setCreatedAt(LocalDateTime.now());
			todoRepository.save(todo);
		}
	}

	@Override
	public List<TodoDTO> getAllTodos() {
		List<TodoDTO> todos = todoRepository.findAll();
		if(todos.size() > 0) {
			return todos;
		}else {
			return new ArrayList<TodoDTO>();
		}
	}

	@Override
	public TodoDTO getSingleTodo(String id) throws TodoCollectionException {
		Optional<TodoDTO> todoOptional = todoRepository.findById(id);
		if(!todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.notFoundException(id));
		}else {
			return todoOptional.get();
		}
		
	}

	@Override
	public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException {
		Optional<TodoDTO> todoWithId = todoRepository.findById(id);
		Optional<TodoDTO> todoWithSameName = todoRepository.findByTodo(todo.getTodo());
		
		
		if(todoWithId.isPresent()) {
			
			if(todoWithSameName.isPresent() && todoWithSameName.get().getId().equals(id)) {
				throw new TodoCollectionException(TodoCollectionException.todoAlreadyExists());
			}
			
			TodoDTO todoToUpdate = todoWithId.get();
			todoToUpdate.setTodo(todo.getTodo());
			todoToUpdate.setDescription(todo.getDescription());
			todoToUpdate.setCompleted(todo.getCompleted());
			todoToUpdate.setUpdateAt(LocalDateTime.now());
			todoRepository.save(todoToUpdate);
			
		}else {
			throw new TodoCollectionException(TodoCollectionException.notFoundException(id));
		}
		
		
	}

	@Override
	public void deleteTodoById(String id) throws TodoCollectionException {
		Optional<TodoDTO> todoOptional = todoRepository.findById(id);
		if(!todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.notFoundException(id));
		} else {
			todoRepository.deleteById(id);
		}
		
	}

	
	
}
