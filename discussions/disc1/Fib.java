public class Fib{
	public static int fib(int n) {
		if (n <= 1) {return n;}
		else {return fib(n - 1) + fib(n - 2);}
	}
	
	public static int fib2(int n, int k, int f0, int f1) {
		if (n == k) {return f0;}
		else {return fib2(n, k + 1, f1, f0 + f1);}
	}
	
	public static void main(String[] args){
		for (int i = 0; i < 10; i = i + 1) {
			System.out.print(fib(i)+" ");
		}
		System.out.println();
		for (int i = 0; i < 10; i = i + 1) {
			System.out.print(fib2(i, 0, 0, 1)+" ");
		}
	}
}