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
> 
> # 🧵 Multithreading vs Multitasking - Quick Notes

---

## 🧠 Process
- A **process** is an independent unit of execution.
- Has its own **memory space** and **resources**.
- Examples: Chrome, VS Code, IntelliJ are separate processes.

---

## 🧵 Thread
- A **thread** is a lightweight sub-part of a process.
- Shares memory with other threads of the same process.
- Faster than processes in communication.

---

## 🚀 Multithreading
- Multiple **threads** running in a **single process**.
- Threads share the same memory, making context switching faster.
- Ideal for tasks like:
    - Background computations
    - Asynchronous operations
    - UI responsiveness

### ✅ Example
```java
Thread t1 = new Thread(() -> {
    System.out.println("Task 1");
});
Thread t2 = new Thread(() -> {
    System.out.println("Task 2");
});
t1.start();
t2.start();


🖥️ Multitasking
Multiple processes running at the same time.

Handled by OS (e.g., running Chrome + Spotify together).

Requires more system resources than threads.

## 🔍 Multithreading vs Multitasking

| Feature            | Multithreading                     | Multitasking                          |
|--------------------|-------------------------------------|----------------------------------------|
| Definition         | Multiple threads in one process     | Multiple processes simultaneously      |
| Memory usage       | Less (shared memory)                | More (separate memory)                 |
| Context Switching  | Faster                              | Slower                                 |
| Communication      | Easier                              | Harder                                 |
| Overhead           | Low                                 | High                                   |

📌 Summary
Thread: Unit of execution within a process.

Process: Independent running application.

Multithreading: Multiple threads, shared memory.

Multitasking: Multiple processes, independent memory.


✅ 1. What is Concurrency?
Concurrency = Dealing with multiple tasks at the same time.

It doesn't mean parallel execution always — can be interleaved on a single CPU.

Useful when tasks are I/O bound (e.g., API calls, DB operations).

✅ 2. Why Backend Devs Need It
To avoid blocking main threads.

Handle multiple requests.

Execute background jobs (@Async, @Scheduled).

Optimize response time for APIs.

✅ 3. Threads vs Concurrency
Thread = Independent unit of execution.

Concurrency = Managing access to shared resources among threads.

####### JDBC #####
| ✅ Topic                           | 📖 What You Should Know                                                                  |
| --------------------------------- | ---------------------------------------------------------------------------------------- |
| **What is JDBC?**                 | Java API to connect and interact with relational databases like MySQL, PostgreSQL, etc.  |
| **JDBC Driver**                   | The software component that allows Java to talk to the database (e.g., MySQL Connector). |
| **DriverManager**                 | Used to load JDBC drivers and create a database connection.                              |
| **Connection**                    | Represents a session with a specific database.                                           |
| **Statement / PreparedStatement** | Used to send SQL commands. Prefer `PreparedStatement` to prevent SQL injection.          |
| **ResultSet**                     | Returned by `executeQuery()` to process SELECT results.                                  |
| **try-with-resources**            | Ensures auto-closing of `Connection`, `Statement`, and `ResultSet` objects.              |
| **Basic SQL Commands**            | Be able to perform `INSERT`, `SELECT`, `UPDATE`, `DELETE`.                               |
| **JDBC vs JPA/Hibernate**         | JDBC is low-level and verbose; JPA is abstracted and cleaner to use.                     |




