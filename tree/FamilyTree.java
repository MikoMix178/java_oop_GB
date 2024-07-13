package family_tree.tree;

import family_tree.human.Human;
import family_tree.utils.FileHandler;
import family_tree.utils.FileHandlerInterface;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class FamilyTree implements Serializable, Iterable<Human>, IterableInterface {
    private List<Human> humans;
    private transient FileHandlerInterface fileHandler;

    public FamilyTree() {
        humans = new ArrayList<>();
        fileHandler = new FileHandler();
    }

    public void addHuman(Human human) {
        humans.add(human);
    }

    public Human findHuman(String name) {
        for (Human human : humans) {
            if (human.getName().equals(name)) {
                return human;
            }
        }
        return null;
    }

     public void saveToFile(String filename) {
        fileHandler.writeToFile(filename, this);
    }

    public static FamilyTree loadFromFile(String filename) {
        FileHandler fileHandler = new FileHandler();
        return (FamilyTree) fileHandler.readFromFile(filename);
    }

    //@Override
    //public List<Human> sortByName(List<Human> humans) {
        //Collections.sort(humans, Comparator.comparing(Human::getName));
        //return humans;
    //}

    @Override
    public List<Human> sortByBirthDate(List<Human> humans) {
        Collections.sort(humans, Comparator.comparing(Human::getBirthDate));
        return humans;
    }

     @Override
    public Iterator<Human> iterator() {
        return humans.iterator();
    }
}