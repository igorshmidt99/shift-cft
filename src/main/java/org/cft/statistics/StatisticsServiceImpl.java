package org.cft.statistics;

import org.cft.dto.NumbersDto;
import org.cft.dto.StringDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public StringDto readStringsStatistics(String filePath, String fileName) {
        StringDto stringDto = new StringDto();
        try {
            Path path = Path.of(filePath + fileName);
            List<String> data = Files.readAllLines(path);
            if (!data.isEmpty()) {
                stringDto = getStatisticsFromStrings(data);
            }
        } catch (IOException e) {
            System.err.println("Can't read statistics.");
        }
        return stringDto;
    }

    @Override
    public NumbersDto readRealsStatistics(String filePath, String fileName) {
        NumbersDto numbersDto = new NumbersDto();
        try {
            Path path = Path.of(filePath + fileName);
            List<String> data = Files.readAllLines(path);
            if (!data.isEmpty()) {
                numbersDto = getStatisticsFromRealNumbers(data);
            }
        } catch (IOException e) {
            System.err.println("Can't read statistics.");
        }
        return numbersDto;
    }

    @Override
    public NumbersDto readIntsStatistics(String filePath, String fileName) {
        NumbersDto numbersDto = new NumbersDto();
        try {
            Path path = Path.of(filePath + fileName);
            List<String> data = Files.readAllLines(path);
            if (!data.isEmpty()) {
                numbersDto = getStatisticsFromIntegers(data);
            }
        } catch (IOException e) {
            System.err.println("Can't read statistics.");
        }
        return numbersDto;
    }

    @Override
    public void printStatistics(NumbersDto intsWithStats, StringDto stringsWithStats, NumbersDto realsWithStats) {
        String statisticsKey = "statistics";
        if (System.getProperty(statisticsKey, "").equals("full")) {
            System.out.println(intsWithStats);
            System.out.println(realsWithStats);
            System.out.println(stringsWithStats);
        } else if (System.getProperty(statisticsKey, "").equals("short")) {
            System.out.println("Total integers amount: " + intsWithStats.getNumbers().size());
            System.out.println("Total strings amount: " + stringsWithStats.getStrings().size());
            System.out.println("Total real numbers amount: " + realsWithStats.getNumbers().size());
        }
    }

    private StringDto getStatisticsFromStrings(List<String> strings) {
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
        return new StringDto(strings, String.valueOf(min.length()), String.valueOf(max.length()));
    }

    private NumbersDto getStatisticsFromIntegers(List<String> integers) {
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

    private NumbersDto getStatisticsFromRealNumbers(List<String> realNums) {
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