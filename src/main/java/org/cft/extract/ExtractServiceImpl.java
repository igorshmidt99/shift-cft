package org.cft.extract;

import org.cft.filter.FilterService;

import java.util.ArrayList;
import java.util.List;

public class ExtractServiceImpl implements ExtractService {

    private final FilterService filterService;

    public ExtractServiceImpl(FilterService filterService) {
        this.filterService = filterService;
    }

    @Override
    public DataFromFileDto extract(List<String> content) {
        List<String> fromFile = new ArrayList<>(content);
        List<String> integers = filterService.filterIntegers(fromFile);
        List<String> reals = filterService.filterRealNumbers(fromFile);
        return new DataFromFileDto(fromFile, integers, reals);
    }
}
