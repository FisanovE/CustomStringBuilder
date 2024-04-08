import builders.CustomStringBuilder;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {

        CustomStringBuilder builder_v2 = new CustomStringBuilder();
        builder_v2.append("one");
        out.println(builder_v2);
        builder_v2.append(" two");
        out.println(builder_v2);
        builder_v2.undo();
        out.println(builder_v2);

    }
}