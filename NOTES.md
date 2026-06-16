# Module 1: Core Java Backend Concepts

## 1. Encapsulation
* **What it is:** Keeping class variables `private` and only allowing access via `public` methods (getters/setters).
* **Why it matters:** It protects data integrity. For example, it prevents external code from manually changing a product's price or stock to a negative number without going through our validation logic.

## 2. HashMap Internals (Interview VIP)
* **Time Complexity:** `O(1)` for `put()` and `get()` operations.
* **How it works:** 1. Uses an array of "buckets" (default size 16).
  2. Runs a math formula (hash function) on the Key to find the exact bucket index.
* **Collisions:** Happens when two different keys hash to the *same* bucket.
* **Resolution:** Java stores them in a Linked List within that bucket. In Java 8+, if a bucket gets crowded (more than 8 items), it optimizes into a Red-Black Tree to maintain fast search speeds.

## 3. Custom Exceptions & Error Handling
* **Checked vs. Unchecked:**
  * `RuntimeException` (Unchecked): The compiler does not force you to handle it. (e.g., our `InsufficientStockException`).
  * `Exception` (Checked): The compiler forces you to use a `try-catch` block or declare `throws` in the method signature.
* **The `throw` Keyword:** Used to actively trigger an error condition (`throw new InsufficientStockException(...)`).
* **The `try-catch` Block:** Attempts "dangerous" code. If an exception is thrown, the app doesn't crash; execution jumps to the `catch` block to handle the error gracefully.


## 4. Interfaces & Polymorphism
* **Interface:** A "contract" in Java. It defines *what* a class must do (e.g., `processPayment()`), but not *how* it does it. 
* **Polymorphism:** Meaning "many forms." It allows us to treat different objects (like a `CreditCardPayment` and a `UPIPayment`) as the exact same type (`PaymentProcessor`).
* **Why it matters (The Interview Answer):** It creates **Loose Coupling**. Our `Main` application doesn't need to know how a credit card API or a UPI API works under the hood. It just calls `.processPayment()` and trusts that the underlying object knows what to do. This makes adding new payment methods in the future (like PayPal or Crypto) incredibly easy without rewriting the core checkout logic.


# Module 2: The Data Layer & Containerization

## 1. Docker & Containers
* **What it is:** A tool that packages software into standardized units called "containers."
* **Why we use it (The Interview Answer):** It solves the "It works on my machine" problem. Instead of installing MySQL directly on an OS and fighting with dependencies, Docker spins up an isolated, pristine server that runs exactly the same way on any developer's computer.

## 2. Relational Databases (SQL)
* **`CREATE TABLE`:** Acts like a Java Class. It builds the strict blueprint (schema) for the data.
* **`PRIMARY KEY`:** A rule ensuring every row has a unique identifier (like our Product ID), making searches incredibly fast.
* **`DECIMAL(10,2)` vs `DOUBLE`:** Always use `DECIMAL` for financial data in SQL to prevent floating-point rounding errors.

## 3. JDBC (Java Database Connectivity)
* **What it is:** The official Java API ("The Waiter") that allows our Java code to talk to relational databases using a driver (`.jar` file).
* **The Process:**
  1. `Connection`: Logs into the database using URL, User, and Password.
  2. `PreparedStatement`: A pre-compiled SQL statement. We use this instead of standard `Statement` because it prevents SQL Injection attacks and allows us to safely pass Java variables into SQL using `?` placeholders.
  3. `ResultSet`: The "tray" that holds the data MySQL sends back to Java after a `SELECT` query.

# Module 3: Spring Boot & Web APIs (REST)

## 1. Maven (The Package Manager)
* **What it is:** A build tool that automatically downloads dependencies (like our MySQL JDBC driver) from the internet and compiles our code.
* **`pom.xml`:** The "shopping list." Instead of manually downloading `.jar` files, we write what we need in this file, and Maven grabs it automatically.

## 2. Spring Boot (The Framework)
* **What it is:** An enterprise Java framework that comes with a built-in web server (Tomcat). It removes the need to write complex server infrastructure from scratch.
* **The Analogy:** If pure Java is building an engine from scratch, Spring Boot is buying a fully assembled sports car.

## 3. Web API Concepts
* **REST API:** A way for different software systems to communicate over the internet using standard HTTP protocols.
* **`@RestController`:** An annotation that tells Spring Boot, "This Java class is a Drive-Thru window open to the internet."
* **`@GetMapping("/products")`:** Tells the server to run a specific method when a user visits that exact URL.
* **JSON (JavaScript Object Notation):** The universal language of the web. Spring Boot automatically converts our Java `List<Product>` into formatted JSON text before sending it to the browser.
