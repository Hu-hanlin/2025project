package student.course.management.system.service;

import student.course.management.system.dao.UserDAO;
import student.course.management.system.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean register(User user) {
        return userDAO.register(user);
    }

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    public boolean changePassword(int userId, String newPassword) {
        return userDAO.changePassword(userId, newPassword);
    }
}    