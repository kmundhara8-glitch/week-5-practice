import java.util.Arrays;

public class PlacementDriveRanking {

    static class Candidate implements Comparable<Candidate> {

        private String name;
        private double cgpa;
        private int codingScore;

        public Candidate(
                String name,
                double cgpa,
                int codingScore) {

            this.name = name;
            this.cgpa = cgpa;
            this.codingScore = codingScore;
        }

        // CGPA-only eligibility
        static boolean isEligible(double cgpa) {

            return cgpa >= 7.5;
        }

        // Combined CGPA + coding score eligibility
        static boolean isEligible(
                double cgpa,
                int codingScore) {

            return cgpa >= 6.5 && codingScore >= 60;
        }

        // Composite score
        double getCompositeScore() {

            return cgpa * 10 + codingScore * 0.5;
        }

        // Descending order
        @Override
        public int compareTo(Candidate other) {

            return Double.compare(
                    other.getCompositeScore(),
                    this.getCompositeScore()
            );
        }

        public String getName() {
            return name;
        }
    }

    static String shortlistAndRank(
            Candidate[] candidates) {

        Candidate[] shortlisted =
                new Candidate[candidates.length];

        int count = 0;

        // Shortlist candidates
        for (Candidate candidate : candidates) {

            if (Candidate.isEligible(candidate.cgpa) ||
                Candidate.isEligible(
                    candidate.cgpa,
                    candidate.codingScore)) {

                shortlisted[count] = candidate;
                count++;
            }
        }

        // Create exact-size array
        Candidate[] finalList =
                new Candidate[count];

        for (int i = 0; i < count; i++) {
            finalList[i] = shortlisted[i];
        }

        // Required standard library sorting
        Arrays.sort(finalList);

        StringBuilder result =
                new StringBuilder();

        for (int i = 0; i < finalList.length; i++) {

            result.append(i + 1)
                  .append(". ")
                  .append(finalList[i].getName())
                  .append(" (")
                  .append(finalList[i].getCompositeScore())
                  .append(")");

            if (i < finalList.length - 1) {
                result.append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Candidate[] candidates = {

            new Candidate("Aisha", 8.2, 40),

            new Candidate("Rohit", 6.8, 65),

            new Candidate("Meena", 6.0, 90),

            new Candidate("Karan", 7.5, 20)
        };

        System.out.println(
            shortlistAndRank(candidates)
        );
    }
}