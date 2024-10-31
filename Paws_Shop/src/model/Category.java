package model;

public class Category {
    private int id;
    private String name;

    // Constructors
    public Category() {}

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters

    // Getter and Setter for 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
     // Override toString() method
    @Override
    public String toString() {
        return name;
    }
}

