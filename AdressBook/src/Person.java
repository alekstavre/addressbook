
public class Person {


	private String name;
	private String surname;
	private String adress;
	private String phone;


	public Person(String name, String address, String surname, String phone) {
		this.name = name;
		this.surname = surname;
		this.adress = address;
		this.phone = phone;

	}


	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}


	public String getAddress() {
		return adress;
	}

	public String getPhone() {
		return phone;
	}



@Override
public String toString() {
	
	 return "\n\nName: " + getName() + "\nSurname: " + getSurname() +
			 "\nPhone: " +phone + "\nAddress: " + getAddress();
	}
}