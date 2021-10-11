
/**
 * 
 * @author marciomoreira  nr - 41972
 *
 * @param <E> Abstract type ADT
 */
public interface Deque<E> {

	public void addFront ( E e );
	public void delFront ();

	public void addEnd ( E e );
	public void delEnd ();

	public E front ();
	public E end ();
}
