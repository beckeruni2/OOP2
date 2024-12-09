package gym.management;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import gym.Exception.*;
import gym.Gym;
import gym.Person;
import gym.customers.Client;
import gym.management.Sessions.*;

public class Secretary extends Person {

    private int _salary;
    public static ArrayList<Client> _clients = new ArrayList<Client>();
    private ArrayList<Instructor> _instructors = new ArrayList<Instructor>();
    private ArrayList<String> messageActions = new ArrayList<String>();


    public Secretary(Person person, int salary) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        _salary = salary;
        messageActions.add("A new secretary has started working at the gym: " + person.getName());
        Gym._secretary = this;
    }

    public int get_salary() {
        return _salary;
    }


    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {

        
        if (!validAge(person.getBirthDate())) {
            throw new InvalidAgeException();
        }
        
        if (Gym.getInstance().getSecretary() != null  && Gym.getInstance().getSecretary().getClients().contains(person)) {
            new DuplicateClientException();
        }
        Client client = new Client(person);
        Gym.getInstance().getSecretary().getClients().add(client);

        messageActions.add("Registered new client: " + person.getName());
        return client;  
    }

    public ArrayList<Client> getClients() {
        return _clients;
    }


    //gymSecretary.notify(s4, "The instructor will be a few minutes late for the session");

    public void notify(Session session, String message)
    {
        ArrayList<Client> clients = session.getClients();
        if(clients.size() == 0) return; 
        for(Client client : clients)
        {
            client.update(message);
        }
    }


    //gymSecretary.notify("Happy New Year to all our valued clients!");
    public void notify(String date, String message)
    {
        ArrayList<Session> sessions = Session.sessionsThatDay(date);
        if(sessions.size() == 0) return; 
        for(Session session : sessions)
        {
            notify(session, message);
        }
    }

    public void notify(String message)
    {
        if(_clients.size() == 0) return; 
        for(Client client : _clients)
        {
            client.update(message);
        }
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!Gym.getInstance().getSecretary().getClients().contains(client)) {
            throw new ClientNotRegisteredException();
        }
        Gym.getInstance().getSecretary().getClients().remove(client);

        messageActions.add("Unregistered client: " + client.getName());
    }




    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes)
    {
        Instructor instructor = new Instructor(person, salary, sessionTypes);
        messageActions.add("Hired new instructor: " + person.getName());
        return instructor;
    }

    //Session s1 = gymSecretary.addSession(SessionType.Pilates, "23-01-2025 10:00", ForumType.All, i2);

    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException
    {
        if(!instructor.getSessionTypes().contains(sessionType))
        {
            throw new InstructorNotQualifiedException();
        }

        Session session = new Session(sessionType, date, forumType, instructor);
        messageActions.add("Added new session: " + sessionType + " " + date);
        return session;
    }


    //gymSecretary.registerClientToLesson(c1, s1);

    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException, NullPointerException, ClientNotRegisteredException
    {
        if(!(_clients.contains(client)))
        {
            throw new ClientNotRegisteredException();
        }
        if(this != Gym._secretary)
        {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        if(session.getClients().contains(client))
        {
            throw new DuplicateClientException();
        }
        session.addClient(client);
    }

    public static boolean validForumType(Session session){return true;}; //update this


    public static boolean validDate(String date){
        String[] arr = date.split("-");
        int year = Integer.parseInt(arr[2]);
        if(year >= LocalDate.now().getYear())
        {
            return true;
        }
        return false;
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

    public boolean validSessionDate(String dateTime)
    {
        return (Calendar.getInstance().before(convertToCalendar(dateTime)));
    }

    public static Calendar convertToCalendar(String dateTime) {
        String[] parts = dateTime.split(" ");
        String datePart = parts[0];
        String timePart = parts[1];
        
        String[] dateComponents = datePart.split("-");
        int day = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]) - 1; // Calendar months are 0-based
        int year = Integer.parseInt(dateComponents[2]);
        
        String[] timeComponents = timePart.split(":");
        int hour = Integer.parseInt(timeComponents[0]);
        int minute = Integer.parseInt(timeComponents[1]);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);
        
        return calendar;
    }



    // gymSecretary.paySalaries();
    public void paySalaries()
    {
        for(Instructor instructor : _instructors)
        {
            Gym.balance -= instructor._salary;
            instructor.payInstructor();
        }
            
    }


    public void printActions()
    {
        for(String message: messageActions)
            System.out.println(message);
    }

    
}
