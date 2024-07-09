import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

class Human implements Serializable {
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private List<Human> children;
    private List<Human> parents;

    public Human(String name, String birthDate, Gender gender) {
        this.name = name;
        this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.gender = gender;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    public void addChild(Human child) {
        children.add(child);
        child.parents.add(this);
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
}

enum Gender {
    Мужской,
    Женский
}