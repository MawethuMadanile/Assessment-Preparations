# Course Management System

A Java-based Course Management System designed as a practical software development assessment. The project demonstrates **Object-Oriented Programming, Unit Testing, CI/CD, Build Automation, Shell Scripting, Makefiles, and Relational Database integration**.

---

## 📚 Project Overview

The system manages university courses and supports different course types with different pricing rules.

The project is divided into four main areas:

1. **Object-Oriented Programming**
2. **Testing**
3. **CI/CD, Build Pipelines & Scripting**
4. **Relational Databases**

The project is intended to be built and tested using **Maven**, with additional automation provided through a **Makefile** and shell scripts.

---

## 🛠️ Technologies

* Java 17
* Maven
* JUnit 5
* Git / GitHub
* GitHub Actions
* Bash / Shell scripting
* GNU Make
* SQLite
* JDBC

---

## 📁 Project Structure

```text
course-system/
├── pom.xml
├── Makefile
├── ci-verify.sh
├── deploy.sh
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/example/course/
│   │           ├── Main.java
│   │           ├── model/
│   │           │   ├── Course.java
│   │           │   ├── ProgrammingCourse.java
│   │           │   ├── BusinessCourse.java
│   │           │   └── ShortCourse.java
│   │           │
│   │           └── service/
│   │               └── CourseService.java
│   │
│   └── test/
│       └── java/
│           └── com/example/course/
│               ├── model/
│               └── service/
│
└── .github/
    └── workflows/
        └── ci.yml
```

---

# 1. Object-Oriented Programming

The system uses an abstract `Course` class with specialised subclasses.

## Course

The base class contains:

```text
code
name
lecturer
students
price
```

It defines:

```java
public abstract double calculateFinalPrice();
```

All course types must implement this method.

---

## Course Types

### ProgrammingCourse

Programming courses have a **10% surcharge**.

```text
final price = price × 1.10
```

### BusinessCourse

Business courses receive a **5% discount** when there are more than 100 students.

```text
students <= 100
    price

students > 100
    price × 0.95
```

### ShortCourse

Short courses receive a **15% discount** when there are more than 30 students.

```text
students <= 30
    price

students > 30
    price × 0.85
```

---

# 2. CourseService

`CourseService` manages all courses using a `Map`.

```java
private Map<String, Course> courses;
```

The course code is used as the key.

The service should provide:

```java
void addCourse(Course course)

void removeCourse(String code)

Course getCourse(String code)

double getTotalRevenue()

Map<String, List<Course>> groupByType()

List<Course> getLargeCourses(int threshold)
```

Duplicate course codes must not be allowed.

---

# 3. Testing

The project uses **JUnit 5**.

Tests should cover:

* `ProgrammingCourse` pricing
* `BusinessCourse` pricing boundaries
* `ShortCourse` pricing boundaries
* `CourseService`
* Total revenue calculations
* Course filtering and grouping

Run the complete test suite with:

```bash
mvn test
```

---

# 4. CI/CD Pipeline

The project includes a CI/CD pipeline using **GitHub Actions**.

The pipeline should run when:

* code is pushed to `main`;
* code is pushed to `develop`;
* a pull request is created.

The pipeline performs:

```text
Checkout
   ↓
Set up Java 17
   ↓
Clean
   ↓
Compile
   ↓
Test
   ↓
Package
   ↓
Store JAR artifact
```

The workflow is located at:

```text
.github/workflows/ci.yml
```

---

## CI Commands

The pipeline uses Maven commands equivalent to:

```bash
mvn clean
mvn compile
mvn test
mvn package
```

If any command fails, the pipeline should fail.

This prevents code that does not build or pass its tests from being treated as a successful build.

---

# 5. Makefile

The project provides a `Makefile` to simplify common development commands.

## Build

```bash
make build
```

Runs:

```bash
mvn compile
```

## Test

```bash
make test
```

Runs the project's test suite.

## Package

```bash
make package
```

Creates the application JAR.

## Clean

```bash
make clean
```

Removes Maven build output.

## Verify

```bash
make verify
```

Runs the complete verification process:

```text
Clean
 ↓
Build
 ↓
Test
 ↓
Package
```

The Makefile uses `.PHONY` for commands that do not represent actual files.

---

# 6. CI Verification Script

The project includes:

```text
ci-verify.sh
```

This script performs the same core verification process that would be used during CI.

Run it with:

```bash
./ci-verify.sh
```

The script should:

1. Clean the project.
2. Compile the project.
3. Run the tests.
4. Package the application.
5. Stop immediately if a command fails.
6. Print a success message if everything passes.

Example:

```text
[1/4] Cleaning...
[2/4] Compiling...
[3/4] Running tests...
[4/4] Packaging...

CI verification successful
```

---

# 7. Deployment Script

The project includes:

```text
deploy.sh
```

The script accepts an environment argument.

### Development

```bash
./deploy.sh dev
```

### Staging

```bash
./deploy.sh staging
```

