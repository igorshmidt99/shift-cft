package org.cft.filler;

import org.cft.dto.NumbersDto;
import org.cft.dto.StringDto;
import org.cft.statistics.StatisticsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileFillerImpl implements FileFiller {

    private final StatisticsService statisticsService;

    public FileFillerImpl(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Override
    public NumbersDto fillInts(String filePath, String fileName, List<String> content) {
        NumbersDto numbersDto = new NumbersDto();
        try {
            fillByPath(filePath, fileName, content);
            numbersDto = readIntsStatistics(filePath, fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            System.err.println("Writing to current directory.");
            try {
                fillByPath("", fileName, content);
                numbersDto = readIntsStatistics("", fileName);
            } catch (IOException ex) {
                System.err.println("Error writing to current directory.");
            }
        }
        return numbersDto;
    }

    @Override
    public StringDto fillStrings(String filePath, String fileName, List<String> content) {
        StringDto stringDto = new StringDto();
        try {
            fillByPath(filePath, fileName, content);
            stringDto = readStringsStatistics(filePath, fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            System.err.println("Writing to current directory.");
            try {
                fillByPath("", fileName, content);
                stringDto = readStringsStatistics("", fileName);
            } catch (IOException ex) {
                System.err.println("Error writing to current directory.");
            }
        }
        return stringDto;
    }

    @Override
    public NumbersDto fillReals(String filePath, String fileName, List<String> content) {
        NumbersDto numbersDto = new NumbersDto();
        try {
            fillByPath(filePath, fileName, content);
            numbersDto = readRealsStatistics(filePath, fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            System.err.println("Writing to current directory.");
            try {
                fillByPath("", fileName, content);
                numbersDto = readRealsStatistics("", fileName);
            } catch (IOException ex) {
                System.err.println("Error writing to current directory.");
            }
        }
        return numbersDto;
    }

    private StringDto readStringsStatistics(String filePath, String fileName) {
        StringDto stringDto = new StringDto();
        try {
            Path path = Path.of(filePath + fileName);
            List<String> data = Files.readAllLines(path);
            stringDto = statisticsService.getStatisticsFromStrings(data);
        } catch (IOException e) {
            System.err.println("Can't read statistics.");
        }
        return stringDto;
    }

    private NumbersDto readIntsStatistics(String filePath, String fileName) {
        NumbersDto numbersDto = new NumbersDto();
        try {
            Path path = Path.of(filePath + fileName);
            List<String> data = Files.readAllLines(path);
            numbersDto = statisticsService.getStatisticsFromIntegers(data);
        } catch (IOException e) {
            System.err.println("Can't read statistics.");
        }
        return numbersDto;
    }

    private NumbersDto readRealsStatistics(String filePath, String fileName) {
        NumbersDto numbersDto = new NumbersDto();
        try {
            Path path = Path.of(filePath + fileName);
            List<String> data = Files.readAllLines(path);
            numbersDto = statisticsService.getStatisticsFromRealNumbers(data);
        } catch (IOException e) {
            System.err.println("Can't read statistics.");
        }
        return numbersDto;
    }

    private void fillByPath(String filePath, String fileName, List<String> content) throws IOException {
        Path path = Path.of(filePath + fileName);
        if (System.getProperty("append", "false").equals("true")) {
            Files.write(path, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } else {
            if (path.toFile().exists()) {
                Files.delete(path);
            }
            Files.write(path, content, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        }
    }
}