package org.cft.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterServiceImpl implements FilterService {

    private final Pattern intPattern;
    private final Pattern realNumbersPattern;

    public FilterServiceImpl(Pattern integers, Pattern realNumbersPattern) {
        this.intPattern = integers;
        this.realNumbersPattern = realNumbersPattern;
    }

    @Override
    public List<String> filterIntegers(List<String> fromFile) {
        return filterNums(fromFile, intPattern);
    }

    @Override
    public List<String> filterRealNumbers(List<String> fromFile) {
        return filterNums(fromFile, realNumbersPattern);
    }

    private List<String> filterNums(List<String> fromFile, Pattern pattern) {
        List<String> filtered = new ArrayList<>(fromFile.size());
        for (int i = 0; i < fromFile.size(); i++) {
            String value = fromFile.get(i);
            Matcher matcher = pattern.matcher(value);
            if (matcher.matches()) {
                filtered.add(value);
                fromFile.remove(i);
                i--;
            }
        }
        return filtered;
    }
}
