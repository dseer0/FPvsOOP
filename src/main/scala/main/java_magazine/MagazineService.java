package main.java_magazine;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MagazineService {
    public final MagazineHolder magazineHolder;
    public final ItemHolder itemHolder;

    public MagazineService(MagazineHolder magazineHolder, ItemHolder itemHolder) {
        this.magazineHolder = magazineHolder;
        this.itemHolder = itemHolder;
    }

    public List<Item> getItemsFromMagazine(Integer magazineId) {
        return itemHolder.getItemList()
                .stream()
                .filter(item -> item.getMagazineId().equals(magazineId))
                .collect(Collectors.toList());
    }
}
