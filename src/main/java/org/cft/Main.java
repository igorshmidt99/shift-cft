package org.cft;

import org.cft.extract.DataFromFileDto;
import org.cft.extract.ExtractService;
import org.cft.extract.ExtractServiceImpl;
import org.cft.filler.FileFiller;
import org.cft.filler.FileFillerImpl;
import org.cft.filter.FilterServiceImpl;
import org.cft.parser.ParserService;
import org.cft.parser.ParserServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        ParserService parserService = new ParserServiceImpl();
        List<String> data = parserService.parseFrom(Arrays.stream(args).toList());
        ExtractService extractService = new ExtractServiceImpl(
                new FilterServiceImpl(Pattern.compile("^-?\\d+$"), Pattern.compile("^-?\\d+\\.\\d+([eE][-+]?\\d+)?$"))
        );
        DataFromFileDto content = extractService.extract(data);
        FileFiller fileFiller = new FileFillerImpl();
        fileFiller.fill("integers.txt", content.getIntegers());
        fileFiller.fill("strings.txt", content.getStrings());
        fileFiller.fill("floats.txt", content.getRealNumbers());
        
    }
}