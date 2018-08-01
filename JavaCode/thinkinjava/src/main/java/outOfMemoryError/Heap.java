package outOfMemoryError;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    static class OOMObject {
        private Integer old;
        private String name;

        public OOMObject() {

        }

        public OOMObject(Integer old, String name) {
            this.old = old;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject(10, "test"));
        }
    }
}
