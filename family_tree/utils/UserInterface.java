package family_tree.utils;

import family_tree.human.Human;
import family_tree.tree.FamilyTree;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private FamilyTree<Human> familyTree;
    private Scanner scanner;

    public UserInterface(FamilyTree<Human> familyTree) {
        this.familyTree = familyTree;
        this.scanner = new Scanner(System.in);
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
                    findHumanByName();
                    break;
                case 2:
                    findChildrenByName();
                    break;
                case 3:
                    findParentByName();
                    break;
                case 4:
                    findSiblingsByName();
                    break;
                case 5:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверная команда. Попробуйте снова.");
            }
    }

    private void findHumanByName() {
        System.out.print("Введите имя человека: ");
        String name = scanner.nextLine();
        Human human = familyTree.findMember(name);
        if (human != null) {
            System.out.println("Человек найден: " + human.getName() + ", Дата рождения: " + human.getBirthDate() + ", Пол: " + human.getGender());;
        } else {
            System.out.println("Человек не найден в генеалогическом древе.");
        }
    }

    private void findChildrenByName() {
        System.out.print("Введите имя родителя: ");
        String name = scanner.nextLine();
        Human parent = familyTree.findMember(name);
        if (parent != null) {
            List<Human> children = parent.getChildren();
            if (children.isEmpty()) {
                System.out.println(name + " не имеет детей.");
            } else {
                System.out.println("Дети " + name + ":");
                children = familyTree.sortByBirthDate(children);
                children = familyTree.sortByName(children);
                for (Human child : children) {
                    System.out.println("Имя: " + child.getName() + ", Дата рождения: " + child.getBirthDate() + ", Пол: " + child.getGender());
                }
            }
        } else {
            System.out.println("Родитель не найден в генеалогическом древе.");
        }
    }

    private void findParentByName() {
        System.out.print("Введите имя ребенка: ");
        String name = scanner.nextLine();
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        Human child = familyTree.findMember(name);
        if (child != null) {
            Human parent = child.getParent();
            if (parent != null) {
                System.out.println("Родитель: " + parent.getName() + ", Дата рождения: " + parent.getBirthDate() + ", Пол: " + parent.getGender());
            } else {
                System.out.println("Родитель не найден.");
            }
        } else {
            System.out.println("Ребенок не найден в генеалогическом древе.");
        }
    }

    private void findSiblingsByName() {
        System.out.print("Введите имя ребенка: ");
        String name = scanner.nextLine();
        Human child = familyTree.findMember(name);
        if (child != null) {
            List<Human> siblings = child.getSiblings();
            if (siblings.isEmpty()) {
                System.out.println("У ребенка нет братьев и сестер.");
            } else {
                System.out.println("Братья и сестры: ");
                for (Human sibling : siblings) {
                    System.out.println("Имя: " + sibling.getName() + ", Дата рождения: " + sibling.getBirthDate() + ", Пол: " + sibling.getGender());
                }
            }
        } else {
            System.out.println("Ребенок не найден в генеалогическом древе.");
        }
    }
}
