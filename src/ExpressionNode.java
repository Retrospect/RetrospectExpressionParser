<<<<<<< HEAD
import java.util.Vector;


public class ExpressionNode
{
	public Vector<ExpressionNode> elements;
	
	public ExpressionNode()
	{
		elements = new Vector<ExpressionNode>();
	}
	
	public String toString()
	{
		String StringRep = "";
		for (int i=0;i<elements.size();i++)
			StringRep = StringRep + (elements.elementAt(i).toString());
		return StringRep;
	}
	
	public double evaluate()
	{
		return 0;
	}
=======
/**
 * 
 */

/**
 *
 */
interface ExpressionNode
{
	public String toString();
>>>>>>> a51bc8828159e0d3ac48abe2d513b6e444d6df28
}
