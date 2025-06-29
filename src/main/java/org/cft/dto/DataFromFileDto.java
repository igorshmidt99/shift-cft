package org.cft.dto;

import java.util.List;
import java.util.Objects;

public class DataFromFileDto {
    private List<String> strings;
    private List<String> integers;
    private List<String> realNumbers;

    public DataFromFileDto(List<String> strings, List<String> integers, List<String> realNumbers) {
        this.strings = strings;
        this.integers = integers;
        this.realNumbers = realNumbers;
    }

    public DataFromFileDto() {
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public List<String> getIntegers() {
        return integers;
    }

    public void setIntegers(List<String> integers) {
        this.integers = integers;
    }

    public List<String> getRealNumbers() {
        return realNumbers;
    }

    public void setRealNumbers(List<String> realNumbers) {
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
