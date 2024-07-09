import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

class FamilyTree implements Serializable {
    private List<Human> humans;
    private FileHandler fileHandler;

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
}

