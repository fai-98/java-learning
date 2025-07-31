# 🧠 Java Functional Programming & Streams – Notes

---

## ✅ What is a Functional Interface?

A **Functional Interface** is an interface that has only **one abstract method** (also known as **SAM – Single Abstract Method**).  
It is used as the target type for **lambda expressions** or **method references** introduced in Java 8.

```java
@FunctionalInterface
interface MyFunction {
    void execute();  // Single abstract method
}
```

> 🔸 Even without the `@FunctionalInterface` annotation, it still works.  
> ✅ But using the annotation gives **compile-time safety**.

---

## 🔹 What is an Abstract Method in Java?

An **abstract method** is a method with **no body** — only a signature.  
Subclasses must **override and implement** it.

```java
abstract class Animal {
    abstract void makeSound();  // Abstract method
}
```

### Why use Abstract Methods?

- Enforce that subclasses **implement common behavior**
- Promote **abstraction**: define *what* to do, not *how*
- Useful in large applications and frameworks

```java
abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    double radius = 5;

    double area() {
        return Math.PI * radius * radius;
    }
}
```

---

## 🔷 What is a Lambda Expression in Java?

A **lambda expression** is a short block of code like an **anonymous function**.  
It can be assigned to a **functional interface**.

```java
(parameters) -> { body }
(int a, int b) -> { return a + b; }
(a, b) -> a + b
```

```java
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

MathOperation add = (a, b) -> a + b;
System.out.println(add.operate(5, 3));  // Output: 8
```

---

## 🔸 Common Functional Interfaces (java.util.function)

### ✅ Predicate<T>
Checks a condition and returns a boolean

```java
Predicate<String> isShort = s -> s.length() < 5;
System.out.println(isShort.test("cat"));  // true
```

### ✅ Consumer<T>
Consumes a value and performs an action

```java
Consumer<Integer> print = n -> System.out.println(n);
print.accept(10);  // Output: 10
```

### ✅ Function<T, R>
Transforms input of type T to output of type R

```java
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("hello"));  // Output: 5
```

### ✅ Supplier<T>
Supplies a value of type T (no input)

```java
Supplier<Double> random = () -> Math.random();
System.out.println(random.get());  // e.g. 0.5476
```

---

## 💡 Streams in Java

### ❓ Can a Stream be reused?
> ❌ **No**, Java streams are single-use.  
Once a **terminal operation** is called (like `.forEach()`, `.count()`), the stream is **consumed** and cannot be reused.

### ❓ Why is Stream Lazy?
Streams are **lazy** to improve performance.

- **Intermediate operations** (`map()`, `filter()`, etc.) are **not executed immediately**
- Only run when a **terminal operation** (`collect()`, `forEach()`, `count()`) is triggered

---

## ⚡ What is a Parallel Stream?

A **parallel stream** breaks data into chunks and processes them **in parallel using multiple threads**, leveraging multi-core CPUs.

```java
list.parallelStream().forEach(...);
```

### 🔍 .stream() vs .parallelStream()

| Feature                | `.stream()` (Sequential) | `.parallelStream()` (Parallel)      |
|------------------------|--------------------------|--------------------------------------|
| Threads used           | Single thread            | Multiple threads (ForkJoinPool)      |
| Order maintained       | Yes                      | No (unless handled manually)         |
| Small data performance | Fast or equal            | Sometimes slower (thread overhead)   |
| Large data performance | Slower                   | Often faster (multi-core parallelism)|

> ☑️ Uses **ForkJoin Pool**, which is also relevant in **multithreading**.
