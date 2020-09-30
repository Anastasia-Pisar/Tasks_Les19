package by.company.library.service;

import java.util.List;

import by.company.library.bean.*;
import by.company.library.service.exception.ServiceException;

public interface UpdateLibraryService {
	
	void addNewBook(String name, String author, int year, Category access) throws ServiceException;
	List<Book> showLibrary(Category access) throws ServiceException;
}
