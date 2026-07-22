# Summative Assessment

## Learning Outcomes

| Learning Outcome | Section |
|---|---|
| Project Management / Agile | Section 1 |
| Brownfields Development | Section 2 |
| Testing | Section 3 |
| Systems Design | Section 4 |

---

## Duration

**Total time: 2 hours 30 minutes**

| Section | Recommended Time |
|---|---|
| Section 1 — Project Management | 40 minutes |
| Section 2 — Brownfields Development | 65 minutes |
| Section 3 — Testing | 30 minutes |
| Section 4 — Systems Design | 15 minutes |

---

## Scoring

| Section | Marks |
|---|---|
| Section 1 — Project Management | 25 |
| Section 2 — Brownfields Development | 40 |
| Section 3 — Testing | 20 |
| Section 4 — Systems Design | 15 |
| **Total** | **100** |

---

# Section 1 — Project Management

## Scenario

Northbridge Bank — Personal Loan Pre-Approval

Northbridge Bank runs a personal loan scheme. Until now, a small team in the lending office has manually checked each applicant's credit score, employment status, and existing accounts against a spreadsheet, then chased them by phone or email for supporting documents like payslips or bank statements.

The bank wants to automate this: applicants should be told immediately whether they are pre-approved for a given loan product, and should be able to upload their supporting documents online before a deadline, with the system checking those documents automatically instead of a person doing it by hand.

You have been brought in as project manager for the team that will build this. Before any code is written, your first responsibility is to set the team's way of working.

