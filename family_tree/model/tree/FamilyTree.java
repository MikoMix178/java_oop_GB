package family_tree.model.tree;

import family_tree.model.utils.FileHandler;
import family_tree.model.utils.FileHandlerInterface;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

// Реализация семейного дерева
public class FamilyTree<T extends Node<T>> implements Serializable, Iterable<T>, IterableInterface<T> {
    private List<T> members;
    private transient FileHandlerInterface fileHandler;

    // Инициализация списка членов дерева и обработчика файлов
    public FamilyTree() {
        members = new ArrayList<>();
        fileHandler = new FileHandler();
    }

    // Добавление члена семьи в дерево
    public void addMember (T member) {
        members.add(member);
    }

    // Поиск члена семьи по имени
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

    // Рекурсивный поиск члена семьи по имени
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

    // Сохранение дерева в файл
     public void saveToFile(String filename) {
        fileHandler.writeToFile(filename, this);
    }

    // Загрузка дерева из файла
     public static <T extends Node<T>> FamilyTree<T> loadFromFile(String filename) {
        FileHandler fileHandler = new FileHandler();
        return (FamilyTree<T>) fileHandler.readFromFile(filename);
    }

    // Сортировка списка по имени
    @Override
    public List<T> sortByName(List<T> members) {
        Collections.sort(members, Comparator.comparing(T::getName));
        return members;
    }

    // Сортировка списка по дате рождения
    @Override
    public List<T> sortByBirthDate(List<T> members) {
        Collections.sort(members, Comparator.comparing(T::getBirthDate));
        return members;
    }

    // Итератор
    @Override
    public Iterator<T> iterator() {
        return members.iterator();
    }
}