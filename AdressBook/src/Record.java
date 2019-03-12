import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Record {

	private  File file;
	
	public Record(String fileName) {
		this.file = new File(fileName);
	}
	
	public Record(File file) {
		this.file = file;
	}

	
	public List<Person> load() throws IOException {
		List<Person> people = new ArrayList<Person>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String name = null;
			while((name = reader.readLine()) != null) {
				Person person = new Person(name, reader.readLine(), reader.readLine(), reader.readLine());
				people.add(person);
				reader.readLine();
			}
			
		}
		catch (IOException e) {
			System.out.println(e);
		}
	return people;

	}
	
	public void save(Person person) {

		try (BufferedWriter writer = new BufferedWriter (new FileWriter(file, true ))) {
			writer.write(person.getName()+ "\r\n" + person.getSurname() + "\r\n" +
					person.getAddress() + "\r\n" + person.getPhone()+ "\r\n\r\n");
		}catch(IOException e) {
			System.out.println(e);
		}

	}
	
	
	
}
