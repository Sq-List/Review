package cglib.enhancer;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

public class Test {
    public static void main(String[] args) {
        testFixedValue();
    }

    public static void testFixedValue() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello CGlib";
            }
        });

        SampleClass sampleClass = (SampleClass) enhancer.create();
        System.out.println(sampleClass.test(null));
        System.out.println(sampleClass.toString());
        System.out.println(sampleClass.getClass());
        System.out.println(sampleClass.hashCode());
    }
}
