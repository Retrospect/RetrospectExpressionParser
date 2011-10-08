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
}
