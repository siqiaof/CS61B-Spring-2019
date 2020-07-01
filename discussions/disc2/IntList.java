public class IntList{
	public int first;
	public IntList rest;
	
	public IntList(int f, IntList r){
		first = f;
		rest = r;
	}
	
	public static IntList square(IntList L){
		if (L.rest == null){
			return new IntList(L.first * L.first, null);
		}
		return new IntList(L.first * L.first, square(L.rest));
	}

	public static IntList squareDestructive(IntList L){
		if (L.rest == null){
			L.first = L.first * L.first;
			return L;
		}
		L.first = L.first * L.first;
		L.rest = squareDestructive(L.rest);
		return L;
	}
	
	public static void main(String[] args){
		IntList L = new IntList(3, null);
		L = new IntList(2, L);
		L = new IntList(1, L);
		System.out.println(square(L).rest.first);
		System.out.println(L.rest.first);
		System.out.println(squareDestructive(L).rest.first);
		System.out.println(L.rest.first);
	}
}