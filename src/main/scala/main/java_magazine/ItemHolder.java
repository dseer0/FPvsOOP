package main.java_magazine;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemHolder {
    private Integer id = 0;
    private final List<Item> itemList = new LinkedList<>();

    public synchronized List<Item> getItemList() {
        return itemList;
    }

    public synchronized Item addItem(CreateItem createItem) {
        Item toAdd = createItem.toItem(id);
        id = id + 1;
        itemList.add(toAdd);
        return toAdd;
    }

    public synchronized Item deleteItem(Integer id) {
        Optional<Item> found = itemList.stream().filter(mag -> mag.getId().equals(id)).findFirst();
        found.ifPresent(itemList::remove);
        return found.orElse(null);
    }

    public synchronized Item getItemWithName(String name) {
        return itemList.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public synchronized Item getItemWithId(Integer id) {
        return itemList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
