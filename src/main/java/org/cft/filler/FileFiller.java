package org.cft.filler;

import org.cft.dto.NumbersDto;
import org.cft.dto.StringDto;

import java.util.List;

public interface FileFiller {

    NumbersDto fillInts(String intPath, String intName, List<String> numbers);

    StringDto fillStrings(String stringsPath, String stringName, List<String> strings);

    NumbersDto fillReals(String floatsPath, String floatName, List<String> numbers);
}
