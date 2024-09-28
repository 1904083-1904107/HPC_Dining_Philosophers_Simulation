# Extended Dining Philosophers Problem Simulation

This document describes the simulation of the extended Dining Philosophers Problem with multiple tables, where philosophers can move to a sixth table upon deadlock.

## Problem Description

The problem simulates the behavior of philosophers seated at multiple tables, each attempting to eat using shared forks. If a table enters a deadlocked state, a philosopher moves to an empty sixth table. The goal is to simulate the movement of philosophers and determine when the sixth table deadlocks, recording the philosopher who last moved to it.

## Steps for Implementation

### 1. Set Up the Environment

- **Number of Tables**: 5 tables, each with 5 philosophers.
- **Sixth Table**: An empty table that can accommodate philosophers from deadlocked tables.
- **Forks**: Each table has 5 shared forks.

### 2. Define Philosopher Behavior

- **Thinking**: Philosophers will think for a random duration (0-10 seconds).
- **Getting Hungry**: Attempt to pick up the left fork, wait 4 seconds, and then attempt to pick up the right fork.
- **Eating**: If successful, they will eat for a random time (0-5 seconds).
- **Dropping Forks**: After eating, they will put down both forks and repeat the process.

### 3. Detect Deadlock

Monitor the state of each table to check if all philosophers are waiting for forks. If so, a deadlock is detected.

### 4. Philosopher Movement

When deadlock occurs:
- A philosopher drops any fork they are holding and moves to the sixth table.
- The simulation should keep track of which philosopher moved last.

### 5. End Condition

The simulation concludes when the sixth table enters a deadlocked state, and the program outputs the philosopher who last moved to it before the deadlock.

