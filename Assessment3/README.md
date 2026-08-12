# Formative Assessment

## Learning Outcomes

| Learning Outcome Section       |           |
| ------------------------------- | --------- |
| Object-Oriented Programming     | Section 1 |
| Testing                         | Section 2 |
| Build Pipelines / Scripting     | Section 3 |
| Relational Databases            | Section 4 |

---

## Duration

**Total time: 2 hours 30 minutes**

| Section Recommended Time                   |            |
| -------------------------------------------- | ---------- |
| Section 1 — Object-Oriented Programming      | 70 minutes |
| Section 2 — Testing                          | 30 minutes |
| Section 3 — Build Pipelines / Scripting      | 20 minutes |
| Section 4 — Relational Databases             | 30 minutes |

---

## Scoring

| Section Marks                              |         |
| -------------------------------------------- | ------- |
| Section 1 — Object-Oriented Programming      | 40      |
| Section 2 — Testing                          | 20      |
| Section 3 — Build Pipelines / Scripting      | 15      |
| Section 4 — Relational Databases             | 25      |
| **Total**                                    | **100** |

---

# Section 1 — Object-Oriented Programming

## Scenario

**Library Loan System**

You have been asked to extend a small Java application used by a community library to track its physical media collection and the loans made against it. The library lends out three kinds of items — **Books**, **DVDs**, and **Magazines** — and every item, regardless of type, can be checked out, returned, and reported on. Each type also has its own rules: books accrue a longer loan period than DVDs, magazines cannot be renewed, and DVDs incur a higher late fee than the other two.

The current codebase represents every item as a single `LibraryItem` class with a `type` field (a `String`) and a large `if/else` block inside `calculateLateFee()` that branches on that field. Your job in this section is to redesign and extend this system properly using object-oriented principles, and to demonstrate your understanding of the underlying concepts.

A partially working Maven project has been provided in the `library-system/` folder. It compiles but does not yet reflect good OOP design.

| Task Marks What earns marks           |    |                                                                                                                                                                                     |
| -------------------------------------- | -- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Q1.1 — The Four Pillars                | 8  | Correct definition of each pillar (4, 1 each), correct and specific example drawn from the Library Loan System for each pillar (4, 1 each)                                          |
| Q1.2 — Class Hierarchy Design          | 6  | Correct identification of shared vs varying behaviour (2), sensible abstract class or interface choice with justification (2), correct UML class diagram of the hierarchy (2)      |
| Q1.3 — Implement the Hierarchy         | 14 | Abstract class/interface correctly defined (3), all three subclasses correctly implemented (6, 2 each), polymorphic method correctly overridden per subclass (3), encapsulation of fields via constructors/getters, no public fields (2) |
| Q1.4 — Composition over Inheritance    | 7  | Correct definition of composition and inheritance (2), correct explanation of why `Loan` should *have a* `LibraryItem` rather than *be* one (3), working implementation of the `Loan` class using composition (2)                          |
| Q1.5 — Coupling and Cohesion           | 5  | Correct definitions of both (2), accurate assessment of the original `LibraryItem` class's cohesion (2), one concrete suggestion for reducing coupling elsewhere in the codebase (1) |

---

### Q1.1 — The Four Pillars *(8 marks)*

Define **encapsulation**, **inheritance**, **polymorphism**, and **abstraction**. For each pillar, give one specific example of how it applies (or should apply) to the Library Loan System described above.

---

### Q1.2 — Class Hierarchy Design *(6 marks)*

Before writing any code, design the class hierarchy on paper.

- Identify which behaviour is shared across `Book`, `DVD`, and `Magazine`, and which behaviour differs between them.
- Decide whether the shared contract should be an **abstract class** or an **interface**, and justify your choice.
- Draw a simple UML class diagram (you may describe it in text form, e.g. using indentation or arrows, if you are not submitting an image) showing the hierarchy, its fields, and its methods.

---

### Q1.3 — Implement the Hierarchy *(14 marks)*

In the provided project, replace the single `LibraryItem` class with a proper class hierarchy:

