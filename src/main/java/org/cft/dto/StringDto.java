package org.cft.dto;

import java.util.List;
import java.util.Objects;


public class StringDto {
    private List<String> strings;
    private String minLength;
    private String maxLength;

    public StringDto() {
    }

    public StringDto(List<String> strings, String minLength, String maxLength) {
        this.strings = strings;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public String getMinLength() {
        return minLength;
    }

    public void setMinLength(String minLength) {
        this.minLength = minLength;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StringDto stringDto = (StringDto) o;
        return Objects.equals(strings, stringDto.strings)
                && Objects.equals(minLength, stringDto.minLength)
                && Objects.equals(maxLength, stringDto.maxLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings, minLength, maxLength);
    }

    @Override
    public String toString() {
        return "Total amount = " + strings.size() + "\n" +
                "min Length = " + minLength + "\n" +
                "max Length = " + maxLength + "\n";
    }
}
