
<<<<<<< HEAD
public class OperandNode extends ExpressionNode
{
	char operator;
	
	public OperandNode(char op)
	{
		operator = op;
	}
	
	public double evaluate()
	{
		double ammount = 1;

		if (operator=='+')
		{
			ammount = 0;
			for (int i=0;i<elements.size();i++)
				ammount+=elements.elementAt(i).evaluate();
		}
		
		if (operator=='*')
		{
			ammount = 1;
			for (int i=0;i<elements.size();i++)
				ammount= ammount*elements.elementAt(i).evaluate();
		}
		if (operator=='/')
		{
			ammount = elements.elementAt(0).evaluate();
			for (int i=1;i<elements.size();i++)
				ammount = ammount/elements.elementAt(i).evaluate();
		}
		return ammount;
=======
public class OperandNode implements ExpressionNode
{
	char operator;
	
	public OperandNode()
	{
		operator ='+';
>>>>>>> a51bc8828159e0d3ac48abe2d513b6e444d6df28
	}
	
	public String toString()
	{
<<<<<<< HEAD
		String StringRep = operator+"[";
		for (int i=0;i<elements.size()-1;i++)
			StringRep = StringRep + (elements.elementAt(i).toString())+", ";
		StringRep = StringRep + (elements.elementAt(elements.size()-1).toString());
		return StringRep+"]";
=======
		return "";
>>>>>>> a51bc8828159e0d3ac48abe2d513b6e444d6df28
	}
	
	
}
