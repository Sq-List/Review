package io.nio;

import java.nio.CharBuffer;

public class BufferDemo {
    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(5);
        charBuffer.put("H");
        charBuffer.put("E");
        charBuffer.put("L");
        charBuffer.put("L");
        charBuffer.put("O");

        charBuffer.flip();
        while (charBuffer.hasRemaining()) {
            System.out.print(charBuffer.get());
        }
    }
}
