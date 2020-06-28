public class TestBody{
	public static void main(String[] args){
		Body a = new Body(0, 0, 0, 0, 1, "a");
		Body b = new Body(1, 0, 0, 0, 2, "b");
		System.out.println(a.calcForceExertedBy(b));
	}
}