import java.time.LocalDate;
import java.util.ArrayList;

public class Secretary extends Person {
    
    private int _salary;
    private ArrayList<Client> _clients = new ArrayList<Client>();

    public Secretary(Person person, int salary) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        _salary = salary;

        System.out.println("A new secretary has started working at the gym: " + person.getName());
    }

    public int get_salary() {
        return _salary;
    }

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (!validAge(person.getBirthDate())) {
            throw new InvalidAgeException();
        }
        if (Gym.getInstance().getSecretary().getClients().contains(person)) {
            throw new DuplicateClientException();
        }
        Client client = new Client(person);
        Gym.getInstance().getSecretary().getClients().add(client);
        System.out.println("Registered new client: " + person.getName());
        return client;  
    }

    public ArrayList<Client> getClients() {
        return _clients;
    }


    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!Gym.getInstance().getSecretary().getClients().contains(client)) {
            throw new ClientNotRegisteredException();
        }
        Gym.getInstance().getSecretary().getClients().remove(client);
        System.out.println("Unregistered client: " + client.getName());
    }




    public static  boolean validAge(String at){
    String[] arr = at.split("-");
    int year = Integer.parseInt(arr[2]);
    if(year + 18 <LocalDate.now().getYear())
    {
        return true;
    }
    else if(year + 18 == LocalDate.now().getYear())
    {
        int month = Integer.parseInt(arr[1]);
        if(month < LocalDate.now().getMonthValue())
        {
            return true;
        }
        else if(month == LocalDate.now().getMonthValue())
        {
            int day = Integer.parseInt(arr[0]);
            if(day <= LocalDate.now().getDayOfMonth())
            {
                return true;
            }
        }
    }
    return false;
}
}
