import java.util.ArrayList;
import java.util.List;

public class LetovCollection {
    private final List<String> strings = new ArrayList<>();

    // Метод для добавления строки в коллекцию
    public void add(String value) {
        strings.add(value);
    }

    // Метод для получения стрима из коллекции
    public List<String> getStrings() {
        return new ArrayList<>(strings);
    }

    public static void main(String[] args) {
        LetovCollection col = new LetovCollection();

        // Добавление строк в коллекцию
        col.add("Границы");
        col.add("ключ");
        col.add("переломлен");
        col.add("пополам");

        // Примеры запросов с использованием Stream API

        // 1. Фильтрация строк, начинающихся с буквы "п"
        List<String> filteredStrings = col.getStrings()
                .stream()
                .filter(s -> s.startsWith("п"))
                .toList();

        System.out.println("Строки, начинающиеся с буквы 'п': " + filteredStrings);

        // 2. Преобразование всех строк в верхний регистр
        List<String> uppercaseStrings = col.getStrings()
                .stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println("Строки в верхнем регистре: " + uppercaseStrings);

        // 3. Ограничение вывода первых двух элементов
        List<String> limitedStrings = col.getStrings()
                .stream()
                .limit(2)
                .toList();

        System.out.println("Первые две строки: " + limitedStrings);

        // 4. Подсчет количества строк, содержащих букву "а"
        long strCount = col.getStrings()
                .stream()
                .filter(s -> s.contains("а"))
                .count();

        System.out.println("Количество строк, содержащих букву 'а': " + strCount);

        // 5. Объединение всех строк в одну строку через пробел
        String concat = String.join(" ", col.getStrings());

        System.out.println("Объединенная строка: " + concat);
    }
}