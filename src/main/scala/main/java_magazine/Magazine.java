package main.java_magazine;

public class Magazine {
    private final Integer id;
    private String name;

    public Magazine(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
