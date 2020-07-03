public class Array_methods {
	public static int[] insert(int[] arr, int item, int position) {
		int[] x = new int[arr.length + 1];
		System.arraycopy(arr, 0, x, 0, position);
		x[position] = item;
		System.arraycopy(arr, position, x, position + 1, arr.length - position);
		return x;
	}
	
	public static void reverse(int[] arr) {
		int len = arr.length;
		int temp;
		for(int i = 0; i < len / 2; i += 1) {
			temp = arr[i];
			arr[i] = arr[len - i - 1];
			arr[len - i - 1] = temp;
		}
		return rep;
	}
		
	public static int[] replicate(int[] arr) {
		int[] rep = new int[0];
		int[] temp;
		for(int a: arr) {
			temp = rep;
			rep = new int[temp.length + a];
			System.arraycopy(temp, 0, rep, 0, temp.length);
			for(int i = temp.length; i < rep.length; i++) {
				rep[i] = a;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] x = new int[]{5, 9, 14, 15};
		x = insert(x, 6, 3);
		reverse(x);
		int[] arr = replicate(new int[]{3, 2 ,1});
	}
}