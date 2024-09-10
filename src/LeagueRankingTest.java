import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class LeagueRankingTest {

    // Test to verify match input processing
    @Test
    public void testMatchInputProcessing() {
        // Prepare input and team map
        Map<String, LeagueRanking.Team> teamMap = new HashMap<>();
        String match1 = "Lions 3, Snakes 3";
        String match2 = "Tarantulas 1, FC Awesome 0";

        // Process the matches
        LeagueRanking.processGameResult(match1, teamMap);
        LeagueRanking.processGameResult(match2, teamMap);

        // Check team points
        assertEquals(1, teamMap.get("Lions").points);
        assertEquals(1, teamMap.get("Snakes").points);
        assertEquals(3, teamMap.get("Tarantulas").points);
        assertEquals(0, teamMap.get("FC Awesome").points);
    }

    // Test the processGameResult logic
    @Test
    public void testProcessGameResult() {
        Map<String, LeagueRanking.Team> teamMap = new HashMap<>();
        LeagueRanking.processGameResult("Lions 3, Snakes 3", teamMap);

        assertEquals(1, teamMap.get("Lions").points);
        assertEquals(1, teamMap.get("Snakes").points);

        LeagueRanking.processGameResult("Tarantulas 1, FC Awesome 0", teamMap);
        assertEquals(3, teamMap.get("Tarantulas").points);
        assertEquals(0, teamMap.get("FC Awesome").points);
    }

    // Test ranking order after multiple game results
    @Test
    public void testRankingOrder() {
        Map<String, LeagueRanking.Team> teamMap = new HashMap<>();
        LeagueRanking.processGameResult("Lions 3, Snakes 3", teamMap);
        LeagueRanking.processGameResult("Tarantulas 1, FC Awesome 0", teamMap);
        LeagueRanking.processGameResult("Lions 1, FC Awesome 1", teamMap);
        LeagueRanking.processGameResult("Tarantulas 3, Snakes 1", teamMap);
        LeagueRanking.processGameResult("Lions 4, Grouches 0", teamMap);

        List<LeagueRanking.Team> ranking = new ArrayList<>(teamMap.values());
        ranking.sort((t1, t2) -> {
            if (t2.points != t1.points) {
                return t2.points - t1.points;
            }
            return t1.name.compareTo(t2.name);
        });

        assertEquals("Tarantulas", ranking.get(0).name);
        assertEquals(6, ranking.get(0).points);

        assertEquals("Lions", ranking.get(1).name);
        assertEquals(5, ranking.get(1).points);

        assertEquals("FC Awesome", ranking.get(2).name);
        assertEquals(1, ranking.get(2).points);

        assertEquals("Snakes", ranking.get(3).name);
        assertEquals(1, ranking.get(3).points);

        assertEquals("Grouches", ranking.get(4).name);
        assertEquals(0, ranking.get(4).points);
    }

    // Test the final ranking output
    @Test
    public void testMatchExpectedOutput() {
        // Prepare input and team map
        Map<String, LeagueRanking.Team> teamMap = new HashMap<>();
        String[] matches = {
                "Lions 3, Snakes 3",
                "Tarantulas 1, FC Awesome 0",
                "Lions 1, FC Awesome 1",
                "Tarantulas 3, Snakes 1",
                "Lions 4, Grouches 0"
        };

        // Process each match
        for (String match : matches) {
            LeagueRanking.processGameResult(match, teamMap);
        }

        // Create the ranking list and sort it
        List<LeagueRanking.Team> ranking = new ArrayList<>(teamMap.values());
        ranking.sort((t1, t2) -> {
            if (t2.points != t1.points) {
                return t2.points - t1.points;
            }
            return t1.name.compareTo(t2.name);
        });

        // Prepare the expected output
        List<String> expectedOutput = Arrays.asList(
                "1. Tarantulas, 6 pts",
                "2. Lions, 5 pts",
                "3. FC Awesome, 1 pt",
                "3. Snakes, 1 pt",
                "5. Grouches, 0 pts"
        );

        // Capture the actual output in a list
        List<String> actualOutput = new ArrayList<>();
        int rank = 1;
        int previousPoints = -1;
        int previousRank = 1;

        for (int i = 0; i < ranking.size(); i++) {
            LeagueRanking.Team team = ranking.get(i);
            if (team.points != previousPoints) {
                rank = i + 1;
                previousRank = rank;
            } else {
                rank = previousRank;
            }

            String pointsLabel = team.points == 1 ? "pt" : "pts";
            actualOutput.add(String.format("%d. %s, %d %s", rank, team.name, team.points, pointsLabel));
            previousPoints = team.points;
        }

        // Assert that the actual output matches the expected output
        assertEquals(expectedOutput, actualOutput);
    }

}
