package org.cft.filler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileFillerImpl implements FileFiller {

    @Override
    public void fill(String filePath, String fileName, List<String> content) {
        try {
            fillByPath(filePath, fileName, content);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            System.err.println("Writing to current directory.");
            try {
                fillByPath("", fileName, content);
            } catch (IOException ex) {
                System.err.println("Error writing to current directory.");
            }
        }
    }

    private void fillByPath(String filePath, String fileName, List<String> content) throws IOException {
        Path path = Path.of(filePath + fileName);
        if (System.getProperty("append", "false").equals("true")) {
            Files.write(path, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } else {
            if (path.toFile().exists()) {
                Files.delete(path);
            }
            Files.write(path, content, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        }
    }
}