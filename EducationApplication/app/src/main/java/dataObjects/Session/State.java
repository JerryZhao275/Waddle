package dataObjects.Session;

public abstract class State {

    protected final UserSession session;

    public State(UserSession machine) {
        this.session = machine;
    }

    public abstract void handle(Event event);

    public UserSession getSession() {
        return session;
    }
}
