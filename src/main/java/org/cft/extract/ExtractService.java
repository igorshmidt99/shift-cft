package org.cft.extract;

import java.util.List;

public interface ExtractService {

    DataFromFileDto extract(List<String> content);

}
