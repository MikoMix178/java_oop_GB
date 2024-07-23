package family_tree.view;

import family_tree.model.human.Human;
import family_tree.model.tree.FamilyTree;
import family_tree.presenter.FamilyTreePresenter;

import java.util.List;
import java.util.Scanner;


public class UserInterface implements View {
    private FamilyTreePresenter presenter;
    private Scanner scanner;
    private FamilyTree<Human> familyTree;

    public UserInterface(FamilyTree<Human> familyTree) {
        this.scanner = new Scanner(System.in);
        this.familyTree = familyTree;
    }

    public void setPresenter(FamilyTreePresenter presenter) {
        this.presenter = presenter;
    }

    public void start() {
        System.out.println("\nВыберите команду: ");
        System.out.println("1. Найти человека по имени");
        System.out.println("2. Вывести детей по имени родителя");
        System.out.println("3. Вывести родителя по имени ребенка");
        System.out.println("4. Вывести братьев и сестер по имени ребенка");
        System.out.println("5. Выход");

        int command = scanner.nextInt();
        scanner.nextLine();

        switch (command) {
            case 1:
                presenter.findHumanByName();
                break;
            case 2:
                presenter.findChildrenByName();
                break;
            case 3:
                presenter.findParentByName();
                break;
            case 4:
                presenter.findSiblingsByName();
                break;
            case 5:
                System.out.println("До свидания! ");
                return;
            default:
                System.out.println("Неверная команда. Попробуйте снова. ");
        }
    }
    @Override
    public void displayHuman(Human human) {
        System.out.println("Человек найден: " + human.getName() + ", Дата рождения: " + human.getBirthDate() + ", Пол: " + human.getGender());
    }

    @Override
    public void displayError(String message) {
        System.out.println("Ошибка: " + message);
    }

    @Override
    public void displayChildren(List<Human> children) {
        System.out.println("Дети:");
        children = familyTree.sortByBirthDate(children);
        children = familyTree.sortByName(children);
        for (Human child : children) {
            System.out.println(child.getName() + ", Дата рождения: " + child.getBirthDate() + ", Пол: " + child.getGender());
        }
    }

    @Override
    public void displayParent(Human parent) {
        System.out.println("Родитель: " + parent.getName() + ", Дата рождения: " + parent.getBirthDate() + ", Пол: " + parent.getGender());
    }

    @Override
    public void displaySiblings(List<Human> siblings) {
        System.out.println("Братья и сестры:");
        siblings = familyTree.sortByBirthDate(siblings);
        siblings = familyTree.sortByName(siblings);
        for (Human sibling : siblings) {
            System.out.println(sibling.getName() + ", Дата рождения: " + sibling.getBirthDate() + ", Пол: " + sibling.getGender());
        }
    }
}