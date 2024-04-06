package builders;

import storage.StringBuilderState;

/**
 * Третья версия аналога StringBuilder с функцией отмены последней операции (функции кроме append() не
 * реализованы в целях упрощения), сохранение состояния происходит внутри класса storage.StringBuilderState,
 * в переменной StringBuilder builder
 */
public class CustomStringBuilder_v3 {
    private StringBuilder currentString;
    private StringBuilderState builderState = new StringBuilderState(currentString);

    public CustomStringBuilder_v3() {
        currentString = new StringBuilder();
    }

    public CustomStringBuilder_v3(String str) {
        currentString = new StringBuilder(str);
    }


    public void append(Object obj) {
        builderState.saveStateBuilder(currentString.toString());
        currentString.append(obj);
    }

    public void undo() {
        currentString = builderState.getBuilder();
    }

    @Override
    public String toString() {
        return currentString.toString();
    }

}
