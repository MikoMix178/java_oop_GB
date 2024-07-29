package family_tree.view;

import family_tree.model.human.Human;
import family_tree.model.tree.FamilyTree;

import java.util.List;

// Интерфейс для представления
public interface View {
    void displayHuman(Human human);
    void displayError(String message);
    void displayChildren(List<Human> children);
    void displayParent(Human parent);
    void displaySiblings(List<Human> siblings);
    void displayNewHuman(Human newHuman);
    void displayFamilyTree(FamilyTree<Human> familyTree);
    void displaySuccess(String message);
}