package outOfMemoryError;

import java.util.ArrayList;
import java.util.List;

public class Perm {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        for (;;) {
            String s = (i++) + "aaaa";
            list.add(s.intern());
        }
    }
}
