package org.cft.filler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileFillerImpl implements FileFiller {

    @Override
    public void fill(String filePath, List<String> content) {
        try {
            Path path = Path.of(filePath);
            File file = path.toFile();
            if (file.exists()) {
                file.delete();
            }
            Files.createFile(path);
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
                for (String line : content) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
