
public class FunctionNode extends ExpressionNode
{
	char operator;
	ExpressionNode node;
	
	public FunctionNode(String func,ExpressionNode a)
	{
		if (func.equalsIgnoreCase("Sin"))
		{
			operator = 's';
		}
		else
		if (func.equalsIgnoreCase("Cos"))
		{
			operator = 'c';
		}
		node  = a;
	}
	
	public double evaluate()
	{
		if (operator == 's')
		{
			return Math.sin(node.evaluate());
		}
		if (operator == 'c')
		{
			return Math.cos(node.evaluate());
		}
		
		return 0;		
	}
	
	public String toString()
	{
		String StringRep="";
		if (operator =='s')
			StringRep = "Sin";
		else
			if (operator =='c')
				StringRep = "Cos";
		StringRep = StringRep + "[" + node.toString();
		return StringRep+"]";
	}
}