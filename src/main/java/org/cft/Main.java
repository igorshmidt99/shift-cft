package org.cft;

import org.cft.dto.DataFromFileDto;
import org.cft.dto.NumbersDto;
import org.cft.dto.StringDto;
import org.cft.extract.ExtractService;
import org.cft.extract.ExtractServiceImpl;
import org.cft.filler.FileFiller;
import org.cft.filler.FileFillerImpl;
import org.cft.filter.FilterServiceImpl;
import org.cft.parser.ParserService;
import org.cft.parser.ParserServiceImpl;
import org.cft.statistics.StatisticsServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments provided. Please provide input files and flags.");
            return;
        }
        setConfiguration(args);
        DataFromFileDto content = extractData(args);
        fillFilesAndPrintStatistics(content);
    }

    private static void fillFilesAndPrintStatistics(DataFromFileDto content) {
        String statisticsKey = "statistics";
        FileFiller fileFiller = new FileFillerImpl(new StatisticsServiceImpl());

        String intPath = System.getProperty("output.path", "");
        String intName = System.getProperty("file.prefix", "") + "integers.txt";
        String stringsPath = System.getProperty("output.path", ".");
        String stringName = System.getProperty("file.prefix", "") + "strings.txt";
        String floatsPath = System.getProperty("output.path", ".");
        String floatName = System.getProperty("file.prefix", "") + "floats.txt";

        NumbersDto integers = content.getIntegers();
        StringDto strings = content.getStrings();
        NumbersDto reals = content.getRealNumbers();
        if (!integers.getNumbers().isEmpty()) {
            integers = fileFiller.fillInts(intPath, intName, integers.getNumbers());
        }
        if (!strings.getStrings().isEmpty()) {
            strings = fileFiller.fillStrings(stringsPath, stringName, strings.getStrings());
        }
        if (!reals.getNumbers().isEmpty()) {
            reals = fileFiller.fillReals(floatsPath, floatName, reals.getNumbers());
        }

        if (System.getProperty(statisticsKey, "").equals("full")) {
            System.out.println(integers);
            System.out.println(reals);
            System.out.println(strings);
        } else if (System.getProperty(statisticsKey, "").equals("short")) {
            System.out.println("Total integers amount: " + integers.getNumbers().size());
            System.out.println("Total strings amount: " + strings.getStrings().size());
            System.out.println("Total real numbers amount: " + reals.getNumbers().size());
        }
    }

    private static DataFromFileDto extractData(String[] args) {
        ParserService parserService = new ParserServiceImpl();
        List<String> data = parserService.parseFrom(Arrays.stream(args).toList());
        ExtractService extractService = new ExtractServiceImpl(
                new FilterServiceImpl(
                        Pattern.compile("^-?\\d+$"),
                        Pattern.compile("^-?\\d+\\.\\d+([eE][-+]?\\d+)?$")
                ),
                new StatisticsServiceImpl()
        );
        return extractService.extract(data);
    }

    private static void setConfiguration(String[] args) {
        String statisticsKey = "statistics";
        for (int i = 0; i < args.length; i++) {
            if (Flag.O.getFlag().equals(args[i]) && i + 1 < args.length) {
                System.setProperty("output.path", args[i + 1] + "/");
                i++;
            } else if (Flag.P.getFlag().equals(args[i]) && i + 1 < args.length) {
                System.setProperty("file.prefix", args[i + 1] + "_");
                i++;
            } else if (Flag.A.getFlag().equals(args[i])) {
                System.setProperty("append", "true");
            } else if (Flag.F.getFlag().equals(args[i])) {
                System.setProperty(statisticsKey, "full");
            } else if (Flag.S.getFlag().equals(args[i])) {
                System.setProperty(statisticsKey, "short");
            }
        }
    }
}