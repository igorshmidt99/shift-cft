package org.cft.statistics;

import org.cft.dto.NumbersDto;
import org.cft.dto.StringDto;

import java.util.List;

public class StatisticsServiceImpl implements StatisticsService {

    // TODO исправить сервис статистики
    @Override
    public StringDto getStatisticsFromStrings(List<String> strings) {
        var min = String.valueOf(Long.MAX_VALUE);
        var max = "";
        for (String value : strings) {
            if (value.length() < min.length()) {
                min = value;
            }
            if (max.length() < value.length()) {
                max = value;
            }
        }
        return new StringDto(strings, min, max);
    }

    @Override
    public NumbersDto getStatisticsFromIntegers(List<String> integers) {
        List<Long> parsedIntegers = integers.stream()
                .map(Long::parseLong)
                .toList();
        var min = Long.MAX_VALUE;
        var max = 0L;
        var sum = 0L;
        var average = 0L;
        NumbersDto integersDto = new NumbersDto();
        for (Long val : parsedIntegers) {
            if (val < min) {
                min = val;
            }
            if (val > max) {
                max = val;
            }
            sum += val;
        }
        if (!integers.isEmpty()) {
            average = sum / integers.size();
        }
        integersDto.setNumbers(integers);
        integersDto.setAverage(average);
        integersDto.setMaxValue(max);
        integersDto.setMinValue(min);
        integersDto.setSum(sum);
        return integersDto;
    }

    @Override
    public NumbersDto getStatisticsFromRealNumbers(List<String> realNums) {
        List<Double> parsedReals = realNums.stream()
                .map(Double::parseDouble)
                .toList();
        var min = Double.MAX_VALUE;
        var max = 0.0;
        var sum = 0.0;
        var average = 0.0;
        NumbersDto realNumbersDto = new NumbersDto();
        for (Double val : parsedReals) {

            if (val < min) {
                min = val;
            }
            if (val > max) {
                max = val;
            }
            sum += val;
        }
        if (!realNums.isEmpty()) {
            average = sum / realNums.size();
        }
        realNumbersDto.setNumbers(realNums);
        realNumbersDto.setAverage(average);
        realNumbersDto.setMaxValue(max);
        realNumbersDto.setMinValue(min);
        realNumbersDto.setSum(sum);
        return realNumbersDto;
    }

}