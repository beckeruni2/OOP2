package gym.management.Sessions;


//new Session(sessionType, date, forumType, instructor);

import java.util.ArrayList;

import gym.customers.Client;
import gym.management.Instructor;
//observer design pattern
public class Session {
    private SessionType _sessionType;
    private int _cost;
    private int _maxClients;
    private String _time;
    private Instructor _instructor;
    private ForumType _forumType;
    public ArrayList<Client> _clients = new ArrayList<Client>();
    public static ArrayList<Session> _sessions = new ArrayList<Session>();


    public Session(SessionType sessionType, String time, ForumType forumType, Instructor instructor) {
        _sessionType = sessionType;
        _cost = sessionType.getCost();
        _maxClients = sessionType.getMaxClients();
        _time = time;
        _forumType = forumType;
        _instructor = instructor;
        _sessions.add(this);
    }

    
    public static ArrayList<Session> sessionsThatDay(String date)
    {
        ArrayList<Session> ans = new ArrayList<Session>();
        String day = date.split(" ")[0];
        for (Session session : _sessions)
        {
            if (session._time.split(" ")[0].equals(day))
            {
                ans.add(session);
            }
        }
        return ans;
    }

    public ArrayList<Client> getClients() {
        return _clients;
    }

    public void removeClient(Client client) {
        _clients.remove(client);
    }

    public ArrayList<Client> addClient(Client client) {
        _clients.add(client);
        return _clients;
    }

    public int getCost()
    {
        return _cost;
    }
}
