package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    // вставка в сортированный массив
    @Override
    protected void insertElement(Resume r, int index) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    // Вычисляем количество элементов для сдвига влево и обнуляем последний элемент
    @Override
    protected void fillDeletedElement(int index) {
        // количество элементов для сдвига в массиве (вычисляем оставшиеся справа от удаляемого элемента)
        // если последний то size -1 или (size = 10, index 5 итого сдвинуть 4 элемента и й последнему присвоить значение null)
        int elementsToMove = size - index - 1;
        if (elementsToMove > 0) {
            System.arraycopy(storage, index + 1, storage, index, elementsToMove);
        }
    }

    // массив обязательно должен быть отсортирован, так как метод binarySearch использует метод equals
    @Override
    protected int getIndex(String uuid) {
        Resume key = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, key);
    }
}
