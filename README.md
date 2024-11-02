# Spy Mission Game

A simple command-line game built in Java where players try to complete a mission by finding hidden spies on a grid. This project is inspired by the classic game Battleship but features a spy twist.

## How to Play

Your mission is to locate and eliminate hidden enemy spies on a 7x7 grid. Enter coordinates (like `a3` or `g5`) to make guesses. The game will tell you if you’ve "hit" a spy, "missed," or if a spy has been "eliminated" (all coordinates for that spy have been found). Try to find all spies with the fewest guesses possible!

You can forfeit the game at any time by pressing **CTRL + C**.

## Game Features

- **Random Spy Placement**: The spies are randomly placed on the grid at the start of each game.
- **Feedback on Guesses**: The game lets you know if your guess was a "hit," "miss," or if you’ve "eliminated" a spy.
- **Forfeit Option**: Press **CTRL + C** to forfeit the game and get a summary of your performance.

## Setup Instructions

### Prerequisites

- Java 8 or higher installed
- Git (optional, if you want to clone the repository directly)

### Clone the Repository

To download the game, you can either clone this repository using Git or download it as a ZIP file.

#### Cloning with Git

```bash
git clone https://github.com/your-username/SpyMission.git
cd SpyMission
```

### Open a terminal and Compile the Java files (one level above the SpyMission folder):

```bash
cd ..
javac SpyMission/SpyMission.java
```

### Run the game:

```bash
java SpyMission.SpyMission
```
