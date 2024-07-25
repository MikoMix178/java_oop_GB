package family_tree.view;

import family_tree.model.tree.FamilyTree;
import family_tree.presenter.FamilyTreePresenter;
import family_tree.model.human.Human;

import java.util.List;
import java.util.Scanner;

public class UserInterface implements View {
    private FamilyTreePresenter presenter;
    private Scanner scanner;
    private FamilyTree<Human> familyTree;

    public UserInterface () {
        this.scanner = new Scanner(System.in);
        this.familyTree = new FamilyTree<>();
    }

    // Установка презентера для взаимодействия
    public void setPresenter(FamilyTreePresenter presenter) {
        this.presenter = presenter;
    }

    // Запуск интерфейса пользователя и вывод меню
    public void start() {
        System.out.println("\nВыберите команду: ");
        System.out.println("1. Найти человека по имени");
        System.out.println("2. Вывести детей по имени родителя");
        System.out.println("3. Вывести родителя по имени ребенка");
        System.out.println("4. Вывести братьев и сестер по имени ребенка");
        System.out.println("5. Выход");

        // Получение команды от пользователя
        int command = scanner.nextInt();
        scanner.nextLine();

        // Обработка команды
        switch (command) {
            case 1:
                System.out.print("Введите имя человека: ");
                String name = scanner.nextLine();
                presenter.findHumanByName(name);
                break;
            case 2:
                System.out.print("Введите имя родителя: ");
                name = scanner.nextLine();
                presenter.findChildrenByName(name);
                break;
            case 3:
                System.out.print("Введите имя ребенка: ");
                name = scanner.nextLine();
                presenter.findParentByName(name);
                break;
            case 4:
                System.out.print("Введите имя ребенка: ");
                name = scanner.nextLine();
                presenter.findSiblingsByName(name);
                break;
            case 5:
                System.out.println("До свидания! ");
                return;
            default:
                System.out.println("Неверная команда. Попробуйте снова. ");
        }
    }

    // Вывод информации о человеке
    @Override
    public void displayHuman(Human human) {
        System.out.println("Человек найден: " + human.getName() + ", Дата рождения: " + human.getBirthDate() + ", Пол: " + human.getGender());
    }

    // Вывод сообщения об ошибке
    @Override
    public void displayError(String message) {
        System.out.println("Ошибка: " + message);
    }

    // Вывод информации о детях
    @Override
    public void displayChildren(List<Human> children) {
        children = familyTree.sortByBirthDate(children); // Сортировка по др
        children = familyTree.sortByName(children); // Сортировка по имени
        System.out.println("Дети:");
        for (Human child : children) {
            System.out.println(child.getName() + ", Дата рождения: " + child.getBirthDate() + ", Пол: " + child.getGender());
        }
    }

    // Вывод информации о родителе
    @Override
    public void displayParent(Human parent) {
        System.out.println("Родитель: " + parent.getName() + ", Дата рождения: " + parent.getBirthDate() + ", Пол: " + parent.getGender());
    }

    // Вывод информации о братьях/сестрах
    @Override
    public void displaySiblings(List<Human> siblings) {
        siblings = familyTree.sortByBirthDate(siblings); // Сортировка по др
        siblings = familyTree.sortByName(siblings); // Сортировка по имени
        System.out.println("Братья и сестры:");
        for (Human sibling : siblings) {
            System.out.println(sibling.getName() + ", Дата рождения: " + sibling.getBirthDate() + ", Пол: " + sibling.getGender());
        }
    }
}