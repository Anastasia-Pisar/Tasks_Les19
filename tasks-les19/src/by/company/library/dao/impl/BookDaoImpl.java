package by.company.library.dao.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import by.company.library.bean.*;
import by.company.library.dao.BookDao;
import by.company.library.dao.exception.DAOException;

public class BookDaoImpl implements BookDao {

	private static final String BOOK_DOC = "books.txt";

	@Override
	public void add(Book book) throws DAOException {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter = null;
		PrintWriter print = null;

		try {

			fileWriter = new FileWriter(BOOK_DOC, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			print = new PrintWriter(bufferedWriter);
			print.println(" " + book.getName() + ", " + book.getAuthor() + ", " + book.getYear() + ", " + book.getAccess());

		} catch (IOException e) {
			throw new DAOException("IOError", e);
		} finally {
			if (print != null) {
				print.close();
			}
		}
	}

	@Override
	public List<Book> allLibrary(Category access) throws DAOException {
		List<Book> books = new ArrayList<Book>();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try {
		
			fileReader = new FileReader(BOOK_DOC);
			bufferedReader = new BufferedReader(fileReader);
			StringBuilder line = new StringBuilder();
			line.append(bufferedReader.readLine());
			System.out.println(line);
			String[] args;
			
			if (access.equals(Category.ADULT)) {
				
				while(line != null) {
					
					String temp = line.toString();
					args = temp.split("\\s+");
					String name = args[0];
					String author = args[1];
					int year = Integer.parseInt(args[2]);
					books.add(new Book(name, author, year));
					line.setLength(0);
					line.append(bufferedReader.readLine());
				}
			} else {
				
				while(line != null) {
					
					String temp = line.toString();
					args = temp.split("\\s+");
					String name = args[0];
					String author = args[1];
					int year = Integer.parseInt(args[2]);
					String bookAccess = args[3];
					
					if (bookAccess.equals(Category.JUNIOR.toString())) {
						books.add(new Book(name, author, year));
					}
					line.setLength(0);
					line.append(bufferedReader.readLine());
				}
			}
		} catch (FileNotFoundException e) {
			throw new DAOException("FileNotFound", e);
		} catch (IOException e) {
			throw new DAOException("IOError", e);
		} catch (NumberFormatException e) {
			throw new DAOException("ParseIntError", e);
		} finally {		
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				throw new DAOException("IOClosingError", e1);
			}
		}
		
		return books;
	}
}
