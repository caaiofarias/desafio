import com.desafio.rest.dao.UserDao;
import com.desafio.rest.model.User;
import junit.framework.TestCase;

import java.sql.SQLException;

public class TestDatabase extends TestCase {

    private final UserDao userDao = new UserDao();

    public void testAdd() throws SQLException {
        final User user = new User("Name", "Email", "Password");
        int id = userDao.addUser(user);
        User returnedUser = userDao.findOne(id);
        assertEquals("Name", returnedUser.getName());
    }

    public void testDelete() throws SQLException {
        final User user = new User("Usuario", "teste", "123");
        int id = userDao.addUser(user);
        userDao.removeUser(id);
        User userRemoved = userDao.findOne(id);
        assertNull("Nulo", userRemoved);
    }

    public void testEdit() throws SQLException {
        final User user = new User("Usuario1", "email1", "123");
        int id = userDao.addUser(user);
        user.setName("newname");
        userDao.editUser(user);
        User userEdited = userDao.findOne(id);
        assertEquals("newname", userEdited.getName());
    }


}
