package by.company.library.bean;

import java.io.Serializable;

public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String author;
	private int year;
	private Category access;
	
	public Book() {
		this.name = "";
		this.author = "";
		this.year = 0;
		this.access = Category.JUNIOR;
	}
	
	public Book(String name, String author, int year) {
		this.name = name;
		this.author = author;
		this.year = year;
		this.access = Category.JUNIOR;
	}
	
	public Book(String name, String author, int year, Category access) {
		this.name = name;
		this.author = author;
		this.year = year;
		this.access = access;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Category getAccess() {
		return access;
	}

	public void setAccess(Category access) {
		this.access = access;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((access == null) ? 0 : access.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (access != other.access)
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [name = " + name + ", author = " + author + ", year = " + year + ", access = " + access + "]";
	}
}
