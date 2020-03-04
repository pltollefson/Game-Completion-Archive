package edu.matc.persistence;

import edu.matc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao dao;

    /**
     * Sets up the user table with fresh data and instantiates the userdao
     */
    @BeforeEach
    void setUp() {
        dao = new UserDao();

        edu.matc.test.util.Database database = edu.matc.test.util.Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getById() {
        User retrievedUser = dao.getById(3);
        assertEquals("thirduser", retrievedUser.getUsername());
        assertEquals("password3", retrievedUser.getPassword());
        assertEquals("email@address.net", retrievedUser.getEmail());

        retrievedUser = dao.getById(2);
        assertEquals("seconduser", retrievedUser.getUsername());
        assertEquals("password2", retrievedUser.getPassword());
        assertEquals(null, retrievedUser.getEmail());
    }

    @Test
    void saveOrUpdate() {
        User retrievedUser = dao.getById(3);
        retrievedUser.setUsername("ThirdUser");
        retrievedUser.setPassword("The3rdPassword");
        retrievedUser.setEmail("thirduser@address.com");
        dao.saveOrUpdate(retrievedUser);
        assertEquals("ThirdUser", retrievedUser.getUsername());
        assertEquals("The3rdPassword", retrievedUser.getPassword());
        assertEquals("thirduser@address.com", retrievedUser.getEmail());
    }

    @Test
    void insert() {
        User newUser = new User("fourthuser", "password4", "email@address.org");
        int id = dao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = dao.getById(id);
        assertEquals("fourthuser", insertedUser.getUsername());
        assertEquals("password4", insertedUser.getPassword());
        assertEquals("email@address.org", insertedUser.getEmail());
    }

    @Test
    void delete() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    @Test
    void getAll() {
        List<User> users = dao.getAll();
        assertEquals(3, users.size());
    }
}