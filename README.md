<br />
<div align="center">
  <h1 align="center">Tugas Kecil 3 IF2211 Strategi Algoritma</h1>

  <p align="center">
    <h3>Word Ladder Solver using UCS, GBFS, and A* Algorithm</h3>

</div>

<!-- CONTRIBUTOR -->
<div align="center" id="contributor">
  <strong>
    <h3>Shazya Audrea Taufik</h3>
    <h3>13522063</h3>
  </strong>
</div>

## Table of Contents
  - [Table of Contents](#table-of-contents)
  - [Implementation](#implementation)
  - [Program Features](#program-features)
  - [Program Structure](#program-structure)
  - [Requirements](#requirements)
  - [Module Used](#module-used)
  - [Running The Program](#how-to-run)
  - [Program Flow](#program-flow)

<!-- GENERAL INFORMATION -->
## Implementation
This program implements the Uniform Cost Search (UCS), Greedy Best First Search (GBFS), and A* Search Algorithm to solve the Word Ladder Game, where the objective is to find the shortest path between a start word and an end word by changing one letter at a time. 

## Program Features
The features of our program:
* Word Ladder Solver using UCS
* Word Ladder Solver using GBFS
* Word Ladder Solver using A*

## Program Structure

```
├── bin
│   ├── Algorithm.class
│   ├── Main.class
│   ├── Node.class
│   ├── Result.class
│   ├── WordLadderGUI.class
│   ├── WordLadderGUI$1.class
│   └── WordsDatabase.class
├── doc
│   └── Tucil3_13522063.pdf
├── src
│   ├── resources
│   │   └── dictionary.txt
│   ├── Algorithm.java
│   ├── Main.java
│   ├── Node.java
│   ├── Result.java
│   ├── WordLadderGUI.java
│   └── WordsDatabase.java
├── test
│   └── test.txt
└── README.md
```

## Requirements
1. Java

## Module Used
1. Java Swing
2. java.util
3. java.io


## How to Run
1. Clone repository 
    ```
    git clone https://github.com/zyaaa-aaa/Tucil3_13522063.git
    ```
2. Open repository folder in terminal.
3. Change directory into *src* with `cd src`
4.  Type in terminal
    ```
    javac Main.java
    ```
5.  Type in terminal
    ```
    java Main
    ```


## Program Flow
1. The program will display the main menu.
2. The program will accept the start word and end word input. If the input is not in the dictionary or if the lengths are not the same, or if the word contains special chars and number, then there will error messages.
3. User clicks start, then the result (path, execution time, nodes visited, and path length) will appear.

<br>
<h3 align="center"> THANK YOU! </h3>