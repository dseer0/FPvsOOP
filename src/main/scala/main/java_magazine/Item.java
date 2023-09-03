package main.java_magazine;

public class Item {
    private final Integer id;
    private String name;
    private final Integer magazineId;

    public Item(Integer id, String name, Integer magazineId) {
        this.id = id;
        this.name = name;
        this.magazineId = magazineId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMagazineId() {
        return magazineId;
    }
}
