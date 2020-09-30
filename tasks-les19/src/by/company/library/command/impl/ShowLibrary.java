package by.company.library.command.impl;

import java.util.List;

import by.company.library.bean.*;
import by.company.library.command.Command;
import by.company.library.command.exception.CommandException;
import by.company.library.service.*;

public class ShowLibrary implements Command {

	@Override
	public String execute(String request) throws CommandException {
		String[] args = request.split("\\s+");
		Category access = Category.JUNIOR;
		List<Book> books;
		
		try {
			access = Category.valueOf(args[0]);				
				
			books = ServiceFactory.getInstance().getUpdateLibraryService().showLibrary(access);
			
		} catch (IllegalArgumentException e) {
			throw new CommandException("UnrecognizableRole", e);
		} catch (Exception e) {
			throw new CommandException(e);
		}
		
		StringBuilder response = new StringBuilder();
		for (Book book: books) {
			response.append(book.getName() + ", " + book.getAuthor() + ", " + book.getYear() + " \n");
		}
		
		return args[0] + " " + response;
	}

}