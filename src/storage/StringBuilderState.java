package storage;

public class StringBuilderState {
    private StringBuilder builder;

    public StringBuilderState() {
        builder = new StringBuilder();
    }

    public StringBuilderState(StringBuilder builder) {
        this.builder = builder;
    }

    public void saveStateBuilder(String str) {
        this.builder = new StringBuilder(str);
    }

    public StringBuilder getBuilder() {
        return builder;
    }
}
