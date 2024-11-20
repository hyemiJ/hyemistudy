package org.example.calculate;

public class DivisionOprator implements NewAritmeticOperator{

    @Override
    public boolean supports(String operater) {
        return "/".equals(operater);
    }

    @Override
    public int caculate(int a, int b) {
        if(b ==0){
            throw new IllegalArgumentException("나눗셈에서 0을 나눌 수 없습니다.");
        }
        return a/b;
    }

    @Override
    public int caculatePosive(PositiveNumber a, PositiveNumber b) {
        return a.getNumber()/b.getNumber();
    }
}
