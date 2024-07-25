package family_tree.model.human;

import family_tree.model.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

// Реализация класса Human (человека)
public class Human implements Node<Human>, Serializable {
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private List<Human> children;
    private Human parent;

    public Human(String name, String birthDate, Gender gender) {
        // Инициализация имени, даты рождения, пола и списка детей
        this.name = name;
        this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.gender = gender;
        this.children = new ArrayList<>();
    }

    // Добавление ребенка к человеку
    public void addChild(Human child) {
        children.add(child);
        child.setParent(this);
    }

    // Получение списка детей
    public List<Human> getChildren() {
        return children;
    }

    // Получение имени
    public String getName() {
        return name;
    }

    // Получение даты рождения
    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Получение пола
    public Gender getGender() {
        return gender;
    }

    // Получение родителя
    public Human getParent() {
        return parent;
    }

    // Установка родителя
    public void setParent(Human parent) {
        this.parent = parent;
    }

    // Получение списка братьев/сестер
    public List<Human> getSiblings() {
        if (parent == null) {
            return new ArrayList<>();
        }
        List<Human> siblings = new ArrayList<>(parent.getChildren());
        siblings.remove(this);
        return siblings;
    }

}