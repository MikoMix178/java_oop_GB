package family_tree.model.human;

import family_tree.model.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Human implements Node<Human>, Serializable {
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private List<Human> children;
    private Human parent;

    public Human(String name, String birthDate, Gender gender) {
        this.name = name;
        this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.gender = gender;
        this.children = new ArrayList<>();
    }

    public void addChild(Human child) {
        children.add(child);
        child.setParent(this);
    }

    public List<Human> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public Human getParent() {
        return parent;
    }

    public void setParent(Human parent) {
        this.parent = parent;
    }

    public List<Human> getSiblings() {
        if (parent == null) {
            return new ArrayList<>();
        }
        List<Human> siblings = new ArrayList<>(parent.getChildren());
        siblings.remove(this);
        return siblings;
    }

}