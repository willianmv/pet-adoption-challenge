package dao;

import java.util.List;

public interface IFileDAO<T> {

    void saveFile(T element, String directoryPath);

    List<T> loadFromFile(String filePath);

}
