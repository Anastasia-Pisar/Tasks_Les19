package by.company.library.service.impl;

import java.util.List;

import by.company.library.bean.*;
import by.company.library.dao.DAOFactory;
import by.company.library.dao.exception.DAOException;
import by.company.library.service.UpdateLibraryService;
import by.company.library.service.exception.ServiceException;

public class UpdateLibraryServiceImpl implements UpdateLibraryService {

	@Override
	public void addNewBook(String name, String author, int year, Category access) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		
		try {
			daoFactory.getBookDAO().add(new Book(name, author, year, access));
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
	@Override
	public List<Book> showLibrary(Category access) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		List<Book> books;
		
		try {
			books = daoFactory.getBookDAO().allLibrary(access);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return books;
	}
}
