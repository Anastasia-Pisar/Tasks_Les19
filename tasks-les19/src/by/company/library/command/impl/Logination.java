package by.company.library.command.impl;

import by.company.library.bean.*;
import by.company.library.command.Command;
import by.company.library.command.exception.CommandException;
import by.company.library.service.*;

public class Logination implements Command{

	@Override
	public String execute(String request) throws CommandException {
		
		String[] args = request.split("\\s+");
		String login;
		String password;
		
		if (args.length > 2) {
			login = args[2];
		} else {
			throw new CommandException("There is no login!");
		}
		if (args.length > 3) {
			password = args[3];
		} else {
			throw new CommandException("There is no password!");
		}
		
		User logUser;
		try {
			logUser = ServiceFactory.getInstance().getUserService().logination(login, password);
		} catch (Exception e) {
			throw new CommandException(e);
		}
		
		return logUser.getCategory().toString() + " Hi, " + logUser.getLogin();
	}

}