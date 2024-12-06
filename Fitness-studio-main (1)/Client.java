public class Client extends Person {
    public Client(Person person) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
    }
}