package org.cft.extract;

import org.cft.dto.DataFromFileDto;

import java.util.List;

public interface ExtractService {

    DataFromFileDto extract(List<String> content);

}
