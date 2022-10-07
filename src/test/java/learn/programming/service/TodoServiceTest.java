package learn.programming.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import learn.programming.entity.Todo;
import learn.programming.entity.User;

@RunWith(Arquillian.class)
public class TodoServiceTest {
	
	@Inject
	private User user;
	
	@Inject
	TodoService todoService;
	Logger logger;

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "to-do")
				.addPackage(Todo.class.getPackage())
				.addPackage(TodoService.class.getPackage())
				.addAsResource("persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void setUp() throws Exception {
		logger = Logger.getLogger(TodoServiceTest.class.getName());
		user.setEmail("donald@email.com");
		user.setFullName("Donald Trump");
		user.setPassword("jDTrumpf@41");
		
		todoService.saveUser(user);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void saveUser() {
		assertNotNull(user.getId());
		logger.log(Level.INFO, user.getId().toString());
		assertNotEquals("The user password is not same as hashed", "jDTrumpf@41", user.getPassword());
		logger.log(Level.INFO, user.getPassword());
	}

}
