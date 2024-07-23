package family_tree.model.tree;

import family_tree.model.human.Gender;

import java.io.Serializable;
import java.util.List;
import java.time.LocalDate;

public interface Node<T> extends Serializable {
    String getName();
    void addChild(T human);
    List<T> getChildren();
    LocalDate getBirthDate();
    Gender getGender();
    T getParent();
    List<T> getSiblings();
}