- Create an abstract class (or interface, matching your answer to Q1.2) named `LibraryItem` that declares a method `calculateLateFee(int daysLate)`.
- Implement `Book`, `DVD`, and `Magazine` as subclasses, each overriding `calculateLateFee()` with its own rule:
  - `Book`: R2 per day late, no cap.
  - `DVD`: R5 per day late, capped at R50.
  - `Magazine`: flat R10 fee if returned late at all, regardless of how many days.
- All fields must be `private`, set through the constructor, and exposed only through getters where necessary. No field may be declared `public`.
- Your classes must compile and the existing `Main.java` demo, which creates one of each item and prints its late fee for 10 days late, must run without modification.

---

### Q1.4 — Composition over Inheritance *(7 marks)*

Define **composition** and contrast it with **inheritance**. The system needs a `Loan` class that represents a single loan transaction: it links a `LibraryItem` to a borrower and a due date. Explain why `Loan` should be built using composition (a `Loan` *has a* `LibraryItem`) rather than by making `Loan` a subclass of `LibraryItem`.

Then implement the `Loan` class with:

- A private `LibraryItem` field, a private `String` borrowerName field, and a private `LocalDate` dueDate field.
- A constructor that sets all three.
- A method `isOverdue(LocalDate today)` that returns `true` if `today` is after `dueDate`.

---

### Q1.5 — Coupling and Cohesion *(5 marks)*

Define **coupling** and **cohesion**. Was the original `LibraryItem` class (with its `type` field and large `if/else` block) highly cohesive or poorly cohesive, and why? Give one concrete suggestion, unrelated to the class hierarchy work above, for reducing coupling elsewhere in a typical Java application (for example, between a class and the way it is constructed, or between a class and the classes it depends on).

---

# Section 2 — Testing

| Task Marks                          |    |
| ------------------------------------ | -- |
| Q2.1 — Types of Testing              | 8  |
| Q2.2 — Unit Testing the Hierarchy    | 8  |
| Q2.3 — Test Doubles                  | 4  |

---

### Q2.1 — Types of Testing *(8 marks)*

Define **unit testing**, **integration testing**, and **regression testing**. For each, explain at what point in development it typically happens, and give an example of what a test of that type would look like for the Library Loan System from Section 1.

---

### Q2.2 — Unit Testing the Hierarchy *(8 marks)*

Using JUnit 5, write unit tests for the `calculateLateFee()` method you implemented in Q1.3. Your tests must cover:

- `Book`: a normal late fee calculation.
- `DVD`: a case where the fee would exceed the R50 cap, proving the cap is applied.
- `Magazine`: that the fee is a flat R10 regardless of whether it is 1 day or 30 days late.

Each test must use a clear, descriptive method name and at least one assertion with a meaningful failure message.

---

### Q2.3 — Test Doubles *(4 marks)*

Define what a **mock** is in the context of unit testing, and explain why you might use one instead of a real object. If the `Loan` class needed to send a real email notification when an item became overdue, explain briefly how you would test `isOverdue()` without actually sending an email.

---

# Section 3 — Build Pipelines / Scripting

| Task Marks                              |   |
| ----------------------------------------- | - |
| Q3.1 — Build Tools and CI                 | 7 |
| Q3.2 — Write a Build/Verification Script  | 8 |

---

### Q3.1 — Build Tools and CI *(7 marks)*

Explain what a **build tool** (such as Maven) is responsible for, distinct from what an IDE does. Then explain what a **CI (Continuous Integration) pipeline** is, and describe, step by step, what should happen automatically when a developer pushes new code to the Library Loan System's repository, from the moment the push happens to the point where the team knows whether the change is safe to merge.

---

### Q3.2 — Write a Build/Verification Script *(8 marks)*

Write a shell script named `verify.sh` that a developer could run locally before pushing code, which automates the following steps in order, stopping immediately if any step fails:

1. Clean any previous build output.
2. Compile the project.
3. Run the test suite.
4. Package the project into a jar.
5. Print a clear success message only if all previous steps succeeded.

