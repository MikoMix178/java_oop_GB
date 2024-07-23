package family_tree.view;

import family_tree.model.human.Human;

import java.util.List;

public interface View {
    void displayHuman(Human human);
    void displayError(String message);
    void displayChildren(List<Human> children);
    void displayParent(Human parent);
    void displaySiblings(List<Human> siblings);
}