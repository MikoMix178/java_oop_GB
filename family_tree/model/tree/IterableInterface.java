package family_tree.model.tree;

import java.util.List;

// Интерфейс для итерируемых объектов, сортировка по имени и др
public interface IterableInterface<T> {
    List<T> sortByName(List<T> humans); //сортировка по имени
    List<T> sortByBirthDate(List<T> humans);
}