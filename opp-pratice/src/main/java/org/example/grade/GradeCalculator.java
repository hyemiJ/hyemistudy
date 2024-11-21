package org.example.grade;

import java.util.List;

public class GradeCalculator {

    List<Course> courses;
//    public GradeCalculator(List<Course> courses) {
//        this.courses = courses;
//    }

    public double calculateGradetest() {
        double result = 0;
        for (Course course : courses) {
            result = course.getCredit() * course.getGradeToNumber();
        }//학점수 * 교과목 평점의 합계

        int totalCountCredit = courses.stream().mapToInt(Course::getCredit).sum();// 총학점수

        return result / totalCountCredit;
    }//해당 메서드는 문제가 조금 있음.
    // 그 이유는 Course 멤버 변수들로 인한 계산을 이용했기 때문에 , 해당하는 계산을 Course에서 할 수 있음.
    //현재 처럼 getter 또는 메서드로 가져와서 계산할 것이 아니다.
    //이 해당 메서드를 사용해야하는 곳이 높다면 , 유지 보수하기 좋지 않아진다.
    //Course 에서 수행하면 응집도가 높아지는 결과를 얻을 수있다.
    public double calculateGradetest2() {
        double result = 0;
        for (Course course : courses) {
            result += course.multiplyCreditAndCourseGrade();
        }//학점수 * 교과목 평점의 합계

        int totalCountCredit = courses.stream().mapToInt(Course::getCredit).sum();// 총학점수

        return result / totalCountCredit;
    }
//    ------------------------------------------------
    //1급 함수 사용
    private Courses coursesCollection;

    public GradeCalculator(List<Course> courses) {
        this.coursesCollection = new Courses(courses);
    }

    public double calculateGrade() {
        //학점수 * 교과목 평점의 합계
        double result = 0;
        result = coursesCollection.multiplyCreditAndCourseGrade();
        // 총학점수
        int totalCountCredit = coursesCollection.calculateTotalComplete();

        return result / totalCountCredit;
    }
}
