package family_tree.tree;

import family_tree.human.Human;

import java.util.List;

public interface IterableInterface {
    //List<Human> sortByName(List<Human> humans); сортировка по имени
    List<Human> sortByBirthDate(List<Human> humans);
}