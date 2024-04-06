package builders;

import storage.StringState;

/**
 * Вторая версия аналога StringBuilder с функцией отмены последней операции (функции кроме append() не
 * реализованы в целях упрощения), сохранение состояния происходит внутри класса storage.StringState, в переменной String str
 */
public class CustomStringBuilder_v2 {
    private String currentString = "";
    private StringState savedState = new StringState();

    public CustomStringBuilder_v2 append(Object obj) {
        return append(String.valueOf(obj));
    }

    public CustomStringBuilder_v2 append(String str) {
        saveState(currentString);
        currentString += str;
        return this;
    }

    private void saveState(String str) {
        savedState = savedState.saveState(str);
    }

    public void undo() {
        currentString = savedState.getStr();
    }

    @Override
    public String toString() {
        return currentString;
    }

}
