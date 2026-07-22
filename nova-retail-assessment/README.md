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

Nova Retail — Loyalty Rewards Redemption

Nova Retail is a mid-sized online retailer running a points-based loyalty programme. Customers earn points on purchases and can redeem them for rewards such as delivery vouchers and discount codes. Until now, rewards have been issued manually by a small operations team working from a spreadsheet, checking eligibility and receipts by hand.

The engineering organisation has historically delivered software the traditional way — requirements gathered up front, then handed off through design, build, and test in sequence — but leadership has decided to move away from this in favour of a more visual, iterative way of working.

You have been brought in as project manager for a new team about to start a project that will replace the manual process: building an automated Rewards Redemption feature for the Nova Retail website.

Customers should be able to see which rewards they qualify for based on their loyalty tier, purchase history, and region, and submit a redemption request — including a proof-of-purchase receipt — before a reward's claim deadline. The system should validate that request automatically instead of relying on someone in operations checking it by hand.

Your first responsibility, before any code is written, is to set the team's way of working.

> **Reference:** Middleton, P., and Joyce, D. (2012). Lean Software Management: BBC Worldwide Case Study. IEEE Transactions on Engineering Management, 59(1), 20-32.
> [Full text available here](https://pureadmin.qub.ac.uk/ws/portalfiles/portal/724967/Lean%20Software%20Management%20BBC%20Worldwide%20Case%20Study%20Feb%202011.pdf)

| Task | Marks | What earns marks |
|---|---|---|
| Q1.1 — Waterfall vs Agile | 5 | Accurate definition of both (2), clear reasoning for why the switch benefits this team specifically (3) |
| Q1.2 — Taskboard | 14 | Lane completeness and logic covering full backlog-to-delivery flow (8), lanes are genuinely distinct and sensibly named, not copied from the curriculum example (4), default Open/Closed columns correctly removed as instructed (2) |
| Q1.3 — Tickets and user stories | 6 | Correct user story format (2), story reflects the actual actor and goal from the scenario for each ticket (4) |

---

### Q1.1 — Waterfall vs Agile *(5 marks)*

Define and contrast the Waterfall model and Agile (time-boxed) delivery. Explain how choosing one over the other will benefit this team in the long run.

---

### Q1.2 — Set Up the Taskboard *(14 marks)*

Drawing on your experience with code clinics and Robot Worlds as part of your project management journey, set up a GitLab taskboard for this team. Design the lanes and columns yourself so that together they reflect the full journey of a piece of work from start to finish.

> GitLab's default Open and Closed columns do not count as lanes and must not appear when you submit your board. Every lane must be one you have deliberately created and named.

---

### Q1.3 — Create the Tickets *(6 marks)*

Create two tickets on your board:

- **Ticket 1:** Set up Reward Eligibility Checking
- **Ticket 2:** Redemption request is rejected when the receipt is invalid

Under each ticket, write a user story describing what that piece of functionality is looking to achieve, from the user's perspective.

---

# Section 2 — Brownfields Development

| Task | Marks | What earns marks |
|---|---|---|
| Q2.1 — Brownfield vs Greenfield | 7 | Accurate contrast (3), Robot Worlds example genuinely illustrates the concept (4) |
| Q2.2 — Refactoring | 7 | Correct definition (2), correct before/during/after conditions (3), clear refactor-vs-rewrite distinction (2) |
| Q2.3 — Refactoring for Maintainability | 26 | Split across two methods, 13 each: correct identification of the specific code smell (3), accurate explanation of why it is a problem (4), refactor actually resolves it (3), tests pass unmodified — binary (2), written justification is specific to their change (1) |

---

### Q2.1 — Brownfield vs Greenfield *(7 marks)*

Define and contrast brownfield and greenfield software development. What makes a project a brownfield project? Use a specific example from your own experience on Robot Worlds to illustrate your answer.

---

### Q2.2 — Refactoring *(7 marks)*

Define refactoring. Explain the process a developer should follow when refactoring code — what must stay true before, during, and after the change — and explain why refactoring is treated differently from a rewrite.

---

### Q2.3 — Refactoring for Maintainability *(26 marks)*

Somewhere in the provided codebase there are two methods, each with a distinct maintainability problem in how its conditional logic is structured.

For each of the two methods, complete all three steps below.

#### Q2.3.1 — Locate and describe the problem

Identify the method. Explain in your own words what is wrong with its structure and why it matters for readability, testability, or safe modification. *(Write this in your `.txt` file.)*

#### Q2.3.2 — Refactor

Refactor the method to resolve the problem you identified.

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
| Q3.1 — Types of Testing | 12 |
| Q3.2 — Test Reports | 8 |

---

### Q3.1 — Types of Testing *(12 marks)*

Define unit testing, integration testing, and acceptance testing. For each one, explain what it is checking — whether that is a single method or class, how components work together, or the system's behaviour as a whole — and roughly when in development it happens.

---

### Q3.2 — Test Reports *(8 marks)*

When you run `mvn test`, Maven generates a test report via Surefire summarising the results of your test suite. What key pieces of information would you expect to find in a test report? If a test report shows all tests passing but the number of tests run is very low relative to the size of the codebase, what might that suggest about the reliability of "all tests passed" as a sign the code works? And why is it useful to look at a test report over time — across multiple runs and iterations — rather than just checking the latest one?

---

# Section 4 — Systems Design

| Task | Marks |
|---|---|
| Q4.1 — System Design | 6 |
| Q4.2 — UML Diagrams | 9 |

---

### Q4.1 — System Design *(6 marks)*

Define what system design — also referred to as software design — means, and explain what it aims to achieve.

---

### Q4.2 — UML Diagrams *(9 marks)*

Define what a UML diagram is. Give two examples of different kinds of UML diagrams and explain what each one aims to achieve.

---
### End of Assessment
---

## Project structure

```
src/main/java/com/wtc/novaretail/
├── Main.java                    # Demo entry point
├── model/                       # Domain classes (Customer, Region, Reward, RedemptionRequest, ReceiptFile)
└── service/                     # RewardsEligibilityService and supporting types
src/test/java/com/wtc/novaretail/
└── service/                     # JUnit 5 tests for RewardsEligibilityService
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
java -cp target/classes com.wtc.novaretail.Main
```
