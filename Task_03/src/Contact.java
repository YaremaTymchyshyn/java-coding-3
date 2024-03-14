public class Contact
{
    public String Name;
    public String Surname;
    public String Address;
    public String PhoneNumber;

    public String getName() { return Name; }
    public String getSurname() { return Surname; }
    public String getAddress() { return Address; }
    public String getPhoneNumber() { return PhoneNumber; }

    public void setName(String name) { this.Name = name; }
    public void setSurname(String surname) { this.Surname = surname; }
    public void setAddress(String address) { this.Address = address; }
    public void setPhoneNumber(String phoneNumber) { this.PhoneNumber = phoneNumber; }

    public Contact(String name, String surname, String address, String phoneNumber)
    {
        this.Name = name;
        this.Surname = surname;
        this.Address = address;
        this.PhoneNumber = phoneNumber;
    }

    @Override
    public String toString() { return Name + ", " + Surname + ", " + Address + ", " + PhoneNumber; }
}