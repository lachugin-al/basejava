package webapp.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

class AbstractArrayStorageTest {
    private final IArrayStorage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    // блок инициализации применяем если переменную необходимо
    // проиницализировать при применении условий (вычисление, эксепшен и тд)
    // если блок static то он подгружается при подгрузке класса - 1 раз
    // если не static то подгружается каждый раз когда конструируется объект
    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_4 = new Resume(UUID_4);
    }

    public AbstractArrayStorageTest(IArrayStorage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void size() throws Exception {
        assertSize(3);
        // assertEquals(3, storage.size());
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        assertSize(0);
        // assertEquals(0, storage.size());
    }

    @Test
    void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));
    }

    @Test
    void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test
    void saveExist() throws StorageException {
        try {
            storage.save(RESUME_1);
            fail();
        } catch (final ExistStorageException e) {
        }
    }

    @Test
    void saveOverflow() throws StorageException {
        for (int i = 3; i <AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        try {
            storage.save(new Resume());
            fail();
        } catch (final StorageException e) {
        }
    }

    @Test
    void delete() throws Exception {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        try {
            storage.get(UUID_1);
            fail();
        } catch (final NotExistStorageException e) {
        }
    }

    @Test
    void deleteNotExist() throws StorageException {
        try {
            storage.delete("dummy");
            fail();
        } catch (final NotExistStorageException e) {
        }
    }

    @Test
    void get() throws Exception {
        assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test
    void getNotExist() throws StorageException {
        try {
            storage.get("dummy");
            fail();
        } catch (final NotExistStorageException e) {
        }
    }

    @Test
    void getAll() throws Exception {
        Resume[] array = storage.getAll();
        assertEquals(3, array.length);
        assertEquals(RESUME_1, array[0]);
        assertEquals(RESUME_2, array[1]);
        assertEquals(RESUME_3, array[2]);
    }

    // метод для сравнения size
    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}