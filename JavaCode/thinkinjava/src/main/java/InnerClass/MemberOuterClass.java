package InnerClass;

/**
 * 成员内部类
 */
public class MemberOuterClass {
	private Integer count1 = 1;
	private Integer count2 = 2;

	// 成员内部类
	class InnerClass {
		private Integer count2 = 3;

		public void test() {
			// 访问外部类的count1
			System.out.println(count1);
			// 内外类属性同名，访问外部类的count2
			System.out.println(MemberOuterClass.this.count2);
			// 访问内部类的count2
			System.out.println(count2);
		}
	}
}
