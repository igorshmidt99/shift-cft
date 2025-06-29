package org.cft.extract;

import org.cft.dto.DataFromFileDto;
import org.cft.filter.FilterService;
import org.cft.parser.ParserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtractServiceImpl implements ExtractService {

    private final FilterService filterService;
    private final ParserService parserService;

    public ExtractServiceImpl(FilterService filterService, ParserService parserService) {
        this.filterService = filterService;
        this.parserService = parserService;
    }

    @Override
    public DataFromFileDto extractData(String[] args) {
        List<String> data = parserService.parseFrom(Arrays.stream(args).toList());
        List<String> fromFile = new ArrayList<>(data);
        List<String> integers = filterService.filterIntegers(fromFile);
        List<String> reals = filterService.filterRealNumbers(fromFile);
        return new DataFromFileDto(fromFile, integers, reals);
    }
}
