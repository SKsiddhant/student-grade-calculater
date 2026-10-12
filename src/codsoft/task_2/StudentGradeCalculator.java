package codsoft.task_2;

import java.util.Locale;
import java.util.Scanner;

/**
 * Task 2: Student Grade Calculator.
 * Reads the marks (out of 100) for each subject, then prints the total,
 * the average percentage and the grade.
 */
public class StudentGradeCalculator {

    static final int MAX_MARKS_PER_SUBJECT = 100;

    /** Average percentage across subjects, each worth {@code MAX_MARKS_PER_SUBJECT}. */
    public static double averagePercentage(int totalMarks, int numSubjects) {
        if (numSubjects <= 0) {
            throw new IllegalArgumentException("Number of subjects must be at least 1.");
        }
        return (double) totalMarks / (numSubjects * MAX_MARKS_PER_SUBJECT) * 100;
    }

    public static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        }
        return "F";
    }

    /** Keeps asking until the user types a whole number in [min, max]. */
    static int readInt(Scanner in, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!in.hasNext()) {
                throw new IllegalStateException("Input ended before all values were entered.");
            }
            if (in.hasNextInt()) {
                int value = in.nextInt();
                if (value >= min && value <= max) {
                    return value;
                }
            } else {
                in.next(); // discard the bad token so we do not loop forever
            }
            System.out.println("Please enter a whole number between " + min + " and " + max + ".");
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numSubjects = readInt(scanner, "Hello, enter the number of subjects: ", 1, 50);

            int totalMarks = 0;
            for (int i = 0; i < numSubjects; i++) {
                totalMarks += readInt(scanner,
                        "Enter marks obtained in subject " + (i + 1) + " (0-" + MAX_MARKS_PER_SUBJECT + "): ",
                        0, MAX_MARKS_PER_SUBJECT);
            }

            double average = averagePercentage(totalMarks, numSubjects);

            System.out.println("Your result is here:");
            System.out.println("Total marks: " + totalMarks + " / " + (numSubjects * MAX_MARKS_PER_SUBJECT));
            System.out.println(String.format(Locale.ROOT, "Average percentage: %.2f%%", average));
            System.out.println("Grade: " + calculateGrade(average));
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
