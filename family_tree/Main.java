package family_tree;

import family_tree.model.Service.Service;
import family_tree.model.tree.FamilyTree;
import family_tree.model.human.Human;
import family_tree.presenter.FamilyTreePresenter;
import family_tree.view.UserInterface;


public class Main {
      public static void main(String[] args) {
           FamilyTree<Human> familyTree;
        try {
            familyTree = Service.loadFromFile("family_tree/model/utils/familyTree.ser").getFamilyTree();
        } catch (Exception e) {
            familyTree = new FamilyTree<>();
            TestTree.createAndSaveFamilyTree(); // Если файла нет, создается тестовое древо
        }
        Service service = new Service(familyTree); // Создание сервиса для работы с древом
        UserInterface view = new UserInterface(); // Создание пользовательского интерфейса
        FamilyTreePresenter presenter = new FamilyTreePresenter(service, view); // Создание презентера для связи сервиса и интерфейса
        view.setPresenter(presenter); // Установка презентера для интерфейса
        view.start();  // Запуск интерфейса
      }
}
