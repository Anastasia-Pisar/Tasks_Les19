package by.company.library.service;

import by.company.library.bean.*;
import by.company.library.service.exception.ServiceException;

public interface UserService {
	
	User logination(String login, String password) throws ServiceException;
	User registration(String login, String password) throws ServiceException;
	boolean changeUserCategory(String login, Category newCategory) throws ServiceException;
}
