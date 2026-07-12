WeThinkCode_ Assessment Mock Test

Module: Brownfields Development, Testing, Agile Methodology, OOP, Systems Design
Duration: 3 Hours
Total Marks: 100

Instructions
Read all questions carefully before starting.
You may use Java, JUnit 5, Maven, and standard Java libraries.
Focus on clean code, maintainability, and testing practices.
Where code is required, explain your reasoning.
Assume all code compiles unless stated otherwise.
Follow Object-Oriented Design principles.
Section A: Testing (30 Marks)
Question 1: Unit Test Creation (10 Marks)

Consider the following class:

public class DiscountCalculator {

    public double calculateDiscount(double amount) {
        if (amount >= 1000) {
            return amount * 0.20;
        }

        if (amount >= 500) {
            return amount * 0.10;
        }

        return 0;
    }
}
Task

Write JUnit 5 tests that verify:

Discount for purchases above R1000.
Discount for purchases between R500 and R999.
No discount for purchases below R500.
Boundary condition at exactly R500.
Boundary condition at exactly R1000.
Question 2: Identifying Missing Tests (5 Marks)

Examine the following code:

public class LoginValidator {

    public boolean isValid(String username, String password) {
        return username.length() >= 5 &&
               password.length() >= 8;
    }
}
Task

List at least 5 test cases that should exist for this method.

For each test case explain:

Input
Expected Result
Why it is important
Question 3: Mocking Dependencies (5 Marks)

Consider:

public class NotificationService {

    private EmailGateway gateway;

    public NotificationService(EmailGateway gateway) {
        this.gateway = gateway;
    }

    public void sendWelcomeEmail(String email) {
        gateway.send(email, "Welcome!");
    }
}
Task

Write a JUnit test using Mockito that verifies:

gateway.send() is called exactly once.
Correct arguments are passed.
Question 4: Test Analysis (10 Marks)

You are given:

public class BankAccount {

    private double balance;

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
Task
Identify at least 4 flaws in the implementation.
Write unit tests exposing those flaws.
Suggest improvements.
Section B: Brownfields Development & Refactoring (25 Marks)
Question 5: Refactor Existing Code (10 Marks)

You inherit the following code:

public class OrderProcessor {

    public double processOrder(String customerType,
                               double total) {

        if(customerType.equals("VIP")) {
            return total * 0.8;
        }

        if(customerType.equals("REGULAR")) {
            return total * 0.95;
        }

        if(customerType.equals("NEW")) {
            return total;
        }

        return total;
    }
}
Tasks
Identify at least 3 code smells.
Refactor using OOP principles.
Explain why your solution is more maintainable.
Question 6: Legacy Code Enhancement (10 Marks)

Existing system:

public class Vehicle {

    public double calculateTax() {
        return 1000;
    }
}

A new requirement arrives:

Cars pay R1000 tax.
Trucks pay R3000 tax.
Motorcycles pay R500 tax.
Tasks
Refactor using inheritance and polymorphism.
Draw a UML Class Diagram.
Explain why your design supports future extensions.
Question 7: Brownfields Scenario (5 Marks)

You join a project containing:

25,000 lines of code
No tests
No documentation
Tight delivery deadlines
Questions
What risks do you identify?
What steps would you take before making changes?
How would you introduce automated testing?
Section C: Object-Oriented Programming (20 Marks)
Question 8: Design Question (10 Marks)

Design a Library Management System.

Requirements:

Books can be borrowed.
Members can borrow multiple books.
Librarians manage inventory.
Books can be available or unavailable.
Tasks
Identify classes.
Define relationships.
Explain where inheritance should and should not be used.
Draw a UML Class Diagram.
Question 9: OOP Principles (10 Marks)

Given:

public class User {

    public String name;
    public String email;
}
Tasks
Identify OOP violations.
Refactor the class.
Explain how encapsulation improves the design.
Discuss how validation could be added.
Section D: Agile Methodology (10 Marks)
Question 10: Agile Concepts (5 Marks)

Explain:

Sprint
Product Backlog
Sprint Backlog
User Story
Definition of Done

Provide practical examples.

Question 11: User Stories (5 Marks)

Write user stories for:

Feature:

Online Vehicle Rental System

Requirements:

Search vehicles
Book vehicles
Cancel bookings

Use the format:

As a ______
I want ______
So that ______

Include acceptance criteria.

Section E: Systems Design (15 Marks)
Question 12: High-Level Design (10 Marks)

Design a Coding Clinic Booking System.

Requirements:

Volunteers create availability slots.
Students view available slots.
Students book slots.
Google Calendar integration.
Booking cancellation.
Tasks
Identify major components.
Draw a high-level architecture diagram.
Explain data flow.
Explain where testing should occur.
Question 13: Design Trade-Offs (5 Marks)

You need to choose between:

Option A

Everything stored in local JSON files.

Option B

Relational database (PostgreSQL).

Discuss:

Advantages
Disadvantages
Scalability
Maintainability

Recommend one option and justify your choice.

Long Question (10 Marks)

You inherit a WeThinkCode_ project with:

Poor code quality
No tests
Multiple developers actively working on it
New features due in 2 weeks
Write a short essay discussion on :
How Agile principles would guide your work.
How you would approach understanding the existing codebase.
The role of automated testing.
Refactoring strategies.
OOP principles you would apply.
Risks of making changes without tests.
How Systems Design thinking helps when modifying an existing system.

