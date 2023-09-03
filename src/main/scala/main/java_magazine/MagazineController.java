package main.java_magazine;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MagazineController {
    public final MagazineHolder magazineHolder;
    public final ItemHolder itemHolder;
    public final MagazineService magazineService;

    public MagazineController(MagazineHolder magazineHolder, ItemHolder itemHolder, MagazineService magazineService) {
        this.magazineHolder = magazineHolder;
        this.itemHolder = itemHolder;
        this.magazineService = magazineService;
    }

    @GetMapping("/magazine/all")
    public List<Magazine> getAllMagazines() {
        return magazineHolder.getMagazines();
    }

    @GetMapping("/magazine/items/{id}")
    public List<Item> getItemsInMagazine(@PathVariable Integer id) {
        return magazineService.getItemsFromMagazine(id);
    }

    @GetMapping("/magazine/item")
    public Item getItemWithName(@RequestParam String name) {
        return itemHolder.getItemWithName(name);
    }

    @GetMapping("/magazine/item/{id}")
    public Item getItemWithId(@RequestParam Integer id) {
        return itemHolder.getItemWithId(id);
    }

    @PostMapping("/magazine")
    public void addMagazine(@RequestBody CreateMagazine createMagazine) {
        magazineHolder.addMagazine(createMagazine);
    }

    @PostMapping("/magazine/item")
    public void addItem(@RequestBody CreateItem createItem) {
        itemHolder.addItem(createItem);
    }

    @DeleteMapping("/magazine/{id}")
    public void deleteMagazine(@RequestParam Integer id) {
        magazineHolder.deleteMagazine(id);
    }

    @DeleteMapping("/magazine/item/{id}")
    public void deleteItem(@RequestParam Integer id) {
        itemHolder.deleteItem(id);
    }

}