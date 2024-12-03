package org.example.calculate;

public class PositiveNumber {
    private int number;
    public PositiveNumber(int number) {
        validate(number);
        this.number = number;
    }
    private void validate(int value) {
        if(isNegativeNumber(value)){
            throw new IllegalArgumentException("0 또는 음수를 전달 할 수 없습니다.");
        }
    }

    private boolean isNegativeNumber(int value) {
        return value <= 0;
    }

    public int getNumber() {
        return number;
    }
}
