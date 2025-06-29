package org.cft.dto;

import java.util.List;
import java.util.Objects;

public class NumbersDto {
    private List<String> numbers;
    private Number maxValue;
    private Number minValue;
    private Number sum;
    private Number average;

    public NumbersDto(List<String> numbers, Number maxValue, Number minValue, Number sum, Number average) {
        this.numbers = numbers;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.sum = sum;
        this.average = average;
    }

    public NumbersDto() {
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public Number getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Number maxValue) {
        this.maxValue = maxValue;
    }

    public Number getMinValue() {
        return minValue;
    }

    public void setMinValue(Number minValue) {
        this.minValue = minValue;
    }

    public Number getSum() {
        return sum;
    }

    public void setSum(Number sum) {
        this.sum = sum;
    }

    public Number getAverage() {
        return average;
    }

    public void setAverage(Number average) {
        this.average = average;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NumbersDto that = (NumbersDto) o;
        return Objects.equals(numbers, that.numbers)
                && Objects.equals(maxValue, that.maxValue)
                && Objects.equals(minValue, that.minValue)
                && Objects.equals(sum, that.sum)
                && Objects.equals(average, that.average);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers, maxValue, minValue, sum, average);
    }

    @Override
    public String toString() {
        return "Total amount = " + numbers.size() + "\n" +
                "maxValue = " + maxValue + "\n" +
                "minValue = " + minValue + "\n" +
                "sum = " + sum + "\n" +
                "average = " + average + "\n";
    }
}
