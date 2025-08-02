import java.util.ArrayList;
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
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        System.out.println("Hello and welcome!");
//
//        for (int i = 1; i <= 5; i++) {
//            System.out.println("i = " + i);
//        }

//        System.out.println(fib(7));
//        System.out.println("Testing Java is Running...");

        //use a lambda expression to implement the functional interface:
        Calculator addValAndTen = (a,b) ->{
            return a + b + 10;
        };
        System.out.println(addValAndTen.operate(5,10));

//        4 Types of imp functional interfaces

//        Supplies a value, no input
        Supplier<String> nameSupplier = ()->"Faisal";
        System.out.println("My Name is: " + nameSupplier.get());

//        Consumer<T> – Consumes a value, no return
        Consumer<Integer> totalCountConsumer = (tc)-> System.out.println("Total Count is: " + tc);
        totalCountConsumer.accept(69);

//        Function<T, R> – Takes one input, returns one output
        Function<Integer, Integer> fiboFunction = (n)->{
            return fib(n);
        };

        //same implementation using method reference
//        Function<Integer, Integer> fiboFunction = Main::fib;



        System.out.println("The nth fibo number is: "+fiboFunction.apply(8));

//        Predicate<T> – Tests a condition, returns boolean
        Predicate<Integer> isEven = (n)-> n%2 == 0;
        System.out.println(isEven.test(8));



        //STREAM API IN JAVA
        //1. Create Stream -> 2.Intermediate Operations(multiple) -> 3.Terminal Opr(only 1)

//        🔁 Summary Table
//        Operation	Type	Purpose
//        stream()	Intermediate	Start a stream
//        filter(Predicate)	Intermediate	Keep matching elements
//        map(Function)	Intermediate	Transform elements
//        sorted()	Intermediate	Sort elements
//        forEach()	Terminal	Act on each element
//        collect(Collectors.toList())	Terminal	Gather result
//        reduce()	Terminal	Aggregate into a single value

        List<Integer> salaryList = new ArrayList<>();
        salaryList.add(3100);
        salaryList.add(4000);
        salaryList.add(2000);
        salaryList.add(3600);
        salaryList.add(1800);

        //without stream we loop and keep count var and check if salary > 3000
        //with stream count() returns long

                                //create intermediate                      terminal
        long count = salaryList.stream().filter(salary->salary>3000).count();
        System.out.println(count);



        List<String> names = List.of("Alice", "Bob", "Charlie");
        Stream<String> stream = names.stream();

        //Map
        Stream<String> lowerCaseNames =  stream.map((name -> name.toLowerCase()));
        lowerCaseNames.forEach(System.out::println);

        //Filter
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println); // Output: Alice


//        flatMap() is used to flatten a stream of streams into a single stream.
//        It transforms each element into a stream and then flattens all those inner streams into one.

        List<List<String>> listOfLists = List.of(
                List.of("a", "b"),
                List.of("c", "d", "a", "b"),
                List.of("e")
        );


//        Stream<Stream<String>> listStream = listOfLists.stream().map(List::stream);
        //it will return If you use .map(), you'll get a Stream<List<String>>:

        //we need flatMap

        List<String> flatList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(flatList); // Output: [a, b, c, d, e]

        //distinct(), sorted(), peek(), limit(), skip(), mapToInt(), mapToLong(), mapToDouble()


        //terminal operators forEach(), toArray(), reduce(), collect(), min and max, count(),
        //anyMatch(), allMatch(), noneMatch(), findFirst()



        //Multithreading using extends thread
        MultiThreadingPractical mtRun = new MultiThreadingPractical();
//        mtRun.runThreadsUisngExtends();

        //Multithreading using implements runnnable
       for(int i=1; i<=3; i++){
           MultiThreadingImplement myThing = new MultiThreadingImplement(i);
           Thread myThread = new Thread(myThing);
           myThread.start();
       }
    }



    //fibonacci recursive solution
    public static int fib(int n) {
        if(n == 0 || n == 1) return n;
        return fib(n-1) + fib(n-2);
    }

}