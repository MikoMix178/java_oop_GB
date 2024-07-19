package family_tree.tree;

import java.util.List;

public interface IterableInterface<T> {
    List<T> sortByName(List<T> humans); //сортировка по имени
    List<T> sortByBirthDate(List<T> humans);
}