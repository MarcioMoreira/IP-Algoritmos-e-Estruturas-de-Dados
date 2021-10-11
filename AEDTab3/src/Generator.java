/**
 * 
 * @author marciomoreira 41972
 *
 * @param <E>
 */
public interface Generator<E> {

	public void reset ();
	// @pre : generator not at first value
	public Long prev ();

	public Long next ();

}
