import java.util.Iterator;
import java.util.NoSuchElementException;

public class TYIterator extends OHIterator {
	
	public TYIterator(OHRequest queue) {
		super(queue);
	}
	
	@Override
	public OHRequest next() {
		if (!hasNext()) {
			throw new NoSuchElementException();

		}
		else if (curr.description.contains("thank u")) {
			OHRequest returnOHR = curr;
			curr = curr.next.next;
			return returnOHR;
		}
		else {
			OHRequest returnOHR = curr;
			curr = curr.next;
			return returnOHR;
		}
	}
}