package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public interface IArrayStorage {

    void clear();

    void update(Resume r);

    void save(Resume r);

    void delete(String uuid);

    Resume get(String uuid);

    Resume[] getAll();

    int size();
}
