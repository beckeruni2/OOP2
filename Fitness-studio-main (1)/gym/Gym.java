package gym;
import gym.management.Secretary;

public class Gym {
    private static Gym instance = null;
    private String _name;
    public static Secretary _secretary;
    private int _money=0;
    public static int balance = 0;


    private Gym(){}; // private constructor

    public static Gym getInstance() {
        if(instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public void setSecretary(Person p, int salary) {
        _secretary= new Secretary(p, salary);
    }

    public Secretary getSecretary() {
        return _secretary;
    }

    public int getMoney()
    {
        return _money;
    }

    public void setMoney(int updatedMoney)
    {
        _money = updatedMoney;
    }
}
