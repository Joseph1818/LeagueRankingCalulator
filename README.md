### League Ranking Calculator

### Overview

This is a command-line application that calculates and displays league rankings. You input the results of matches, and the program will calculate points for each team based on the results. A win gets 3 points, a draw gets 1 point, and a loss gets 0 points. The rankings are sorted by points, with teams having the same points sorted alphabetically.

### Features

- **Input Handling:** Accepts match results in the format `Team1 Name Score, Team2 Name Score` from the terminal.
- **Ranking Calculation:** Updates points based on match results.
- **Display Rankings:** Shows the league standings, sorted by points and then alphabetically if needed.
- **Command-Line Interaction:** Allows users to view rankings .

3 Prerequisites

- Java Development Kit (JDK) 8 or higher.

### Setup

1. **Clone the Repository**

    ```bash
    git clone https://github.com/Joseph1818/LeagueRankingCalculator.git
    ```

2. **Navigate to the Project Directory**

    ```bash
    cd LeagueRankingCalculator
    ```

3. **Compile the Code**

   In your terminal (or command prompt), navigate to the folder where your LeagueRanking.java file is located and run the following command to compile it:

    ```bash
    javac LeagueRanking.java
    ```

4. **Run the Application**

   After compiling, you need to run the program. To run it, use the following command:

    ```bash
    java LeagueRanking
    ```

5. **Provide Input**

   When you run the program, you need to provide the match results. You can input the results directly into the terminal.

   **Manual Input (stdin):**

   After running the program, enter the game results directly into the terminal. For example:

    ```text
    Lions 3, Snakes 3
    Tarantulas 1, FC Awesome 0
    Lions 1, FC Awesome 1
    Tarantulas 3, Snakes 1
    Lions 4, Grouches 0
    ```

   Press `Ctrl+D` on macOS/Linux or `Ctrl+Z` (then press Enter) on Windows to signal the end of input.

6. **View the Results**

   The program will process the input and display the rankings in the terminal. For the sample input, the output will be:

    ```text
    1. Tarantulas, 6 pts
    2. Lions, 5 pts
    3. FC Awesome, 1 pt
    3. Snakes, 1 pt
    5. Grouches, 0 pts
    ```

### Sample Input/Output

**Sample Input**

```text
Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0
