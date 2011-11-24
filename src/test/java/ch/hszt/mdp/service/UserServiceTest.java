package ch.hszt.mdp.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.hszt.mdp.dao.UserDao;
import ch.hszt.mdp.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mdp-test-daos.xml"})
public class UserServiceTest {
	
	// this instance will be dependency injected by type
    private UserDao userDao;

    @Autowired
    public void setTitleDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Test
    public void testLoadTitle() throws Exception {
    	userDao.save(new User("", "gabathuler@gmail.com", "", new Integer(0)));
        assertNotNull(userDao.getUserByEmail("gabathuler@gmail.com"));
    }

}
