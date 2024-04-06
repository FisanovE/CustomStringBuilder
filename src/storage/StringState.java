package storage;

public class StringState {
    private String str;

    public StringState() {
    }

    public StringState(String str) {
        this.str = str;
    }

    public StringState saveState(String str) {
        return new StringState(str);
    }

    public String getStr() {
        return str;
    }


}
