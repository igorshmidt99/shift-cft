package org.cft.filler;

import org.cft.dto.DataFromFileDto;
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
    public void fillFiles(DataFromFileDto content) {
        String intPath = System.getProperty("output.path", "");
        String intName = System.getProperty("file.prefix", "") + "integers.txt";
        String stringsPath = System.getProperty("output.path", "");
        String stringName = System.getProperty("file.prefix", "") + "strings.txt";
        String floatsPath = System.getProperty("output.path", "");
        String floatName = System.getProperty("file.prefix", "") + "floats.txt";
        List<String> integers = content.getIntegers();
        List<String> strings = content.getStrings();
        List<String> reals = content.getRealNumbers();
        NumbersDto intsWithStats = new NumbersDto(List.of(), 0, 0, 0, 0);
        StringDto stringsWithStats = new StringDto(List.of(), "0", "0");
        NumbersDto realsWithStats = new NumbersDto(List.of(), 0, 0, 0, 0);

        if (!integers.isEmpty()) {
            intsWithStats = fillInts(intPath, intName, integers);
        }
        if (!strings.isEmpty()) {
            stringsWithStats = fillStrings(stringsPath, stringName, strings);
        }
        if (!reals.isEmpty()) {
            realsWithStats = fillReals(floatsPath, floatName, reals);
        }

        statisticsService.printStatistics(intsWithStats, stringsWithStats, realsWithStats);
    }

    private NumbersDto fillInts(String filePath, String fileName, List<String> content) {
        NumbersDto numbersDto = new NumbersDto();
        try {
            fillByPath(filePath, fileName, content);
            numbersDto = statisticsService.readIntsStatistics(filePath, fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            System.err.println("Writing to current directory.");
            try {
                fillByPath("", fileName, content);
                numbersDto = statisticsService.readIntsStatistics("", fileName);
            } catch (IOException ex) {
                System.err.println("Error writing to current directory.");
            }
        }
        return numbersDto;
    }

    private StringDto fillStrings(String filePath, String fileName, List<String> content) {
        StringDto stringDto = new StringDto();
        try {
            fillByPath(filePath, fileName, content);
            stringDto = statisticsService.readStringsStatistics(filePath, fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            System.err.println("Writing to current directory.");
            try {
                fillByPath("", fileName, content);
                stringDto = statisticsService.readStringsStatistics("", fileName);
            } catch (IOException ex) {
                System.err.println("Error writing to current directory.");
            }
        }
        return stringDto;
    }

    private NumbersDto fillReals(String filePath, String fileName, List<String> content) {
        NumbersDto numbersDto = new NumbersDto();
        try {
            fillByPath(filePath, fileName, content);
            numbersDto = statisticsService.readRealsStatistics(filePath, fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            System.err.println("Writing to current directory.");
            try {
                fillByPath("", fileName, content);
                numbersDto = statisticsService.readRealsStatistics("", fileName);
            } catch (IOException ex) {
                System.err.println("Error writing to current directory.");
            }
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