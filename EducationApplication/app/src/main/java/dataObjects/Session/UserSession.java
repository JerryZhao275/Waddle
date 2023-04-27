package dataObjects.Session;

public abstract class UserSession {

    protected State state = new UserLoginSession(this);

    public abstract boolean login(String username, String password);

    public abstract boolean logout();
}