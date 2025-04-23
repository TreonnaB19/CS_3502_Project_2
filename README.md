# CPU Scheduling Simulator
CS 3502 - Operating Systems
**Author:** Treonna Gardner  
**Date:** April 2025

---

## Description

This is a Java-based console application that simulates and evaluates CPU scheduling algorithms. The simulator is designed for academic purposes to study and compare advanced CPU scheduling techniques.

Implemented Algorithms:
- 🔁 **SRTF (Shortest Remaining Time First)** – A preemptive algorithm that selects the process with the shortest remaining time.
- 🔄 **MLFQ (Multi-Level Feedback Queue)** – A dynamic, multi-level scheduling strategy that adjusts process priority based on execution behavior.

---

## Features

- Simulates CPU scheduling using different algorithms
- Tracks and reports the following metrics:
  - Average Waiting Time (AWT)
  - Average Turnaround Time (ATT)
  - Response Time (RT)
  - CPU Utilization (%)
  - Throughput
- Displays results in a formatted table
- Modular design for adding new scheduling algorithms
- Designed for console-based interaction

---

## Technologies Used

- Java 17+
- Command Line / Terminal
- Object-Oriented Design Principles

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK 17 or newer)
- Git (to clone the repository)
- Any Java IDE (VS Code, IntelliJ, Eclipse) or terminal

### 🔧 How to Run

1. **Clone the repository:**

```bash
git clone https://github.com/your-username/cpu-scheduling-simulator.git
cd cpu-scheduling-simulator
