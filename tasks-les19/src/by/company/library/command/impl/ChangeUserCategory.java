package by.company.library.command.impl;

import by.company.library.bean.*;
import by.company.library.command.Command;
import by.company.library.command.exception.CommandException;
import by.company.library.service.*;

public class ChangeUserCategory implements Command {

	@Override
	public String execute(String request) throws CommandException {
		String[] args = request.split("\\s+");
		Category accessCategory = Category.JUNIOR;
		Category objectiveCategory = Category.ADULT;
		String objectiveUserLogin;
		boolean success;

		try {
			accessCategory = Category.valueOf(args[0]);

			if (!accessCategory.equals(Category.ADULT)) {
				return args[0] + " This command can't be executed with your access!";
			}

			if (args.length > 2) {
				objectiveUserLogin = args[2];
			} else {
				throw new CommandException("There is no target user login!");
			}

			if (args.length > 3) {
				objectiveCategory = Category.valueOf(args[3]);
			}

			success = ServiceFactory.getInstance().getUserService().changeUserCategory(objectiveUserLogin, objectiveCategory);

		} catch (IllegalArgumentException e) {
			throw new CommandException("UnrecognizableCategory", e);
		} catch (Exception e) {
			throw new CommandException(e);
		}

		if (success) {
			return args[0] + " The " + objectiveUserLogin + "'s category is set to the " + objectiveCategory.toString();
		} else {
			return args[0] + " Such user doesn't exist!";
		}
	}

}
