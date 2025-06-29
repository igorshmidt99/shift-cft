package org.cft;

import org.cft.dto.DataFromFileDto;
import org.cft.extract.ExtractService;
import org.cft.extract.ExtractServiceImpl;
import org.cft.filler.FileFiller;
import org.cft.filler.FileFillerImpl;
import org.cft.filter.FilterService;
import org.cft.filter.FilterServiceImpl;
import org.cft.parser.ParserService;
import org.cft.parser.ParserServiceImpl;
import org.cft.statistics.StatisticsService;
import org.cft.statistics.StatisticsServiceImpl;

import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments provided. Please provide input files and flags.");
            return;
        }
        StatisticsService statisticsService = new StatisticsServiceImpl();
        FileFiller fileFiller = new FileFillerImpl(statisticsService);
        FilterService filterService = new FilterServiceImpl(Pattern.compile("^-?\\d+$"),
                Pattern.compile("^-?\\d+\\.\\d+([eE][-+]?\\d+)?$"));
        ParserService parserService = new ParserServiceImpl();
        ExtractService extractService = new ExtractServiceImpl(filterService, parserService);

        setConfiguration(args);
        DataFromFileDto content = extractService.extractData(args);
        fileFiller.fillFiles(content);
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