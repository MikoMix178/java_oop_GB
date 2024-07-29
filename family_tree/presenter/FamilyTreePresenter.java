package family_tree.presenter;

import family_tree.model.Service.Service;
import family_tree.model.tree.FamilyTree;
import family_tree.model.tree.RelationshipType;
import family_tree.view.UserInterface;
import family_tree.model.human.Human;
import java.util.List;

// Презентер, связывающий сервис и представление
public class FamilyTreePresenter {
    private Service service;
    private UserInterface view;

    public FamilyTreePresenter(Service service, UserInterface view) {
        this.service = service;
        this.view = view;
    }

    // Поиск человека по имени
    public void findHumanByName(String name) {
        Human human = service.findMember(name);
        if (human != null) {
            view.displayHuman(human);
        } else {
            view.displayError("Человек не найден в генеалогическом древе. ");
        }
    }

    // Поиск детей по имени родителя
    public void findChildrenByName(String name) {
        Human parent = service.findMember(name);
        if (parent != null) {
            List<Human> children = service.findChildren(parent);
            if (children.isEmpty()) {
                view.displayError("Детей нет");
            } else {
                view.displayChildren(children);
            }
        } else {
            view.displayError("Родитель не найден в генеалогическом древе. ");
        }
    }

    // Поиск родителя по имени ребенка
    public void findParentByName(String name) {
        Human child = service.findMember(name);
        if (child != null) {
            Human parent = service.findParent(child);
            if (parent != null) {
                view.displayParent(parent);
            } else {
                view.displayError("Родитель не найден. ");
            }
        } else {
            view.displayError("Ребенок не найден в генеалогическом древе. ");
        }
    }

    // Поиск братьев/сестер по имени ребенка
    public void findSiblingsByName(String name) {
        Human child = service.findMember(name);
        if (child != null) {
            List<Human> siblings = service.findSiblings(child);
            if (siblings.isEmpty()) {
                view.displayError("У ребенка нет братьев и сестер. ");
            } else {
                view.displaySiblings(siblings);
            }
        } else {
            view.displayError("Ребенок не найден в генеалогическом древе. ");
        }
    }

    // Добавление нового человека в древо
     public void addNewHuman(Human newHuman) { // New method
        service.addMember(newHuman);
        service.familyTree = service.getFamilyTree();
        service.saveToFile("family_tree/model/utils/familyTree.ser");
        view.displayNewHuman(newHuman);
    }

    // Вывод древа
    public void displayFamilyTree() {
        FamilyTree<Human> familyTree = service.getFamilyTree();
        view.displayFamilyTree(familyTree);
    }

    // Создание родственных связей
    public void addRelationship(String personName, String relativeName, RelationshipType type) {
        Human person = service.findMember(personName);
        Human relative = service.findMember(relativeName);
        if (person != null && relative != null) {
            service.addRelationship(person, relative, type);
            service.saveToFile("family_tree/model/utils/familyTree.ser");
            view.displaySuccess("Отношения успешно установлены");
        } else {
            view.displayError("Один или оба человека отсутсвуют в семейном древе");
        }
    }
}