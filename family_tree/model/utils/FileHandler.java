package family_tree.model.utils;

import java.io.*;

// Реализация интерфейса FileHandlerInterface
public class FileHandler implements FileHandlerInterface {

    // Метод для записи объекта в файл
    public void writeToFile(String filename, Serializable object) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(object);
        } catch (IOException e) {
            System.err.println("Произошла ошибка при записи в файл: " + e.getMessage());
        }
    }

    // Метод для чтения объекта из файла
    public Object readFromFile(String filename) {
        Object object = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            object = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Произошла ошибка при чтении из файла: " + e.getMessage());
        }
        return object;
    }
}