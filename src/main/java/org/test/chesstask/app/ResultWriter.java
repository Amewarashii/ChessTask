package org.test.chesstask.app;

import java.io.IOException;

public interface ResultWriter {

    void write(StringBuilder builder) throws IOException;

    void open(Input config) throws IOException;

    void close();
}
