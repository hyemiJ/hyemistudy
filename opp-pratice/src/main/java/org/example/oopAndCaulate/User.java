package org.example.oopAndCaulate;

public class User {
    private String password;

    //강한 결합도를 사용하는 메서드.
    public void initPassword1() {
         PasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
        String randomPassword = randomPasswordGenerator.generatePassword();
        if(randomPassword.length()>=8 && randomPassword.length()<=12){
            this.password = randomPassword;
        }//해당 조건을 만족했을때 password에 초기화.
        // 이부분에 대한 컨트롤이 힘들게 됨.
    }

    //낮은 결합도를 사용하는 메서드
    //인자로서 인터페이스를 만들어서 사용했기 때문에 , RandomPasswordGenerator에 의존을 가진 것이 아닌 , 낮은 결합으로 정의 할 수 있다.
    //여기서 말하는 의존은 만약 RandomPasswordGenerator가 다른 패키지에 있었다면 import가 필요했을 것이다.

    public void initPassword(PasswordGenerator generator) {
       // PasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
        String randomPassword = generator.generatePassword();
        if(randomPassword.length()>=8 && randomPassword.length()<=12){
            this.password = randomPassword;
        }//해당 조건을 만족했을때 password에 초기화.
        // 이부분에 대한 컨트롤이 힘들게 됨.
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
