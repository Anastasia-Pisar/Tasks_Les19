package by.company.library.main;

import java.util.Scanner;

import by.company.library.controller.Controller;

public class Main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Controller control = new Controller();
		String category = "JUNIOR"; 
		System.out.println("Wellcome to the Library!\n"
				+ "Allowed commands ([] - for optional with default values):\n"
				+ "ADDBOOK [name = A] [author = B] [year = 0] [category = JUNIOR]\n"
				+ "LOGIN login password\n"
				+ "SIGNUP login password\n"
				+ "CHANGEROLE objective_user_login [new_category = ADULT]\n"
				+ "SHOWLIB\n");
		
		String line = "";	
		System.out.print("Your command: ");
		
		if (scanner.hasNextLine()) {
			line = scanner.nextLine();
		} else {
			System.out.println("Error occured, please try again after a while");
		}
		
		while (!line.equals("EXIT") && !line.equals("Exit") && !line.equals("exit")) {
			String[] response = control.doAction(category + " " + line).split("\\s+");
			category = response[0];
			
			for (int i = 1; i < response.length; i++){
				System.out.print(response[i] + " ");
			}
			System.out.println();
			
			System.out.print("Your command: ");
			
			if (scanner.hasNextLine()) {
				line = scanner.nextLine();
			} else {
				System.out.println("Error occured, please try again after a while");
			}
		}
		
		scanner.close();
	}

}
