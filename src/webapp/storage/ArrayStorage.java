package webapp.storage;

import webapp.model.Resume;

// так как мы создали абстрактный класс AbstractArrayStorage, то вместо implements IArrayStorage
// мы расширяем extends AbstractArrayStorage
public class ArrayStorage extends AbstractArrayStorage {

    // вставляем элемент в массив по size
    @Override
    protected void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    // заполняем элемент последним в массиве
    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
    }

    // поиск элемента по массиву, сравнение по "uuid"
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}