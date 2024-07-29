package family_tree.model.Service;

import family_tree.model.human.Human;
import family_tree.model.tree.FamilyTree;
import family_tree.model.tree.RelationshipType;

import java.util.List;

// Сервисный файл проекта
public class Service {
    public FamilyTree<Human> familyTree;

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

    // Добавление нового члена семьи
     public void addMember(Human newHuman) { // New method
        familyTree.addMember(newHuman);
        saveToFile("family_tree/model/utils/familyTree.ser");
    }

    // Вывод древа
     public FamilyTree<Human> getFamilyTree() {
        return familyTree;
    }

    // Сохранение в файл
    public void saveToFile(String filename) {
        familyTree.saveToFile(filename);
    }

    // Загрузка из файла
    public static Service loadFromFile(String filename) {
        FamilyTree<Human> familyTree = FamilyTree.loadFromFile(filename);
        return new Service(familyTree);
    }

    // Добавление родственных связей
    public void addRelationship(Human person, Human relative, RelationshipType type) {
        familyTree.addRelationship(person, relative, type);
    }
}
