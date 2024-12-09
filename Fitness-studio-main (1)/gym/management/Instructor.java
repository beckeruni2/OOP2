package gym.management;
import gym.Person;

import java.util.ArrayList;

import gym.management.Sessions.SessionType;

public class Instructor extends Person {
    int wallet=0;
    int _salary;
    ArrayList<SessionType>  _sessionType;


    public Instructor(Person person,int salary,ArrayList<SessionType> sessionType) 
    {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        _salary = salary;
        _sessionType = new ArrayList<SessionType>(sessionType);
    }


    public ArrayList<SessionType> getSessionTypes()
    {
        return _sessionType;
    }


    public void payInstructor()
    {
        wallet += _salary;
    }
}
