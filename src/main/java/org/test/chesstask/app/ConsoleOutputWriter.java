package org.test.chesstask.app;

public class ConsoleOutputWriter implements ResultWriter {
    
    @Override
    public void write(StringBuilder builder) {
        System.out.println(builder);
    }

    @Override
    public void open(Input config) {
    }

    @Override
    public void close() {
    }
}