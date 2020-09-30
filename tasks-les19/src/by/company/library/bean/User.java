package by.company.library.bean;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;	
	private String login;
	private String password;
	private Category category;

	public User() {
		this.login = "person";
		this.password = "person";
		this.category = Category.JUNIOR;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.category = Category.JUNIOR;
	}
	
	public User(String login, String password, Category category) {
		this.login = login;
		this.password = password;
		this.category = category;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (category != other.category)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [login = " + login + ", password = " + password + ", category = " + category + "]";
	}
}