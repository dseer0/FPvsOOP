package main.java_magazine;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class MagazineHolder {
    private Integer id = 0;
    private final List<Magazine> magazineList = new LinkedList<>();

    public List<Magazine> getMagazines() {
        return magazineList;
    }

    public synchronized Magazine addMagazine(CreateMagazine createMagazine) {
        Magazine toAdd = createMagazine.toMagazine(id);
        id = id + 1;
        magazineList.add(toAdd);
        return toAdd;
    }

    public synchronized Magazine deleteMagazine(Integer id) {
        Optional<Magazine> found = magazineList.stream().filter(mag -> mag.getId().equals(id)).findFirst();
        found.ifPresent(magazineList::remove);
        return found.orElse(null);
    }
}
