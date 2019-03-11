import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Records {

	static ArrayList<Person>people = new ArrayList<>();
	static Scanner input = new Scanner(System.in);
	static File file = new File ("Addressbook.txt");

	public static void main(String[] args) throws IOException {

		load();
		menu();

	}

	private static void load() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String name = null;
			while((name = reader.readLine()) != null) {
				Person person = new Person(name, name, name, name);
				people.add(person);
				reader.readLine();
			}
		}

	}

	private static void menu() {

		System.out.println("choose an option: /n");
		System.out.println("1.Add entry");
		System.out.println("2.Find entry");
		System.out.println("3.Show all entries");
		System.out.println("4.Close and exit");

		String option;

		do {

			option = input.nextLine();
			switch (option){

			case "1":
				addEntry();
				break;

			case "2":
				findEntry();
				break;

			case "3" :
				System.out.println(people);
				menu();
				break;

			case"4" :
				System.exit(0);
				break;

			default :
				System.out.println("enter num from 1 to 4 ");
			}

		}while (!option.equals("4"));

	}

	private static void save(Person person) {

		BufferedWriter writer = new BufferedWriter (new FileWriter(file, true )) {
			writer.write(person.getName()+ "\r\n" + person.getSurname() + "\r\n" +
					person.getAddress() + "\r\n" + person.getPhone()+ "\r\n\r\n");
		}

	}


	private static void addEntry() {
		System.out.println("Enter name: ");
		String name = input.nextLine();
		System.out.println("Enter surname: ");
		String surname = input.nextLine();
		System.out.println("Enter address: ");
		String address = input.nextLine();
		System.out.println("Enter phone: ");
		String phone = input.nextLine();

		Person person = new Person (name, surname, address, phone);
		save (person);
		people.add(person);
		System.out.println("added person");
		menu();

	}
	private static void findEntry() {
		System.out.println("1.search by name");
		System.out.println("1.search by surname");

		String select;
		do {
			select = input.nextLine();
			switch(select) {

			case "1" :
				findName();
				break;
			case"2" :
				findSurname();
				break;
			default:
				System.out.print("Choose 1 or 2: ");
			}


		}while(select.equals("1") && !select.equals("2"));
		menu();

	}

	private static void findName() {
		System.out.println("enter name: ");
		String searchName = input.nextLine();
		int found = 0;
		for (Person person : people) {
			if (person.getName().equals(searchName)) {
				System.out.println(person);
				found ++;
			}
		}
		if (found <=0) {
			System.out.println("no entry by that name!");
		}
	}
	private static void findSurname() {
		
		System.out.println("enter surname: ");
		String searchSurname = input.nextLine();
		int found = 0;
		for (Person person : people) {
			if (person.getName().equals(searchSurname)) {
				System.out.println(person);
				found ++;
			}
		}
		if (found <=0) {
			System.out.println("no entry by that surname!");
		}


	}
}
