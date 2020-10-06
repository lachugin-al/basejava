package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;

// общие части которые относятся к ArrayStorage переносим в абстрактный класс
// абстрактный класс не может иметь инстанс
public abstract class AbstractArrayStorage implements IArrayStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        // если index < 0 то резюме нет
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    // поиск по индексу общий для ArrayStorage и SortedArrayStorage
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        // если index > 0 то резюме найдено
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (size >= storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        // индивидуальная часть алгоритма для ArrayStorage и SortedArrayStorage
        } else {
            insertElement(r, index);
            size++;
        }
    }

    // поиск по индексу общий для ArrayStorage и SortedArrayStorage
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        // индивидуальная часть алгоритма для ArrayStorage и SortedArrayStorage
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage,0, size);
    }

    protected abstract void insertElement(Resume r, int index);

    // в ArrayStorage заменяем удаляемый элемент последним с однулением последнего
    // в SortedArrayStorage удаляем элемент, сдвигаем массив влево и обнуляем последний элемент
    protected abstract void fillDeletedElement(int index);

    protected abstract int getIndex(String uuid);
}
