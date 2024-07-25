package family_tree.model.Service;

import family_tree.model.human.Human;
import family_tree.model.tree.FamilyTree;

import java.util.List;

// Сервисный файл проекта
public class Service {
    private FamilyTree<Human> familyTree;

    public Service(FamilyTree<Human> familyTree) {
        // Инициализация семейного дерева
        this.familyTree = familyTree;
    }

    // Поиск члена семьи по имени
    public Human findMember(String name) {
        return familyTree.findMember(name);
    }

    // Получение списка детей родителя
    public List<Human> findChildren(Human parent) {
        return parent.getChildren();
    }

    // Получение родителя ребенка
    public Human findParent(Human child) {
        return child.getParent();
    }

    // Получение списка братьев/сестер ребенка
    public List<Human> findSiblings(Human child) {
        return child.getSiblings();
    }
}
