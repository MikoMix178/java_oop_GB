package family_tree.model.utils;

import java.io.Serializable;

public interface FileHandlerInterface  {
   void writeToFile(String filename, Serializable object);
   Object readFromFile(String filename);
}
