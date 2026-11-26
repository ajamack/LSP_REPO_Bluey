# Campus Event Registration System

CSCI 363 Large Scale Programming, Final Project
Author: Aja Wilkes

## Overview

A Java application that manages Howard University campus events, attendees,
and registrations. It enforces these business rules:

- No duplicate event or attendee IDs
- An attendee cannot register twice for the same event
- Event capacity is never exceeded
- CLOSED or CANCELED events do not accept registrations
- Canceling a registration frees a spot

## Project Structure

**Source package:** `org.howard.edu.lsp.project` (in the `src` folder)

| Class | Responsibility |
|---|---|
| `EventManager` | Stores all data and enforces every business rule |
| `Event` | One event, its details, and its status |
| `Attendee` | One attendee's details |
| `Registration` | Links one attendee to one event |
| `EventStatus` | OPEN, CLOSED, CANCELED |
| `RegistrationStatus` | ACTIVE, CANCELED |
| `EventSystemException` | Parent class for all custom exceptions |
| 9 child exceptions | One per type of rule violation |

**Test package:** `org.howard.edu.lsp.project` (in the `test` source folder)

| Test class | Covers |
|---|---|
| `EventManagerEventTest` | Create, retrieve, update, close, cancel events |
| `EventManagerAttendeeTest` | Create and retrieve attendees |
| `EventManagerRegistrationTest` | Register, cancel, and all registration rules |
| `EventManagerQueryTest` | Attendees by event, events by attendee |

## Requirements

- Java 21
- JUnit 5
- Eclipse IDE (or any IDE with JUnit 5 support)

## How to Run

There is no driver program. All functionality is demonstrated through JUnit 5 tests.

1. Import the project into Eclipse (**File > Import > Existing Projects into Workspace**).
2. Make sure JUnit 5 is on the build path (**right-click project > Build Path > Add Libraries > JUnit > JUnit 5**).
3. Make sure `test` is a source folder (**right-click test > Build Path > Use as Source Folder** if needed).
4. In Package Explorer, right-click the `test` source folder.
5. Select **Run As > JUnit Test**.
6. All 49 tests should pass with a green bar.

## Design Notes

- `EventManager` is the single entry point. Methods that change an event or
  registration (`close`, `cancel`, `update`) are package-private, so outside
  code cannot skip the business rules.
- All custom exceptions extend `EventSystemException`, so callers can catch
  one specific problem or any system problem.
- Input validation (blank names, missing @ in email, capacity below 1) throws
  Java's built-in `IllegalArgumentException`. Business rule violations throw
  custom exceptions.