### Production

```bash
./deploy.sh prod
```

Production deployments require confirmation before continuing.

```text
WARNING: You are deploying to production.
Type YES to continue:
```

Only:

```text
YES
```

should allow the deployment to continue.

---

## Exit Codes

The deployment script should use meaningful exit codes.

| Situation                        | Exit Code |
| -------------------------------- | --------: |
| Successful deployment/check      |       `0` |
| Missing argument                 |       `1` |
| Invalid environment              |       `1` |
| Production confirmation rejected |       `1` |

Exit codes allow CI/CD systems to determine whether a script succeeded or failed.

---

# 8. Database

The system can use a relational database to persist course information.

The database contains a `courses` table with:

```text
course_code
name
lecturer
price
students
```

`course_code` acts as the primary key.

The Java application connects to the database using **JDBC**.

Database operations should use `PreparedStatement` rather than string concatenation.

Example:

```java
PreparedStatement statement =
    connection.prepareStatement(
        "SELECT * FROM courses WHERE students > ?"
    );
```

---

# 9. CRUD Operations

The database layer should support:

### Create

Add a new course.

### Read

Retrieve courses from the database.

### Update

Update the number of students.

### Delete

Remove a course.

All user-supplied values should be passed through parameterised SQL.

---

# 10. Getting Started

## Requirements

Make sure the following are installed:

```text
Java 17+
Maven
Git
GNU Make
Bash
```

Check Java:

```bash
java -version
```

Check Maven:

```bash
mvn -version
```

Check Make:

```bash
make --version
```

---

## Clone the Repository

```bash
git clone <repository-url>
cd course-system
```

---

# 11. Build the Project

Using Maven:

```bash
mvn compile
```

Or using Make:

```bash
make build
```

---

# 12. Run Tests

Using Maven:

```bash
mvn test
```

Or:

```bash
make test
```

---

# 13. Package the Application

```bash
mvn package
```

Or:

```bash
make package
```

The generated JAR will be placed in:

```text
target/
```

---

# 14. Run Full Verification

The recommended local verification command is:

```bash
make verify
```

Alternatively:

```bash
./ci-verify.sh
```

A successful verification means:

```text
Clean ✓
Build ✓
Tests ✓
Package ✓
```

---

# 15. Development Workflow

A typical development workflow is:

```text
Create branch
     ↓
Write code
     ↓
Write/update tests
     ↓
Run make verify
     ↓
Commit
     ↓
Push
     ↓
Create Pull Request
     ↓
CI Pipeline
     ↓
Build
     ↓
Test
     ↓
Package
     ↓
Review
     ↓
Merge
     ↓
Deploy
```

The purpose of CI is to automatically catch problems before changes are merged.

---

# 16. Useful Commands

| Command               | Purpose                        |
| --------------------- | ------------------------------ |
| `mvn clean`           | Remove previous build output   |
| `mvn compile`         | Compile the project            |
| `mvn test`            | Run JUnit tests                |
| `mvn package`         | Create the JAR                 |
| `make build`          | Compile using Make             |
| `make test`           | Run tests using Make           |
| `make package`        | Package using Make             |
| `make clean`          | Clean using Make               |
| `make verify`         | Clean, build, test and package |
| `./ci-verify.sh`      | Run CI verification locally    |
| `./deploy.sh dev`     | Check/deploy to development    |
| `./deploy.sh staging` | Check/deploy to staging        |
| `./deploy.sh prod`    | Check/deploy to production     |

---

# 17. Learning Objectives

By completing this project, you should be able to demonstrate knowledge of:

### Java / OOP

* Abstraction
* Inheritance
* Polymorphism
* Encapsulation
* Abstract classes
* Collections
* Maps
* Lists

### Testing

* Unit testing
* JUnit 5
* Test boundaries
* Assertions
* Mocking
* Test doubles

### CI/CD

* Continuous Integration
* Continuous Delivery
* Continuous Deployment
* Pipeline stages
* Pipeline triggers
* Build automation
* Build artifacts
* Deployment environments
* Pipeline failures

### Shell Scripting

* Shebangs
* Variables
* Command-line arguments
* Conditional statements
* Exit codes
* Error handling
* `set -e`
* User input

### Make

* Targets
* Prerequisites
* Recipes
* Dependencies
* `.PHONY`
* Build automation

### Databases

* Relational databases
* Primary keys
* Foreign keys
* Normalisation
* CRUD
* JDBC
* Prepared statements

---

## 🎯 Recommended Practice Order

If you are using this repository to prepare for an assessment, work through it in this order:

```text
1. Java OOP
      ↓
2. JUnit Testing
      ↓
3. Maven
      ↓
4. Makefiles
      ↓
5. Bash scripting
      ↓
6. CI/CD pipelines
      ↓
7. Git/GitHub workflow
      ↓
8. JDBC & SQL
```

The most important part of the build/pipeline section is being able to explain **why each stage exists**, not just memorising commands.
