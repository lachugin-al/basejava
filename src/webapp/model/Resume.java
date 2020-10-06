package webapp.model;

// имплементируем интерфейс Comparable так как необходимо чтобы объекты сравнивались для сортированного массива
// Compare возвращает число -1 если меньше, 0 равно, +1 если больше
// после алгоритм двоичного поиска будет работать
// Comparable<Resume> дженерики - позволяет создавать тип
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    // переопределяем сравнение обьектов для сравнения по uuid
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }


    // если compareTo.uuid меньше другого то и резюме в массиве лежит раньше по индексу
    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}