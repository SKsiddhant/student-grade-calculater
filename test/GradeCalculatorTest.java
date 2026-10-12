import codsoft.task_2.StudentGradeCalculator;

/** Dependency-free checks: javac -d out src/codsoft/task_2/*.java test/*.java && java -cp out GradeCalculatorTest */
public class GradeCalculatorTest {
    static int failures = 0;

    static void eq(Object expected, Object actual, String what) {
        if (!expected.equals(actual)) {
            failures++;
            System.out.println("FAIL " + what + ": expected " + expected + " got " + actual);
        }
    }

    public static void main(String[] args) {
        eq("A+", StudentGradeCalculator.calculateGrade(90), "90 -> A+");
        eq("A", StudentGradeCalculator.calculateGrade(89.99), "89.99 -> A");
        eq("B", StudentGradeCalculator.calculateGrade(70), "70 -> B");
        eq("C", StudentGradeCalculator.calculateGrade(60), "60 -> C");
        eq("D", StudentGradeCalculator.calculateGrade(50), "50 -> D");
        eq("F", StudentGradeCalculator.calculateGrade(49.99), "49.99 -> F");
        eq(75.0, StudentGradeCalculator.averagePercentage(375, 5), "average");
        try {
            StudentGradeCalculator.averagePercentage(0, 0);
            failures++;
            System.out.println("FAIL zero subjects should throw");
        } catch (IllegalArgumentException expected) {
            // ok: used to print NaN%
        }
        System.out.println(failures == 0 ? "all tests passed" : failures + " failure(s)");
        System.exit(failures == 0 ? 0 : 1);
    }
}
