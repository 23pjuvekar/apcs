package com.pratham;

public class StudentInfo {

    private String name = null;
    private final int MAX_TEST_GRADES = 5;
    private double[] testGrades = new double[MAX_TEST_GRADES];
    private final int MAX_QUIZ_GRADES = 8;
    private double[] quizGrades = new double[MAX_QUIZ_GRADES];
    private double homeworkAvg;
    private double finalAvg;

    public StudentInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTestGrade(double[] grades) {
        int size = grades.length < MAX_TEST_GRADES ? grades.length : MAX_TEST_GRADES;
        for (int i=0; i<size; i++) {
            testGrades[i] = grades[i];
        }
        calculateFinalAvg();
    }

    public void setQuizGrade(double[] grades) {
        int size = grades.length < MAX_QUIZ_GRADES ? grades.length : MAX_QUIZ_GRADES;
        for (int i=0; i<size; i++) {
            quizGrades[i] = grades[i];
        }
        calculateFinalAvg();
    }

    public void setHomeworkAvg(double avg) {
        homeworkAvg = avg;
        calculateFinalAvg();
    }

    public void calculateFinalAvg() {

        double testAvg = 0; int testCount = 0;
        for (int i=0; i<MAX_TEST_GRADES; i++) {
            if ( testGrades[i] > 0 ) {
                testAvg += testGrades[i];
                testCount++;
            }
        }
        testAvg = (testCount > 0 ? testAvg / testCount : 0);

        double quizAvg = 0; int quizCount = 0;
        for (int i=0; i<MAX_QUIZ_GRADES; i++) {
            if ( quizGrades[i] > 0 ) {
                quizAvg += quizGrades[i];
                quizCount++;
            }
        }
        quizAvg = (quizCount > 0 ? quizAvg / quizCount : 0);

        finalAvg = 0.5 * testAvg + 0.3 * quizAvg + 0.2 * homeworkAvg;
        finalAvg = (int)(finalAvg);
    }

    public double getFinalAvg() {
        return finalAvg;
    }
}