> **Reference:** Middleton, P., and Joyce, D. (2012). Lean Software Management: BBC Worldwide Case Study. IEEE Transactions on Engineering Management, 59(1), 20-32.
> [Full text available here](https://pureadmin.qub.ac.uk/ws/portalfiles/portal/724967/Lean%20Software%20Management%20BBC%20Worldwide%20Case%20Study%20Feb%202011.pdf)

| Task | Marks | What earns marks |
|---|---|---|
| Q1.1 — Waterfall or Agile? | 6 | Correct position taken and defended (2), accurate description of both models used to support the argument (2), reasoning is specific to the risks/unknowns of this project rather than generic (2) |
| Q1.2 — Taskboard with WIP limits | 14 | Lane completeness and logic covering full backlog-to-delivery flow (7), lanes are genuinely distinct and sensibly named (3), a WIP limit is set on at least one lane with a sound justification (2), default Open/Closed columns correctly removed as instructed (2) |
| Q1.3 — Ticket and acceptance criteria | 5 | Correct user story format (2), at least two acceptance criteria that would let a tester know when the ticket is genuinely done (3) |

---

### Q1.1 — Waterfall or Agile? *(6 marks)*

A colleague on the team argues that because the lending rules are already well understood and documented by the bank's compliance team, Waterfall would be the safer choice for this project — everything can be specified up front, so there is no need for iteration.

Do you agree or disagree? Take a position and justify it, drawing on accurate definitions of both Waterfall and Agile (time-boxed) delivery, and referring to specifics of the Northbridge scenario above.

---

### Q1.2 — Set Up the Taskboard *(14 marks)*

Drawing on your experience with code clinics and Robot Worlds as part of your project management journey, set up a GitLab taskboard for this team. Design the lanes and columns yourself so that together they reflect the full journey of a piece of work from start to finish.

In addition, set a Work-In-Progress (WIP) limit on at least one lane, and briefly explain in the ticket description or board why that limit is appropriate for this team.

> GitLab's default Open and Closed columns do not count as lanes and must not appear when you submit your board. Every lane must be one you have deliberately created and named.

---

### Q1.3 — Create a Ticket with Acceptance Criteria *(5 marks)*

Create one ticket on your board:

- **Ticket:** Applicant is told immediately whether they are pre-approved for a loan product

Under the ticket, write a user story describing what this functionality is looking to achieve, from the applicant's perspective, followed by at least two acceptance criteria a tester could use to confirm the ticket is complete.

---

# Section 2 — Brownfields Development

| Task | Marks | What earns marks |
|---|---|---|
| Q2.1 — Spotting a Brownfield Project | 8 | Correct identification with reasoning (3), Robot Worlds example genuinely illustrates the concept (3), at least one specific risk named that would not exist on a greenfield project (2) |
| Q2.2 — Refactoring Techniques | 6 | Two named refactoring techniques correctly described (4), correct explanation of what must remain unchanged before/during/after a refactor (2) |
| Q2.3 — Refactoring for Maintainability | 26 | Split across two methods, 13 each: correct identification of the specific code smell (3), accurate explanation of why it is a problem (4), refactor actually resolves it (3), tests pass unmodified — binary (2), written justification is specific to their change (1) |

---

### Q2.1 — Spotting a Brownfield Project *(8 marks)*

You join the Northbridge team a year after this system first went live. The lending rules have changed twice since launch, there are real applicants' data in the production database, and the original developers who wrote the eligibility logic have since left the bank.

Is this a brownfield or a greenfield project? Justify your answer, and use a specific example from your own experience on Robot Worlds to illustrate the concept. Then name one specific risk this situation creates that would simply not exist if the team were starting from scratch.

---

### Q2.2 — Refactoring Techniques *(6 marks)*

Name and briefly describe two distinct refactoring techniques a developer could use to simplify a method with deeply nested conditional logic. For each technique, explain in one or two sentences how it changes the structure of the code.

Then explain what must remain true about the code's external behaviour before, during, and after a refactor — and why this is what distinguishes refactoring from a rewrite.

---

### Q2.3 — Refactoring for Maintainability *(26 marks)*

Somewhere in the provided codebase there are two methods, each with a distinct maintainability problem in how its conditional logic is structured.

For each of the two methods, complete all three steps below.

#### Q2.3.1 — Locate and describe the problem

Identify the method. Explain in your own words what is wrong with its structure and why it matters for readability, testability, or safe modification. *(Write this in your `.txt` file.)*

#### Q2.3.2 — Refactor

Refactor the method to resolve the problem you identified, applying at least one of the techniques you named in Q2.2 where it genuinely fits.

#### Q2.3.3 — Prove behaviour preservation

Run the existing test suite before and after your refactor:

```bash
mvn test
```

Your refactored code must pass all existing tests unchanged. You may not modify the tests to make them pass.

Alongside your refactored code, include a short written explanation of 3 to 5 sentences per method justifying why your new structure is more maintainable than the original.

---

# Section 3 — Testing

| Task | Marks |
|---|---|
| Q3.1 — Classify the Test | 12 |
| Q3.2 — Reading a Test Report | 8 |

---

### Q3.1 — Classify the Test *(12 marks)*

Below are three descriptions of tests written for the Northbridge system. For each one, state whether it is a unit test, an integration test, or an acceptance test, and justify your answer by explaining what the test is actually checking and roughly when in development it would typically be written.

**Test A:** A test that creates an `Applicant` and a `LoanProduct` directly in code, calls `isPreApproved(...)` on the service, and asserts the boolean it returns — without touching a database, file system, or web request.

**Test B:** A test that starts the application, submits a real HTTP request to upload a document through the API, and then queries the database directly to confirm a document record was actually written and linked to the correct applicant.

**Test C:** A test, written from a loan officer's point of view before any code existed, that describes: "Given an applicant with a credit score below the minimum, when they apply for a loan product, then they should see a message telling them why they were not pre-approved."

---

### Q3.2 — Reading a Test Report *(8 marks)*

When you run `mvn test`, Maven generates a test report via Surefire summarising the results of your test suite.

A colleague shows you a Surefire report for the Northbridge codebase: 4 tests run, 4 passed, 0 failed, 0 skipped — for a codebase with over 30 methods across several services.

What key pieces of information would you expect to find in a test report generally? Given the numbers above, what might this specific report suggest — or fail to guarantee — about whether the code actually works? Why is it more useful to look at a test report's trend over several runs and iterations, rather than judging code quality from a single "all tests passed" result?

---

# Section 4 — Systems Design

| Task | Marks |
|---|---|
| Q4.1 — System Design in Practice | 6 |
| Q4.2 — Choose the Right UML Diagram | 9 |

---

### Q4.1 — System Design in Practice *(6 marks)*

Define what system design — also referred to as software design — means, and explain what it aims to achieve. Then briefly explain, using the code smells you identified in Q2.3, how weak systems design decisions made early in a project's life can be a direct cause of the maintainability problems you found later on.

---

### Q4.2 — Choose the Right UML Diagram *(9 marks)*

Define what a UML diagram is. The Northbridge team needs to communicate two different things to stakeholders: (1) the static structure of the `Applicant`, `LoanProduct`, and `DocumentSubmission` classes and how they relate to one another, and (2) the sequence of steps and interactions that happen when an applicant submits their documents. For each of these two needs, name the kind of UML diagram best suited to it and explain what that diagram type aims to achieve.

---
### End of Assessment
---

## Project structure

```
src/main/java/com/wtc/northbridge/
├── Main.java                    # Demo entry point
├── model/                       # Domain classes (Applicant, Branch, LoanProduct, DocumentSubmission, SupportingDocument)
└── service/                     # LoanEligibilityService and supporting types
src/test/java/com/wtc/northbridge/
└── service/                     # JUnit 5 tests for LoanEligibilityService
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
java -cp target/classes com.wtc.northbridge.Main
```
