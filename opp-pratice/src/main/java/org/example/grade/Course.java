package org.example.grade;

import java.sql.PreparedStatement;

public class Course {

    private final String subject;//과목명
    private final int credit;//학점 수
    private final String grade;//성적단위 ( )

    public Course(String subject, int credit, String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }


    public int getCredit() {
        return credit;
    }


    public double getGradeToNumber() {
        double result = 0;
        switch (this.grade) {
            case "A+": result = 4.5; break;
            case "A": result = 4.0; break;
            case "B+": result = 3.5; break;
            case "B": result = 3.0; break;
            case "C+": result = 2.5; break;
            case "C": result = 2.0; break;
        }
        return result;
    }

    public double multiplyCreditAndCourseGrade(){
        return credit * this.getGradeToNumber();
    }// 이 메서드를 사용함으로서 , 응집도가 높기 떄문에 , 변화가 생겼을때 한군데만 수정해주면 되는 이점이 발생한다.


}
