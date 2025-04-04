package org.example.oopAndCaulate.calculate;

public class AdditionOperator implements  NewAritmeticOperator{

    @Override
    public boolean supports(String operater) {
        return "+".equals(operater);
    }

    @Override
    public int caculate(int a, int b) {
        return a+b;
    }

    @Override
    public int caculatePosive(PositiveNumber a, PositiveNumber b) {
        return a.getNumber()+b.getNumber();
    }


}
