package by.company.library.dao;

import by.company.library.bean.Category;
import by.company.library.bean.User;
import by.company.library.dao.exception.DAOException;

public interface UserDao {
	
	public User registration(String login, String password) throws DAOException;
	public User logination(String login, String password) throws DAOException;
	public boolean changeUserCategory(String login, Category newCategory) throws DAOException;
}
