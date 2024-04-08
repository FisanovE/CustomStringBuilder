package builders;

import storage.SnapShotStorage;

/**
 * Вторая версия аналога StringBuilder с функцией отмены последней операции,
 * сохранение состояния происходит внутри класса storage.StringState
 */
public class CustomStringBuilder {

    /**
     * value используется для хранения символов.
     */
    byte[] value;


//    byte coder;

    /**
     * count - это количество используемых символов.
     */
    int count;

    /**
     * Константа для установки в value пустого массива.
     */
    private static final byte[] EMPTYVALUE = new byte[0];

    SnapShotStorage storage = new SnapShotStorage();

    /**
     * Создает StringBuilder указанной емкости.
     */
    CustomStringBuilder(int capacity) {
        value = new byte[capacity];
    }

    /**
     * Конструктор без аргументов необходим для сериализации подклассов.
     */
    CustomStringBuilder() {
        value = EMPTYVALUE;
    }


    /**
     * Создает объект StringBuilder, содержащий те же символы
     * что указаны в {@code String}. Первоначальная ёмкость
     * StringBuilder - {@code 16} плюс длина аргумента {@code String}.
     *
     * @param str копируемая строка.
     */
    CustomStringBuilder(String str) {
        int length = str.length();
        int capacity = (length < Integer.MAX_VALUE - 16)
                ? length + 16 : Integer.MAX_VALUE;
        value = new byte[capacity];
        append(str);
    }

    /**
     * Создает объект StringBuilder, содержащий те же символы
     * что указаны в {@code CharSequence}. Первоначальная ёмкость
     * StringBuilder - {@code 16} плюс длина аргумента {@code CharSequence}.
     *
     * @param seq копируемая последовательность.
     */
    CustomStringBuilder(CharSequence seq) {
        int length = seq.length();
        if (length < 0) {
            throw new NegativeArraySizeException("Negative length: " + length);
        }
        int capacity = (length < Integer.MAX_VALUE - 16)
                ? length + 16 : Integer.MAX_VALUE;

        value = new byte[capacity];
        append(seq);
    }

    int compareTo(CustomStringBuilder another) {
        if (this == another) {
            return 0;
        }
        return value.length - another.value.length;
    }

    public CustomStringBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }

    /**
     * Добавляет указанную строку к имеющейся последовательности символов.
     * <p>
     * Символы аргумента {@code String}, добавляются в последовательность,
     * увеличивая длину этой последовательности на длину аргумента.
     * Если {@code str} равен {@code null}, тогда добавляются  четыре символа {@code «null»}.
     * <p>
     * Пусть <i>n</i> - длина этой последовательности символов непосредственно перед
     * выполнение метода {@code append}. Тогда символ в
     * индекс <i>k</i> в новой последовательности символов равен символу
     * при индексе <i>k</i> в старой последовательности символов, если <i>k</i> меньше
     * чем <i>n</i>;
     * в противном случае он равен символу в индексе <i>k-n</i> в аргументе {@code str}.
     *
     * @param str строка.
     * @return ссылку на этот объект.
     */
    public CustomStringBuilder append(String str) {
        if (str == null) {
            return appendNull();
        }
        int len = str.length();
        ensureCapacityInternal(count + len);
        putStringAt(count, str);
        count += len;
        return this;
    }

    private CustomStringBuilder appendNull() {
        ensureCapacityInternal(count + 4);
        int count = this.count;
        byte[] val = this.value;
        if (isLatin1()) {
            val[count++] = 'n';
            val[count++] = 'u';
            val[count++] = 'l';
            val[count++] = 'l';
        } else {
            count = StringUTF16.putCharsAt(val, count, 'n', 'u', 'l', 'l');
        }
        this.count = count;
        return this;
    }

    private void save(String str) {
        storage.save(new SnapShot(value, count));
    }

    public void undo() {
        SnapShot snapShot = storage.get();
        value = snapShot.value;
        count = snapShot.count;
    }

   /* @Override
    public String toString() {
        return  ;
    }*/

    public static class SnapShot {
        private final byte[] value;
        private final int count;

        SnapShot(byte[] value, int count) {
            this.value = value;
            this.count = count;
        }

    }
}
