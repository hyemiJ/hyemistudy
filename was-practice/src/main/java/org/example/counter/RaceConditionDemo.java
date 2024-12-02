package org.example.counter;



//*레이스 컨디션이란
//여러 프로세스 혹은 스레드가 동시에 하나의 자원에 접근하기 위해 경쟁하는 상태를 레이스 컨디션이라고 한다.

public class RaceConditionDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();//싱글톤  객체
        Thread t1 = new Thread(counter,"Thread-1");// 멀티 스레드 환경
        Thread t2 = new Thread(counter,"Thread-2");// 멀티 스레드 환경
        Thread t3 = new Thread(counter,"Thread-3");// 멀티 스레드 환경
        //멀티 스레드 환경에서 하나의 객체를 공유하게 된다면 ,하나의 자원을 공유하게 되면 , 우리가 뜻하지 않는 결과를 볼 수 있다.
        //1. 상태 유지되게 만든 코드
        t1.start();
        t2.start();
        t3.start();
        //2. 동기화 작업전 실행 결과
//        value for Thread After increment Thread-3 :2
//        value for Thread After increment Thread-2 :3
//        value for Thread at last Thread-3 :2
//        value for Thread After increment Thread-1 :1
//        value for Thread at last Thread-1 :0
//        value for Thread at last Thread-2 :1
        // 즉 여러 스레드가 하나의 자원에 동시에 접근하기 위해 경쟁을 하면서 우리가 원하지 않는 결과가 나올 수있다는 것을 알 수 있다.
        //3. 동기화 작업 후 실행 결과
//        value for Thread After increment Thread-1 :1
//        value for Thread at last Thread-1 :0
//        value for Thread After increment Thread-3 :1
//        value for Thread at last Thread-3 :0
//        value for Thread After increment Thread-2 :1
//        value for Thread at last Thread-2 :0
        //
    }
}
