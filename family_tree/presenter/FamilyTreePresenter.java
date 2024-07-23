package family_tree.presenter;

import family_tree.model.tree.FamilyTree;
import family_tree.model.human.Human;
import family_tree.view.UserInterface;

import java.util.List;
import java.util.Scanner;

public class FamilyTreePresenter {
    private FamilyTree<Human> familyTree;
    private UserInterface view;
    private Scanner scanner;

    public FamilyTreePresenter(FamilyTree<Human> familyTree, UserInterface view) {
        this.familyTree = familyTree;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void findHumanByName() {
        System.out.print("Введите имя человека: ");
        String name = scanner.nextLine();
        Human human = familyTree.findMember(name);
        if (human != null) {
            view.displayHuman(human);
        } else {
            view.displayError("Человек не найден в генеалогическом древе. ");
        }
    }

    public void findChildrenByName() {
        System.out.print("Введите имя родителя: ");
        String name = scanner.nextLine();
        Human parent = familyTree.findMember(name);
        if (parent != null) {
            List<Human> children = parent.getChildren();
            if (children.isEmpty()) {
                view.displayError("Детей нет");
            } else {
                view.displayChildren(children);
            }
        } else {
            view.displayError("Родитель не найден в генеалогическом древе. ");
        }
    }

    public void findParentByName() {
        System.out.print("Введите имя ребенка: ");
        String name = scanner.nextLine();
        Human child = familyTree.findMember(name);
        if (child != null) {
            Human parent = child.getParent();
            if (parent != null) {
                view.displayParent(parent);
            } else {
                view.displayError("Родитель не найден. ");
            }
        } else {
            view.displayError("Ребенок не найден в генеалогическом древе. ");
        }
    }

    public void findSiblingsByName() {
        System.out.print("Введите имя ребенка: ");
        String name = scanner.nextLine();
        Human child = familyTree.findMember(name);
        if (child != null) {
            List<Human> siblings = child.getSiblings();
            if (siblings.isEmpty()) {
                view.displayError("У ребенка нет братьев и сестер. ");
            } else {
                view.displaySiblings(siblings);
            }
        } else {
            view.displayError("Ребенок не найден в генеалогическом древе. ");
        }
    }
}
