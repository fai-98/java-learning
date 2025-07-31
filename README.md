What is a Functional Interface?
A Functional Interface is an interface that has only one abstract method (SAM – Single Abstract Method).

Used as the target type for lambda expressions or method references in Java 8+.


@FunctionalInterface
interface MyFunction {
void execute();  // Single abstract method
}


Even if not annotated with @FunctionalInterface, it's still valid — but using the annotation gives compile-time safety.


🔹 What is an Abstract Method in Java?
An abstract method is a method without a body — only the method signature is provided, and subclasses must implement it.

abstract class Animal {
abstract void makeSound();  // abstract method
}


Think of it as a placeholder — "this method must be implemented by any concrete subclass."

🔹 Why Are Abstract Methods Needed?
Force subclasses to implement common behavior

E.g., every animal "makes a sound", but how depends on the animal.

Supports abstraction (OOP principle)

You define "what" should be done, not "how".

Used in frameworks and large applications

Allows base classes to define a template, while subclasses fill in the details.


abstract class Shape {
abstract double area();  // No body — subclasses must define this
}

class Circle extends Shape {
double radius = 5;
double area() {
return Math.PI * radius * radius;
}
}


🔷 What is a Lambda Expression in Java?
A lambda expression is a short block of code which takes in parameters and returns a value — like an anonymous function. It was introduced in Java 8 to enable functional programming using functional interfaces.

(parameters) -> { body }
(int a, int b) -> { return a + b; }
(a, b) -> a + b


You can only assign a lambda expression to a functional interface — an interface with a single abstract method.
@FunctionalInterface
interface MathOperation {
int operate(int a, int b);
}

// Lambda for addition
MathOperation add = (a, b) -> a + b;
System.out.println(add.operate(5, 3));  // Output: 8



checks a condition
Predicate<String> isShort = s -> s.length() < 5;
System.out.println(isShort.test("cat"));  // true

consumes a value
Consumer<Integer> print = n -> System.out.println(n);
print.accept(10);  // Output: 10

transforms input to output
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("hello"));  // Output: 5

Supplier<T> is a functional interface from java.util.function package that:
Takes no input
Returns a result of type T



❓ Can a Stream be reused?
❌ No, Java streams cannot be reused.
Once a stream has been consumed (i.e., a terminal operation like .collect(), .forEach(), .count() is called), it cannot be used again.

❓ Why is Stream lazy?
Streams in Java are designed to be lazy to improve performance and efficiency.

✅ What does laziness mean?
Intermediate operations (like .map(), .filter(), .sorted()) are not executed immediately.
They are only executed when a terminal operation is called (.collect(), .forEach(), .count() etc.).


✅ What is a Parallel Stream in Java?
A parallel stream divides the stream’s data into multiple chunks, processes them in parallel using multiple threads, and then combines the results.

It’s useful when you want to speed up operations on large collections by leveraging multi-core CPUs.

🔍 Difference: .stream() vs .parallelStream()
Feature	.stream() (Sequential)	.parallelStream() (Parallel)
Threads used	Single thread	ForkJoinPool (multiple threads)
Order maintained	Yes	No (unless explicitly handled)
Performance (small data)	Often faster or same	Sometimes slower (overhead)
Performance (large data)	May be slower	Often faster (multi-core)


ForkJoin Pool Technique will also come in MultiThreading


