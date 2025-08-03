public class User {
    private int id;
    private String name;
    private String email;

    public User(){}

    public User(String name){
        this.name = name;
    }

    public User(int id, String name, String email){
        this.name = name;
        this.id = id;
        this.email = email;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    //we use this in setters bcz parameter name is same as private field name, so conflict name = name
    //if we don't use this it will just assign the parameter to itself, so we need to refer to the
    //Here, the parameter name  shadows the instance variable, so without this., you're just assigning the parameter to itself:


    //in getters there is not conflict

    // toString() override for easy printing
    @Override
    public String toString() {
        return id + " - " + name + " - " + email;
    }
}
