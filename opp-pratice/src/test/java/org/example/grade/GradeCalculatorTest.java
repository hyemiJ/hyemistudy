package org.example.grade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
- 요구사항
    - 평균 학점 계산 방법 = (학점 * 교과목 평점)의 합계 / 수강신청 총학점 수
    - 일급 컬렉션 사용
    - 객체 지향 설계방식 대로 진행
 */
public class GradeCalculatorTest {
    //학점계산기 도메인 : 이수한 과목(객체지향 프로그래밍 , 자료구조 , 중국어 회화) , 학점 계산기
    //이수한 과목 ? -> 과목(코스) 클래스

    //평균학정을 계산하기 위해서는 이수한 과목을 전달하여 평균학점 계산을 요청한다 ---> 누구에게 요청할까 ? : 학점계산기
    // 계산 요청 ---> 학점 계산기 ---> 학점 수 * 교과목 평점 의 합계 --->과목(코스)
    //                              수강 신청 총 학점 수         ---> 과목(코스)


    @DisplayName("평균 학점을 계산한다.")
    @Test
    void calculateGradeTest() {
        List<Course> courses = List.of(new Course("OOP",3,"A+")
        ,new Course("자료구조",4,"A+"), new Course("중국어",2,"A+"));

        GradeCalculator gradeCalculator = new GradeCalculator(courses);
        double result = gradeCalculator.calculateGrade();

        assertThat(result).isEqualTo(4.5);
    }
}
