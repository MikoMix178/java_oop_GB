package family_tree.model.tree;

import family_tree.model.utils.FileHandler;
import family_tree.model.utils.FileHandlerInterface;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class FamilyTree<T extends Node<T>> implements Serializable, Iterable<T>, IterableInterface<T> {
    private List<T> members;
    private transient FileHandlerInterface fileHandler;

    public FamilyTree() {
        members = new ArrayList<>();
        fileHandler = new FileHandler();
    }

    public void addMember (T member) {
        members.add(member);
    }

    public T findMember(String name) {
    for (T member : members) {
        if (member.getName().equalsIgnoreCase(name)) {
            return member;
        }
        T foundChild = findMemberRecursive(member, name);
        if (foundChild != null) {
            return foundChild;
        }
    }
    return null;
}

    private T findMemberRecursive(T member, String name) {
        for (T child : member.getChildren()) {
            if (child.getName().equalsIgnoreCase(name)) {
                return child;
            }
            T foundGrandChild = findMemberRecursive(child, name);
            if (foundGrandChild != null) {
                    return foundGrandChild;
            }
        }
        return null;
    }

     public void saveToFile(String filename) {
        fileHandler.writeToFile(filename, this);
    }

     public static <T extends Node<T>> FamilyTree<T> loadFromFile(String filename) {
        FileHandler fileHandler = new FileHandler();
        return (FamilyTree<T>) fileHandler.readFromFile(filename);
    }

    @Override
    public List<T> sortByName(List<T> members) {
        Collections.sort(members, Comparator.comparing(T::getName));
        return members;
    }

    @Override
    public List<T> sortByBirthDate(List<T> members) {
        Collections.sort(members, Comparator.comparing(T::getBirthDate));
        return members;
    }

    @Override
    public Iterator<T> iterator() {
        return members.iterator();
    }
}