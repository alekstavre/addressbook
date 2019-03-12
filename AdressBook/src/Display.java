import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Display {
    private enum Task {
        ADD_PERSON,
        FIND_PERSON,
        DISPLAY_ALL,
        EXIT
    }

    private enum Option {
        FIRST_NAME,
        SURNAME
    }

    private Record dataFile;
    private Scanner input;
    private List<Person> people;

    public Display(Record dataFile) {
        input = new Scanner(System.in);
        this.dataFile = dataFile;
        try {
            people = dataFile.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Display(String fileName) {
        this(new Record(fileName));
    }

    public Display(File file) {
        this(new Record(file));
    }

    private List<Person> findPerson(String searchString, Option filter) {
        switch (filter) {
            case FIRST_NAME: return people.stream().filter(person -> person.getSurname().equals(searchString)).collect(Collectors.toList());
            case SURNAME: return people.stream().filter(person -> person.getName().equals(searchString)).collect(Collectors.toList());
            default:
                System.out.println("Invalid filter option");
                return new ArrayList<>();
        }
    }

    public void run() {
        while(true) {
            Task action = MainMenu();
            switch (action) {
                case ADD_PERSON:
                    Person person = insertData();
                    dataFile.save(person);
                    people.add(person);
                    break;
                case FIND_PERSON:
                    Option selectedFilter = displayData();
                    System.out.print("Enter name: ");
                    String searchString = input.nextLine();
                    List<Person> filteredPeople = findPerson(searchString, selectedFilter);
                    if (filteredPeople.size() == 0) {
                        System.out.println("No matches");
                    } else {
                        for (Person p : filteredPeople)
                            System.out.println(p);
                    }
                    break;
                case DISPLAY_ALL:
                    System.out.println(this.people);
                    System.out.println();
                    break;
                case EXIT:
                    System.out.println("Exiting Program");
                    System.exit(0);
                    break;
            }
        }
    }

    private Task MainMenu() {
        System.out.println("1. Add person");
        System.out.println("2. Find person");
        System.out.println("3. Show all contacts");
        System.out.println("4. Close program");
        String choice;
        do {
            choice = input.nextLine();
            switch (choice) {
                case "1": return Task.ADD_PERSON;
                case "2": return Task.FIND_PERSON;
                case "3": return Task.DISPLAY_ALL;
                case "4": return Task.EXIT;
                default: System.out.println("Enter a number from 1 to 4");
            }
        } while (!choice.equals("4"));
        return null; //should never reach here
    }

    private Person insertData() {
        System.out.println("Enter first name: ");
        String firstName = input.nextLine();
        System.out.println("Enter surname: ");
        String surname = input.nextLine();
        System.out.println("Enter addres: ");
        String address = input.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = input.nextLine();
        return new Person(firstName, surname, address,phoneNumber);
    }

    private Option displayData() {
        System.out.println("1. Find with name");
        System.out.println("2. Find with surname");
        System.out.println();
        String choice;
        do {
            choice = input.nextLine();
            switch (choice) {
                case "1":  return Option.FIRST_NAME;
                case "2": return Option.SURNAME;
                default: System.out.print("Choose 1 or 2");
            }
        } while (!choice.equals("1") && !choice.equals("2"));
        return null; //should never reach here
    }
}
