package org.example.grade;

import java.util.List;

public class Courses {
    private final List<Course> courses;
    //list 형태로의 Course 의 정보만 가진 클래스로 , list를 컨트롤 하기 위한 클래스.
    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade(){
//        double result = 0;
//        for (Course course : courses) {
//            result += course.multiplyCreditAndCourseGrade();
//        }
//        return result;

        return courses.stream().mapToDouble(Course::multiplyCreditAndCourseGrade)
                .sum();
    }//학점수 * 교과목 평점의 합계

    public int calculateTotalComplete() {
        return courses.stream().mapToInt(Course::getCredit).sum();
    }// 총학점수
}
