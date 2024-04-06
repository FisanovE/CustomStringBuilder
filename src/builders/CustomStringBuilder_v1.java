package builders;

/**
 * Первая версия аналога StringBuilder с функцией отмены последней операции (функции кроме append() не
 * реализованы в целях упрощения), сохранение состояния происходит внутри класса, в переменной String oldString
 */
public class CustomStringBuilder_v1 {
    private String currentString = "";
    private String oldString = "";

    public CustomStringBuilder_v1() {
    }

    public CustomStringBuilder_v1(String currentString) {
        this.currentString = currentString;
    }

    public void append(Object obj) {
        append(String.valueOf(obj));
    }

    public void append(String str) {
        saveState(currentString);
        currentString += str;
    }

    private void saveState(String str) {
        oldString = str;
    }

    public void undo() {
        currentString = oldString;
    }

    @Override
    public String toString() {
        return currentString;
    }

}
