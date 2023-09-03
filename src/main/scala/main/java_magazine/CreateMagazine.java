package main.java_magazine;

public class CreateMagazine {
    private String name;

    public CreateMagazine(String name) {
        this.name = name;
    }

    public CreateMagazine() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Magazine toMagazine(Integer withId) {
        return new Magazine(withId, name);
    }
}
