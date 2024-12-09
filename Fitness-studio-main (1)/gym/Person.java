package gym;
//Person p1 = new Person("David", 500, Gender.Male, "20-02-1978");


public class Person {
    
    public enum Gender {
        Male,
        Female
    }
    private String _name;
    private int _balance;
    private final Gender _gender;
    private String _birthDate;



    public Person(String name, int balance, Gender gender, String birthDate) 
    {
        _name = name;
        _balance = balance;
        _gender = gender;
        _birthDate = birthDate;
       
    }

    public String getName()
    {
        return _name;
    }

    public int getBalance() 
    {
        return _balance;
    }

    public Gender getGender() 
    {
        return _gender;
    }

    public String getBirthDate() 
    {
        return _birthDate;
    }
}
