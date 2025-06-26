package org.cft.filter;

import java.util.List;

public interface FilterService {

    List<String> filterIntegers(List<String> fileContent);

    List<String> filterRealNumbers(List<String> fileContent);

}
