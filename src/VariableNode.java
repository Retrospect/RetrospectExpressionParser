
public class VariableNode extends ExpressionNode
{
	String name ="";
	ExpressionNode value;
	
	public VariableNode (String n)
	{
		name = n;
	}
	
	public VariableNode(String n,ExpressionNode v)
	{
		name = n;
		value = v;
	}
	
	public String toString()
	{
		return name;
	}
	
	public double evaluate()
	{
		return value.evaluate();
	}
}