Your script should use the project's Maven commands (`mvn clean`, `mvn compile`, `mvn test`, `mvn package`) and must exit with a non-zero status code if any step fails, so it could later be used inside a CI pipeline.

---

# Section 4 — Relational Databases

## Scenario

The library wants to move the Loan system from an in-memory list to a real relational database, so that loan records persist between runs of the application. You will design the schema, then connect to it from Java using JDBC.

| Task Marks                                  |    |                                                                                                                                                                            |
| --------------------------------------------- | -- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Q4.1 — The Relational Model                   | 6  | Correct definitions of table, row, column, primary key, foreign key (5, 1 each), correct identification of the relationship between `loans` and `library_items` (1)     |
| Q4.2 — Normalisation                          | 5  | Correct explanation of normalisation and why it matters (2), correct identification of a normalisation problem in the given unnormalised table (2), corrected design (1) |
| Q4.3 — JDBC Connection and Table Creation     | 6  | Correct connection setup using `DriverManager` (2), correct `CREATE TABLE` statement with appropriate types and constraints (2), resources closed correctly (2)         |
| Q4.4 — CRUD with JDBC                         | 8  | Correct `INSERT` using `PreparedStatement` (2), correct `UPDATE` (2), correct `DELETE` (2), correct `SELECT` with results read and printed (2)                          |

---

### Q4.1 — The Relational Model *(6 marks)*

Define, in your own words: **table**, **row**, **column**, **primary key**, and **foreign key**. Given a `library_items` table (one row per physical item) and a `loans` table (one row per loan transaction, as in Q1.4), explain how these two tables would be related, and which table would hold the foreign key.

---

### Q4.2 — Normalisation *(5 marks)*

Explain what normalisation means and why it matters when designing a relational database. Consider the following single, unnormalised table a junior developer proposed for storing loans:

| loan_id | borrower_name | item_title       | item_type | due_date   |
| ------- | -------------- | ----------------- | --------- | ---------- |
| 1       | J. Dlamini     | Clean Code         | Book      | 2026-08-20 |
| 2       | J. Dlamini     | The Matrix         | DVD       | 2026-08-15 |

Identify one specific problem with this design (for example, related to repeated or duplicated data), and briefly describe how you would split it into two or more properly normalised tables to fix it.

---

### Q4.3 — JDBC Connection and Table Creation *(6 marks)*

Using the provided `library.db` SQLite database (or an equivalent local database of your choice), write Java code that:

- Establishes a connection using `DriverManager.getConnection(...)`.
- Creates a table called `loans` with the following columns: `id` (integer, primary key, auto-increment), `borrower_name` (text, not null), `item_title` (text, not null), `due_date` (text, not null), `returned` (boolean, default false).
- Closes the connection (and any other JDBC resources you open) correctly, using try-with-resources.

---

### Q4.4 — CRUD with JDBC *(8 marks)*

Using the `loans` table from Q4.3, write Java methods using `PreparedStatement` (not string-concatenated SQL) that:

1. **Add** a new loan record, given a borrower name, item title, and due date.
2. **Update** a loan record to mark it as returned, given its `id`.
3. **Delete** a loan record, given its `id`.
4. **Select and print** all loan records that have not yet been returned, in the format `[id] borrower_name — item_title (due due_date)`.

Your methods must use parameterised placeholders (`?`) for all user-supplied values, not string concatenation, to avoid SQL injection.

---

### End of Assessment

---

## Project structure

```
library-system/
├── src/main/java/com/wtc/library/
│   ├── Main.java                    # Demo entry point
│   ├── model/                       # LibraryItem hierarchy, Loan
│   └── db/                          # JDBC connection and DAO classes
├── src/test/java/com/wtc/library/
│   └── model/                       # JUnit 5 tests
├── verify.sh                        # Your build/verification script (Q3.2)
└── library.db                       # SQLite database used in Section 4
```

## Useful Commands You Can Run

Compile the project:

```
mvn compile
```

Package it into a jar:

```
mvn package
```

## Running the tests

```
mvn test
```

## Running the demo

After compiling, run the `Main` class directly:

```
mvn compile
java -cp target/classes com.wtc.library.Main
```