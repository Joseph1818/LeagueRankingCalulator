import java.util.*;

public class LeagueRanking {

    // A class to represent a team
    static class Team {
        String name;
        int points;

        public Team(String name) {
            this.name = name;
            this.points = 0;
        }

        public void addPoints(int points) {
            this.points += points;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // A map to store teams and their points
        Map<String, Team> teamMap = new HashMap<>();

        // Reading input from stdin
        while (scanner.hasNextLine()) {
            String gameResult = scanner.nextLine();
            processGameResult(gameResult, teamMap);
        }

        // Sorting the teams by points and name
        List<Team> ranking = new ArrayList<>(teamMap.values());
        ranking.sort((t1, t2) -> {
            if (t2.points != t1.points) {
                return t2.points - t1.points; // Sort by points (desc)
            }
            return t1.name.compareTo(t2.name); // Sort alphabetically if tied
        });

        // Printing the ranking
        printRanking(ranking);
    }

    // Method to process a single game result
    public static void processGameResult(String gameResult, Map<String, Team> teamMap) {
        String[] teamsAndScores = gameResult.split(", ");
        String[] team1Details = teamsAndScores[0].split(" ");
        String[] team2Details = teamsAndScores[1].split(" ");

        String team1Name = String.join(" ", Arrays.copyOf(team1Details, team1Details.length - 1));
        int team1Score = Integer.parseInt(team1Details[team1Details.length - 1]);

        String team2Name = String.join(" ", Arrays.copyOf(team2Details, team2Details.length - 1));
        int team2Score = Integer.parseInt(team2Details[team2Details.length - 1]);

        teamMap.putIfAbsent(team1Name, new Team(team1Name));
        teamMap.putIfAbsent(team2Name, new Team(team2Name));

        if (team1Score > team2Score) {
            teamMap.get(team1Name).addPoints(3); // Team 1 wins
        } else if (team1Score < team2Score) {
            teamMap.get(team2Name).addPoints(3); // Team 2 wins
        } else {
            teamMap.get(team1Name).addPoints(1); // Draw
            teamMap.get(team2Name).addPoints(1); // Draw
        }
    }

    // Method to print the ranking in the expected format
    public static void printRanking(List<Team> ranking) {
        int rank = 1;
        int previousPoints = -1;
        for (int i = 0; i < ranking.size(); i++) {
            Team team = ranking.get(i);
            if (team.points != previousPoints) {
                rank = i + 1;
            }
            String pointsLabel = team.points == 1 ? "pt" : "pts";
            System.out.printf("%d. %s, %d %s\n", rank, team.name, team.points, pointsLabel);
            previousPoints = team.points;
        }
    }
}
