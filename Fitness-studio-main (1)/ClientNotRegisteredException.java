
public class ClientNotRegisteredException extends Exception {
    public ClientNotRegisteredException() {
        super("Error: Registration is required before attempting to unregister");
    }

}
