package org.cft.dto;

import java.util.Objects;

public class DataFromFileDto {
    private StringDto strings;
    private NumbersDto integers;
    private NumbersDto realNumbers;

    public DataFromFileDto(StringDto strings, NumbersDto integers, NumbersDto realNumbers) {
        this.strings = strings;
        this.integers = integers;
        this.realNumbers = realNumbers;
    }

    public DataFromFileDto() {
    }

    public StringDto getStrings() {
        return strings;
    }

    public void setStrings(StringDto strings) {
        this.strings = strings;
    }

    public NumbersDto getIntegers() {
        return integers;
    }

    public void setIntegers(NumbersDto integers) {
        this.integers = integers;
    }

    public NumbersDto getRealNumbers() {
        return realNumbers;
    }

    public void setRealNumbers(NumbersDto realNumbers) {
        this.realNumbers = realNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DataFromFileDto that = (DataFromFileDto) o;
        return Objects.equals(strings, that.strings)
                && Objects.equals(integers, that.integers)
                && Objects.equals(realNumbers, that.realNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings, integers, realNumbers);
    }
}
