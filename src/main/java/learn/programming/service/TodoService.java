package learn.programming.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import learn.programming.entity.Todo;


@Stateless
public class TodoService {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public Todo createTodo(Todo todo) {
		return todo;
	}
	
	public Todo updateTodo(Todo todo) {
		entityManager.merge(todo);
		return todo;
	}
	
	public Todo findById (Long id) {
		return entityManager.find(Todo.class, id);
	}

	public List<Todo> getTodos(){
		return entityManager.createQuery("select t from todo t", Todo.class).getResultList();
	}
	
}
