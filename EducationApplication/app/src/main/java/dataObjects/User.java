package dataObjects;

public abstract class User {

    final private int uuid;
    private String email;
    private String firstName;
    private String lastName;

    public User(int uuid) {
        this.uuid = uuid;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public User withName(String firstName, String lastName) {
        return this.withFirstName(firstName).withLastName(lastName);
    }

    public User withFirstName(String email) {
        this.firstName = email;
        return this;
    }

    public User withLastName(String email) {
        this.lastName = email;
        return this;
    }

    public Integer getUUID(){
        return uuid;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

}
