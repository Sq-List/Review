package InnerClass;

import org.junit.Test;

public class MemberInnerClassTest {
    @Test
    public void test() {
        MemberOuterClass outerClass = new MemberOuterClass();
        MemberOuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.test();

        // 错误
        // OuterClass.InnerClass innerClass1 = new OuterClass.InnerClass();
    }
}
