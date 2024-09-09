
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String welcomeMessage = "Welcome to League Ranking Calculator Program!";
        System.out.println(welcomeMessage);

        String underline = "_".repeat(welcomeMessage.length());
        System.out.println(underline);

        Scanner scanner = new Scanner(System.in);

        // Using Map to initialize the point for each team!
        Map<String, Integer> teamPoints = new HashMap<>();

        // Display instructions for inputs
        System.out.println("Please enter the match results in the following format:");
        System.out.println("Team1 Name Score, Team2 Name Score");
        System.out.println("Type 'results' at any time to view the rankings.");
        System.out.println("Type 'quit' to exit the program.");

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            // Check if the user requests results
            if (line.equalsIgnoreCase("results")) {
                displayRankings(teamPoints);
                continue;  // Continue to allow further match inputs
            }

            // Check if the user wants to quit
            if (line.equalsIgnoreCase("quit")) {
                break;
            }

            // Device the line into two parts for each Team and their Score
            String[] matchResult = line.split(",");

            // Split the parts to get team names and scores
            String[] team1 = matchResult[0].split(" ");
            String[] team2 = matchResult[1].split(" ");


            // Get team names and scores
            String teamName1 = team1[0];
            String teamName2 = team2[1];
            int score1 = Integer.parseInt(team1[team1.length - 1]);
            int score2 = Integer.parseInt(team2[team2.length - 1]);

            //Update points in the Map.
            if (score1 > score2) {
                teamPoints.put(teamName1, teamPoints.getOrDefault(teamName1, 0) + 3);

            } else if (score2 > score1) {
                teamPoints.put(teamName2, teamPoints.getOrDefault(teamName2, 0) + 3);
            } else {
                teamPoints.put(teamName1, teamPoints.getOrDefault(teamName1, 0) + 1);
                teamPoints.put(teamName2, teamPoints.getOrDefault(teamName2, 0) + 1);
            }

        }
        scanner.close();
        // Display final rankings when quitting
        displayRankings(teamPoints);
        System.out.println("Thank you for visiting!");
    }

    // Method to display rankings
    public static void displayRankings(Map<String, Integer> teamPoints) {
        if (teamPoints.isEmpty()) {
            System.out.println("No match results to display.");
            return;
        }

        List<Map.Entry<String, Integer>> teamList = new ArrayList<>(teamPoints.entrySet());

        // Sort the list by points in descending order; in case of a tie, sort alphabetically
        teamList.sort((team1, team2) -> {
            int pointComparison = team2.getValue().compareTo(team1.getValue());
            if (pointComparison == 0) {
                return team1.getKey().compareTo(team2.getKey());
            }
            return pointComparison;
        });

        // Display the sorted list with ranking
        System.out.println("League Rankings:");
        int rank = 1;
        for (int i = 0; i < teamList.size(); i++) {
            Map.Entry<String, Integer> team = teamList.get(i);
            if (i > 0 && team.getValue().equals(teamList.get(i - 1).getValue())) {
                // If current team has the same points as the previous one, they share the rank
                System.out.println((rank - 1) + ". " + team.getKey() + ", " + team.getValue() + " pts");
            } else {
                System.out.println(rank + ". " + team.getKey() + ", " + team.getValue() + " pts");
            }
            rank++;
        }
        System.out.println("Thank you for visiting !");
    }
}
