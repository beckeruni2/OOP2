package gym.customers;

import java.util.ArrayList;

import gym.Person;

public class Client extends Person {
    ArrayList<String> _updates = new ArrayList<String>();
    public Client(Person person) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
    }


    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }
    public void update(String string)
    {

        _updates.add(string);
        System.out.println("Client updated: " + string);
    }

    public String getNotifications()
    {
        String ans="";
        for(String update : _updates)
            ans = ans + update;
        return ans;
    }
}