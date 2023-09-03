package main.java_magazine;

public class CreateItem {
    private String name;
    private Integer magazineId;


    public CreateItem(String name, Integer magazineId) {
        this.name = name;
        this.magazineId = magazineId;
    }

    public CreateItem() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMagazineId(Integer magazineId) {
        this.magazineId = magazineId;
    }

    public String getName() {
        return name;
    }

    public Integer getMagazineId() {
        return magazineId;
    }

    public Item toItem(Integer withId) {
        return new Item(withId, name, magazineId);
    }
}
