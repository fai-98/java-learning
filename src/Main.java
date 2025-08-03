import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // run only what you want by commenting others

//        runBasicLoop();
//        runFibonacci();
//        runLambdaAndFunctionalInterfaces();
//        runStreamApiExamples();
//        runMultiThreading();
        runJdbcDemo();
    }

    public static void runBasicLoop() {
        System.out.println("Hello and welcome!");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }

    public static int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static void runFibonacci() {
        System.out.println(fib(7));
        System.out.println("Testing Java is Running...");
    }

    public static void runLambdaAndFunctionalInterfaces() {
        Calculator addValAndTen = (a, b) -> a + b + 10;
        System.out.println(addValAndTen.operate(5, 10));

        Supplier<String> nameSupplier = () -> "Faisal";
        System.out.println("My Name is: " + nameSupplier.get());

        Consumer<Integer> totalCountConsumer = (tc) -> System.out.println("Total Count is: " + tc);
        totalCountConsumer.accept(69);

        Function<Integer, Integer> fiboFunction = (n) -> fib(n);
        System.out.println("The nth fibo number is: " + fiboFunction.apply(8));

        Predicate<Integer> isEven = (n) -> n % 2 == 0;
        System.out.println(isEven.test(8));
    }

    public static void runStreamApiExamples() {
        List<Integer> salaryList = new ArrayList<>();
        salaryList.add(3100);
        salaryList.add(4000);
        salaryList.add(2000);
        salaryList.add(3600);
        salaryList.add(1800);

        long count = salaryList.stream().filter(salary -> salary > 3000).count();
        System.out.println(count);

        List<String> names = List.of("Alice", "Bob", "Charlie");
        Stream<String> stream = names.stream();

        Stream<String> lowerCaseNames = stream.map(name -> name.toLowerCase());
        lowerCaseNames.forEach(System.out::println);

        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);

        List<List<String>> listOfLists = List.of(
                List.of("a", "b"),
                List.of("c", "d", "a", "b"),
                List.of("e")
        );

        List<String> flatList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(flatList);
    }

    public static void runMultiThreading() {
        MultiThreadingPractical mtRun = new MultiThreadingPractical();
//        mtRun.runThreadsUisngExtends();

        for (int i = 1; i <= 3; i++) {
            MultiThreadingImplement myThing = new MultiThreadingImplement(i);
            Thread myThread = new Thread(myThing);
            myThread.start();
        }
    }

    public static void runJdbcDemo() {
        UserDAO dao = new UserDAO();
        try {
            dao.createUserTable();
            dao.insertUser("Faisal", "faisa22@gm.com");
            dao.insertUser("Speed", "speed33@yopmail.com");
            dao.listUsers();
//
            dao.updateUser(1, "fsss", "null");
            dao.deleteUser(2);

            List<Integer> idsToDelete = Arrays.asList(8,9,10,11,12,13,14,15);
            dao.deleteUsersByIds(idsToDelete);

            dao.listUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}