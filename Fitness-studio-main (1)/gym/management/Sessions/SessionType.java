package gym.management.Sessions;
public enum SessionType {
    Pilates(60, 30),
    MachinePilates(80, 10),
    ThaiBoxing(100, 20),
    Ninja(150, 5);

    private int _cost, _maxClients;

    SessionType(int cost, int maxClients) {
        _cost = cost;
        _maxClients = maxClients;
    }

    public int getCost()
    {
        return _cost;
    }
    
    public int getMaxClients()
    {
        return _maxClients;
    }
}
