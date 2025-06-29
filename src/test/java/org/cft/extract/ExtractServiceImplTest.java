package org.cft.extract;

import org.cft.dto.DataFromFileDto;
import org.cft.dto.NumbersDto;
import org.cft.dto.StringDto;
import org.cft.filter.FilterServiceImpl;
import org.cft.parser.ParserServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExtractServiceImplTest {

    ExtractService service = new ExtractServiceImpl(
            new FilterServiceImpl(
                    Pattern.compile("^-?\\d+$"),
                    Pattern.compile("^-?\\d+\\.\\d+([eE][-+]?\\d+)?$")
            ), new ParserServiceImpl()
    );

    @Test
    void extractFromGoodFiles() {
        List<String> fromFile = List.of("Я читаю текст", "1234", "123.123", "False", "False123",
                "False123.213", "false", "Text with content", "1234", "true", "1234.1234",
                "Something special", "1234", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer");
        List<String> strings = List.of("Я читаю текст", "False", "False123", "False123.213", "false",
                "Text with content", "true", "Something special", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer");
        List<String> ints = List.of("1234", "1234", "1234");
        List<String> realNums = List.of("123.123", "1234.1234");

        StringDto stringDto = new StringDto();
        stringDto.setStrings(strings);
        stringDto.setMaxLength("1234.2345!@#@$*!@&$@$qwer");
        stringDto.setMinLength("true");

        NumbersDto intsDto = new NumbersDto();
        intsDto.setNumbers(ints);
        intsDto.setAverage(1234L);
        intsDto.setMaxValue(1234L);
        intsDto.setSum(3702L);
        intsDto.setMinValue(1234L);

        NumbersDto realsDto = new NumbersDto();
        realsDto.setNumbers(realNums);
        realsDto.setAverage(678.6232D);
        realsDto.setMaxValue(1234.1234D);
        realsDto.setSum(1357.2464D);
        realsDto.setMinValue(123.123D);

        DataFromFileDto expected = new DataFromFileDto(strings, ints, realNums);
        String prefix = "src/test/resources/";
        String[] args = {"-o", "src/test/resources/created", "-p", "endToEndTest",
                prefix + "something.txt", prefix + "sm.txt", prefix + "path/to/file/1-2_3$r.txt"};
        DataFromFileDto actual = service.extractData(args);

//        assertEquals(expected.getIntegers().getAverage(), actual.getIntegers().getAverage());
//        assertEquals(expected.getIntegers().getMaxValue(), actual.getIntegers().getMaxValue());
//        assertEquals(expected.getIntegers().getMinValue(), actual.getIntegers().getMinValue());
//        assertEquals(expected.getIntegers().getSum(), actual.getIntegers().getSum());
//        assertEquals(expected.getIntegers().getNumbers(), actual.getIntegers().getNumbers());
//
//        assertEquals(expected.getRealNumbers().getAverage(), actual.getRealNumbers().getAverage());
//        assertEquals(expected.getRealNumbers().getMaxValue(), actual.getRealNumbers().getMaxValue());
//        assertEquals(expected.getRealNumbers().getMinValue(), actual.getRealNumbers().getMinValue());
//        assertEquals(expected.getRealNumbers().getSum(), actual.getRealNumbers().getSum());
//        assertEquals(expected.getRealNumbers().getNumbers(), actual.getRealNumbers().getNumbers());
        assertEquals(expected, actual);
    }

}