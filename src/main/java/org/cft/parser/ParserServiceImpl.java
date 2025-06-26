package org.cft.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ParserServiceImpl implements ParserService {

    @Override
    public List<String> parseFrom(List<String> args) {
        Pattern filePattern = Pattern.compile(".+(\\.txt)$");
        return args.stream()
                .filter(arg -> filePattern.matcher(arg).matches())
                .map(Path::of)
                .flatMap(toContent())
                .toList();
    }

    private static Function<? super Path, ? extends Stream<? extends String>> toContent() {
        return path -> {
            try {
                return Files.lines(path);
            } catch (IOException e) {
                System.err.println("Error reading file: " + path);
            }
            return Stream.empty();
        };
    }

}
