package by.company.library.command.impl;

import by.company.library.bean.*;
import by.company.library.command.Command;
import by.company.library.command.exception.CommandException;
import by.company.library.service.*;

public class AddNewBook implements Command {

	@Override
	public String execute(String request) throws CommandException {
		String[] args = request.split("\\s+");
		String name = "";
		String author = "";
		int year = 0;
		Category access = Category.JUNIOR;

		try {
			if (args.length > 2) {
				name = args[2];
			}
			if (args.length > 3) {
				author = args[3];
			}
			if (args.length > 4) {
				year = Integer.parseInt(args[4]);
			}
			if (args.length > 5) {
				access = Category.valueOf(args[5]);
			}

			ServiceFactory.getInstance().getUpdateLibraryService().addNewBook(name, author, year, access);

		} catch (NumberFormatException e) {
			throw new CommandException("Incorrect book year!", e);
		} catch (IllegalArgumentException e) {
			throw new CommandException("UnrecognizableCategory", e);
		} catch (Exception e) {
			throw new CommandException(e);
		}

		return args[0] + "New book added successfully!";
	}

}