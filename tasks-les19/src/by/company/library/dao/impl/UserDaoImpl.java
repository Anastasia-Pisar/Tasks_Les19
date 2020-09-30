package by.company.library.dao.impl;

import java.io.*;

import by.company.library.bean.*;
import by.company.library.dao.UserDao;
import by.company.library.dao.exception.DAOException;

public class UserDaoImpl implements UserDao {
	
	private static final String USER_DOC = "users.txt";
	
	@Override
	public User registration(String login, String password) throws DAOException {
		BufferedReader bufferedReader = null;
		PrintWriter print = null;
		
		try {
			
			FileReader reader = new FileReader(USER_DOC);
			bufferedReader = new BufferedReader(reader);
			String line;
			line = bufferedReader.readLine();

			boolean found = false;
			String userLogin = null;
			while(line != null) {
				userLogin = line.split("\\s+")[0];
				if (userLogin.equals(login)) {
					found = true;
					break;
				}
				line = bufferedReader.readLine();
			}
			
			if (found) {
				throw new DAOException("SuchUserAlreadyExists");
			}
			
			FileWriter fileWriter = new FileWriter(USER_DOC, true);			
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			print = new PrintWriter(bufferedWriter);
			
			print.println(login + " " + password + " " + "JUNIOR");
			
		} catch (FileNotFoundException e) {
			throw new DAOException("FileNotFound", e);
		} catch (IOException e) {
			throw new DAOException("IOError", e);
		} finally {
			if (print != null) {
				print.close(); 
			}
			
			try {	
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				throw new DAOException("IOClosingError", e1);
			}
		}

		return new User(login, password);
	}

	@Override
	public User logination(String login, String password) throws DAOException {
		BufferedReader bufferedReader = null;
		boolean found;
		String[] args;
		
		try {
			FileReader reader = new FileReader(USER_DOC);			
			bufferedReader = new BufferedReader(reader);
			String line;			
			line = bufferedReader.readLine();
			
			found = false;
			args = null;
			while(line != null) {
				args = line.split("\\s+");
				if (args[0].equals(login) && args[1].equals(password)) {
					found = true;
					break;
				}
				
				line = bufferedReader.readLine();
			}
		} catch (FileNotFoundException e) {
			throw new DAOException("FileNotFound", e);
		} catch (IOException e) {			
			throw new DAOException("IOError", e);
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				throw new DAOException("IOClosingError", e1);
			}						
		}
		
		if (found && args != null) {
			return new User(login, password, Category.valueOf(args[2]));
		} else {
			throw new DAOException("UserNotFound");
		}
	}

	@Override
	public boolean changeUserCategory(String login, Category newCategory) throws DAOException {
		RandomAccessFile ran = null;
		
		try {
			ran = new RandomAccessFile(USER_DOC, "read");
			
			String line;					
			line = ran.readLine();
			
			String[] args = null;
			while(line != null) {
				args = line.split("\\s+");
				
				if (args[0].equals(login)) {							
					Category currentCategory;						
					currentCategory = Category.valueOf(args[5]);
										
					if (!currentCategory.equals(newCategory)) {
						ran.seek(ran.getFilePointer() - categoryMaxLength() - 2);
						
						for (int i = 0; i < categoryMaxLength(); i++) {
							ran.writeByte(' ');
						}
						ran.seek(ran.getFilePointer() - categoryMaxLength());
						ran.writeBytes(newCategory.toString());						
					}
					
					return true;
				}
				
				line = ran.readLine();
			}			
		} catch (FileNotFoundException e) {
			throw new DAOException("FileNotFound", e);
		} catch (IOException e) {
			throw new DAOException("IOError", e);
		} catch (IllegalArgumentException e) {
			throw new DAOException("UnrecognizableRole", e);
		} finally {
			try {
				if (ran != null) {
					ran.close();
				}
			} catch (IOException e) {
				throw new DAOException("IOClosingError", e);
			}
		}
		return false;
	}
	
	private static byte categoryMaxLength() {
		byte maxLength = 0;
		for (Category category: Category.values()) {
			if (category.toString().length() > maxLength) {
				maxLength = (byte)category.toString().length();
			}
		}
		return maxLength;
	}
}
