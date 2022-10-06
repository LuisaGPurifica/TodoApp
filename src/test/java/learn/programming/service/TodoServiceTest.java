package learn.programming.service;


import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import learn.programming.entity.Todo;

@RunWith(Arquillian.class)
public class TodoServiceTest {
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(TodoService.class, Todo.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createTodo() {
		
	}
	
	@Test
	public void updateTodo() {
		
	}
	
	@Test
	public void findToDoById() {
		assertEquals(0, 1);
	}
	
	@Test
	public void getTodos() {
		
	}

}
