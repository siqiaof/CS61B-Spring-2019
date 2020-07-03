public class SLList {
	
	private class IntNode {
		public int item;
		public IntNode next;
		public IntNode(int item, IntNode next) {
			this.item = item;
			this.next = next;
		}
	}

	private IntNode first;

	public SLList(int x) {
		first = new IntNode(x, null);
	}
	
	public void addFirst(int x) {
		first = new IntNode(x, first);
	}
	
	public void insert(int item, int position) {
		if (position == 0) {
			addFirst(item);
		}
		else {
			IntNode p = first;
			while (position != 1) {
				if (p.next == null) {
					break;
				}
				p = p.next;
				position -= 1;
			}
			p.next = new IntNode(item, p.next);
		}
	}
	
	public void reverse() {
		IntNode p = first;
		IntNode rev = null;
		while (p != null) {
			rev = new IntNode(p.item, rev);
			p = p.next;
		}
		first = rev;
	}
	
	public IntNode helper(IntNode origin, IntNode rev) {
		if (origin == null) {
			return rev;
		}
		else {
			return helper(origin.next, new IntNode(origin.item, rev));
		}
	}
	
	public void reverse_rec() {
		first = helper(first, null);
	}
	
	public static void main(String[] args) {
		SLList L = new SLList(2);
		L.addFirst(6);
		L.addFirst(5);
		L.reverse();
		L.reverse_rec();
	}
}