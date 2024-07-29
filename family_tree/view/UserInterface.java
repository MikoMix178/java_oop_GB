package family_tree.view;

import family_tree.model.human.Gender;
import family_tree.model.tree.FamilyTree;
import family_tree.model.tree.RelationshipType;
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
        System.out.println("5. Добавить нового человека");
        System.out.println("6. Вывести семейное древо");
        System.out.println("7. Добавить родственную связь");
        System.out.println("8. Выход");

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
                System.out.print("Введите имя нового человека: ");
                String newName = scanner.nextLine();
                System.out.print("Введите дату рождения (ДД-ММ-ГГГГ): ");
                String birthDate = scanner.nextLine();
                System.out.print("Введите пол (Мужской/Женский): ");
                String genderStr = scanner.nextLine();
                Gender gender;
                if (genderStr.equalsIgnoreCase("Мужской")) {
                    gender = Gender.Мужской;
                } else if (genderStr.equalsIgnoreCase("Женский")) {
                    gender = Gender.Женский;
                } else {
                    System.out.println("Неверный формат ввода пола. Используйте Мужской или Женский.");
                    return;
                }
                Human newHuman = new Human(newName, birthDate, gender);
                presenter.addNewHuman(newHuman);
                break;
            case 6:
                presenter.displayFamilyTree();
                break;
            case 7:
                System.out.print("Введите имя человека: ");
                String personName = scanner.nextLine();
                System.out.print("Выберите тип связи (PARENT/CHILD): ");
                String typeStr = scanner.nextLine();
                RelationshipType type = typeStr.equalsIgnoreCase("PARENT") ? RelationshipType.PARENT : RelationshipType.CHILD;
                System.out.print("Введите имя родителя/ребенка: ");
                String relativeName = scanner.nextLine();
                presenter.addRelationship(personName, relativeName, type);
                break;
            case 8:
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

    // Вывод информации о добавленном человеке
    @Override
    public void displayNewHuman(Human newHuman) { // New method
        System.out.println("Новый человек добавлен: " + newHuman.getName() + ", Дата рождения: " + newHuman.getBirthDate() + ", Пол: " + newHuman.getGender());
    }

    // Вывод всего древа
    @Override
    public void displayFamilyTree(FamilyTree<Human> familyTree) {
        System.out.println("Генеалогическое древо:");
        for (Human human : familyTree) {
            System.out.println(human.getName() + " (" + human.getBirthDate() + ") - " + human.getGender());
            if (!human.getChildren().isEmpty()) {
                System.out.print("Дети: ");
                for (Human child : human.getChildren()) {
                    System.out.print(child.getName() + " (" + child.getBirthDate() + ") - " + child.getGender() + "; ");
                }
                System.out.println();
            }
        }
    }

    // Вывод сообщение об успехе
    @Override
    public void displaySuccess(String message) {
        System.out.println("Успешно: " + message);
    }
}