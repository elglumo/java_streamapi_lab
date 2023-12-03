import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;

public class MainstreamAPI {
    public static void main(String[] args) {
        // Создаем Supplier для генерации стрима случайных целых чисел
        Supplier<IntStream> randomIntStreamSupplier = () ->
                new Random().ints(1000, 10, 10000);

        // 1) Запрос со сбором данных в коллекцию
        List<Integer> collectedList = randomIntStreamSupplier.get()
                .boxed()  // преобразование в Stream<Integer>
                .toList();

        System.out.println("Список из стрима: " + collectedList);

        // 2) Запрос на подсчет количества простых чисел
        long countOfPrimes = randomIntStreamSupplier.get()
                .filter(MainstreamAPI::isPrime)
                .count();

        System.out.println("Количество простых чисел: " + countOfPrimes);

        // 3) Запрос, где применяем редукцию множества значений к одному
        int sum = randomIntStreamSupplier.get().sum();
        System.out.println("Сумма всех чисел: " + sum);

        // 4) Просто вывод на консоль
        System.out.println("Первые 10 чисел:");
        randomIntStreamSupplier.get().limit(10).forEach(System.out::println);

        // 5) Запрос с формированием результата в карту
        Map<Integer, List<Integer>> groupedByDigitCount = randomIntStreamSupplier.get()
                .boxed()
                .collect(Collectors.groupingBy(MainstreamAPI::countDigits));

        System.out.println("Карта, сгруппированная по количеству цифр:\n" + groupedByDigitCount);
    }

    // Проверка простых чисел
    private static boolean isPrime(int number) {
        if (number < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(number); i++)
            if (number % i == 0)
                return false;
        return true;
    }

    // Подсчет количества цифр в числе
    private static int countDigits(int number) {
        return String.valueOf(Math.abs(number)).length();
    }
}
