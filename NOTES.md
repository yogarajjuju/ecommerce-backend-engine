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


---


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


---


# Module 3: Spring Boot, Web APIs (REST) & SOLID Principles

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

## 4. How the Internet talks to Java (Spring Boot Annotations)
There are two main ways a user can send data to our API:
* **`@PathVariable` (The URL Method):**
    * **The Analogy:** Shouting your order from the car.
    * **What it is:** The data is right there in the web address (e.g., `localhost:8080/products/P001`).
    * **When to use it:** For short, simple lookups like finding a single item by its ID. Used mostly with `@GetMapping`.
* **`@RequestBody` (The JSON Method):**
    * **The Analogy:** Handing the worker a folded paper order slip.
    * **What it is:** The data is sent in a secure, structured JSON package over the network, completely hidden from the URL.
    * **When to use it:** For complex or secure data, like checkout forms, quantities, or passwords. Used mostly with `@PostMapping`.

## 5. The Anatomy of a Simple REST API
Building an API in Spring Boot takes just three pieces:
1.  **The Building Tag (`@RestController`):** Opens the Java class to the internet.
2.  **The Window Tag (`@GetMapping("/status")`):** Maps a specific web URL to the Java method directly below it.
3.  **The Method:** Standard Java logic that runs and returns data (or text) straight to the user's screen.

## 6. The API & Architecture Cheat Sheet
* **@RestController:** The Drive-Thru window. 
* **@GetMapping:** Used for *reading* data (like looking at a menu). This is what web browsers default to.
* **@PostMapping:** Used for *submitting* data (like a checkout form). Web browsers cannot do this from the URL bar; it requires a hidden JSON package.
* **JSON:** A structured text format `{}` used to safely send data between completely different systems.

## 7. The 3-Tier Architecture (Single Responsibility Principle)
To prevent server crashes and keep code organized, enterprise backends are split into three separate jobs:
1. **The Controller (The Waiter):** ONLY takes orders from the internet. Hands the data to the Service.
2. **The Service (The Chef):** Does the brain work. Checks stock, calculates prices, handles errors.
3. **The Repository (The Pantry):** ONLY talks to the database. Fetches and saves data using SQL/JDBC/JPA.

## 8. The "O" - Open-Closed Principle (OCP)
* **The Rule:** Code should be **Open for extension**, but **Closed for modification**.
* **The Concept:** You should be able to add new features without ever rewriting your old code. 
* **The Implementation:** We use Java `interfaces` (The Disc Drive). If we need a new payment method, we don't hack open the core `ProductService` file. We just create a new file (The Game Disc) that `implements` the interface and slide it in.

## 9. Liskov Substitution Principle (LSP) & Architecture Traits
* **The Rule:** Child classes must be completely interchangeable with their Parent classes without breaking the application.
* **The Strategy:** Instead of forcing all classes to inherit properties they don't use (e.g., giving a Digital Download a physical shipping weight), split optional behaviors into specialized interfaces called **Traits** (like `Shippable`).
* **The Result:** The system remains generic where possible (treating everything as a `Product` during checkout) but remains highly specific where necessary, preventing runtime server crashes.

## 10. Interface Segregation Principle (ISP)
* **The Rule:** Do not force a class to implement methods it doesn't need (Don't build a TV remote with a Drone button).
* **The Strategy:** Keep interfaces incredibly small and specific (e.g., separating `PaymentProcessor` from `Refundable`). 
* **The Result:** Classes like `GiftCardPayment` remain lightweight and mathematically impossible to refund, preventing runtime crashes.

## 11. Dependency Inversion Principle (DIP)
* **The Rule:** High-level modules (The Waiter/Chef) should not depend on low-level modules (Bitcoin/Credit Card). Both should depend on abstractions (Interfaces).
* **The Strategy:** Instead of hard-coding a specific payment method inside the engine, we read the user's JSON request and dynamically plug the correct "Game Disc" into the engine.
* **The Result:** The system is 100% dynamic. The frontend controls the flow, and the backend safely processes it through the interface contract.


---


# Module 4: The Database Upgrade (JPA & MySQL)

## 1. The Core Theory: ORM (Object-Relational Mapping)
* **The Problem:** Java speaks in Objects (Classes, variables). Databases speak in Tables (Rows, SQL queries). They don't understand each other.
* **The Solution:** **JPA** (Java Persistence API) is the official translator. **Hibernate** is the engine inside Spring Boot that actually writes the SQL code for us automatically.

## 2. The Entity Checklist (Building a Table)
To turn a normal Java class into a MySQL table, we use "Sticky Notes" (Annotations).

```java
@Entity // 1. Tells Spring: "Make this a Database Table!"
@Table(name = "products") // 2. (Optional) Rename the table in SQL
public class Product {

    @Id // 3. Every table MUST have a Primary Key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. Tells MySQL to auto-count 1, 2, 3...
    private Long id;

    @Column(unique = true) // 5. Prevents duplicates in this specific column
    private String sku; 

    // 🚨 6. CRITICAL: JPA absolutely requires a completely blank constructor to work!
    public Product() {} 
}