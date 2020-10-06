package webapp;

public class MainString {
    public static void main(String[] args) {
        String[] stringArray = new String[] {"1", "2", "3", "4", "5"};

        StringBuilder stringBuilder = new StringBuilder();
        for (String str : stringArray) {
            stringBuilder.append(str).append(", ");
        }
        System.out.println(stringBuilder.toString());

        String str1 = "abc";
//        String str2 = "abc";
//        String str2 = "ab" + "c";
        String str3 = "c";
        // intern() принудительно ищет строчку в рантайме (в пуле объектов) и если находит строчку
        // то не создает новый объект, а присваивает ссылку на эту строчку
        String str2 = ("ab" + str3).intern();
        System.out.println(str1 == str2);
    }
}
