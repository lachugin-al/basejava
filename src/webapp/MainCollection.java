package webapp;

import webapp.model.Resume;

import java.util.*;

public class MainCollection {
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);

        // нельзя удалять элемент в коллекции когда по ней проходим к примеру циклом
        for (Resume r : collection) {
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
//                collection.remove(r);
            }
        }

        /*Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next().getUuid(), UUID_1)) {
                iterator.remove();
            }
        }*/
        collection.removeIf(resume -> Objects.equals(resume.getUuid(), UUID_1));

        System.out.println(collection.toString());


        Map<String, Resume> map = new HashMap<>() {
            // добавим блок инициализации
            {
                put(UUID_1, RESUME_1);
                put(UUID_2, RESUME_2);
                put(UUID_3, RESUME_3);
            }
        };

        // map.keySet получаем значения всех ключей в map через Set
        // и каждый раз ходим в map
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }
        // map.entrySet получаем значения всех пар в map через Set
        // и из пары берем значение entry.getValue
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
