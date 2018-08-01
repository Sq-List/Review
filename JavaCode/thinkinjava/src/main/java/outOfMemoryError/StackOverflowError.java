package outOfMemoryError;

public class StackOverflowError {
    static class Stack {
        private int stackLength = 1;

        public void leak() {
            stackLength++;
            leak();
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();

        try {
            stack.leak();
        } catch (Throwable e) {
            System.out.println(stack.stackLength);
            throw e;
        }
    }
}
