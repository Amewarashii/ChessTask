package org.test.chesstask.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileResultWriter implements ResultWriter {

    private static final Logger LOGGER = Logger.getLogger(FileResultWriter.class.getSimpleName());

    private FileOutputStream os;

    public void open(Input config) throws IOException {
        File file = new File(config.toString());
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Unable to create results file");
        }
        os = new FileOutputStream(file);
    }

    @Override
    public void close() {
        try {
            os.close();
        } catch (IOException e) {
            LOGGER.log(Level.FINEST, e.getMessage(), e);
        }
    }

    public void write(StringBuilder builder) throws IOException {
        os.write(builder.toString().getBytes());
        os.flush();
    }
}
