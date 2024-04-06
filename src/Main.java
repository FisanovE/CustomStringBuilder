import builders.CustomStringBuilder_v1;
import builders.CustomStringBuilder_v2;
import builders.CustomStringBuilder_v3;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        CustomStringBuilder_v1 builder_v1 = new CustomStringBuilder_v1();
        builder_v1.append("one");
        out.println(builder_v1);
        builder_v1.append(" two");
        out.println(builder_v1);
        builder_v1.undo();
        out.println(builder_v1);

        CustomStringBuilder_v2 builder_v2 = new CustomStringBuilder_v2();
        builder_v2.append("one");
        out.println(builder_v2);
        builder_v2.append(" two");
        out.println(builder_v2);
        builder_v2.undo();
        out.println(builder_v2);

        CustomStringBuilder_v3 builder_v3 = new CustomStringBuilder_v3();
        builder_v3.append("one");
        out.println(builder_v3);
        builder_v3.append(" two");
        out.println(builder_v3);
        builder_v3.undo();
        out.println(builder_v3);
    }
}