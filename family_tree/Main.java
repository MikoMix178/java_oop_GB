package family_tree;

import family_tree.human.Gender;
import family_tree.tree.FamilyTree;
import family_tree.human.Human;
import family_tree.utils.UserInterface;

public class Main {
    public static void main(String[] args) {
        FamilyTree<Human> familyTree = createFamilyTree();
        UserInterface ui = new UserInterface(familyTree);
        ui.start();
    }

    private static FamilyTree<Human> createFamilyTree() {
        FamilyTree<Human> familyTree = new FamilyTree<>();

        Human human1 = new Human("Максим", "01-01-1980", Gender.Мужской);
        Human human2 = new Human("Алиса", "01-01-1985", Gender.Женский);
        Human human3 = new Human("Михаил", "01-01-1975", Gender.Мужской);
        Human human4 = new Human("Эмилия", "01-01-1990", Gender.Женский);
        Human human5 = new Human("Давид", "01-01-1995", Gender.Мужской);

        familyTree.addMember(human1);
        familyTree.addMember(human2);
        familyTree.addMember(human3);
        familyTree.addMember(human4);
        familyTree.addMember(human5);

        Human child1 = new Human("Никита", "01-01-2015", Gender.Мужской);
        Human child2 = new Human("Кристина", "01-01-2012", Gender.Женский);
        Human child3 = new Human("Кирилл", "01-01-2010", Gender.Мужской);
        Human child4 = new Human("София", "01-01-2017", Gender.Женский);
        Human child5 = new Human("Яков", "01-01-2019", Gender.Мужской);
        Human child6 = new Human("Милана", "01-01-2020", Gender.Женский);
        Human child7 = new Human("Егор", "01-01-2022", Gender.Мужской);

        human1.addChild(child1);
        human1.addChild(child2);
        human1.addChild(child3);
        human2.addChild(child4);
        human2.addChild(child5);
        human2.addChild(child6);
        human3.addChild(child7);

        familyTree.saveToFile("family_tree/utils/familyTree.ser");

        return FamilyTree.loadFromFile("family_tree/utils/familyTree.ser");
    }
}
