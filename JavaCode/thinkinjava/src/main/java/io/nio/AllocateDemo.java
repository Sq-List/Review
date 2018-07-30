package io.nio;

import java.nio.CharBuffer;

public class AllocateDemo {
    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(8);
        charBuffer.put("L");
        charBuffer.put("E");
        charBuffer.put("E");
        charBuffer.put("S");
        charBuffer.put("F");
        charBuffer.position(3);
        System.out.println(charBuffer.get());
        charBuffer.limit(6).mark().position(5);
        System.out.println(charBuffer.get());
        CharBuffer dupeBuffer = charBuffer.duplicate();
        charBuffer.clear();
        dupeBuffer.flip();
        System.out.println(dupeBuffer.position());
        System.out.println(dupeBuffer.limit());
        System.out.println(dupeBuffer.get());

        charBuffer.put("Y");
        charBuffer.put("D");
        charBuffer.flip();

        System.out.println(dupeBuffer.get());
    }
}
