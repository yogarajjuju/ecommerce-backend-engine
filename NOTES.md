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