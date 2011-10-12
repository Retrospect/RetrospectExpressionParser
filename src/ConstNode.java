
public class ConstNode extends ExpressionNode

{
	double value;
	
	public ConstNode(double num)
	{
		value = num;
	}
	
	public String toString()
	{
		return Double.toString(value);
	}
	
	public double evaluate()
	{
		return value;
	}
}
