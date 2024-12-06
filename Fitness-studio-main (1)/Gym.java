


// using singleton pattern

public class Gym {
    private static Gym instance = null;
    private String _name;
    private Secretary _secretary;


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
}
