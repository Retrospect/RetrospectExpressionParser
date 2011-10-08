
<<<<<<< HEAD
public class ConstNode extends ExpressionNode
=======
public class ConstNode implements ExpressionNode
>>>>>>> a51bc8828159e0d3ac48abe2d513b6e444d6df28
{
	double value;
	
	public ConstNode(double num)
	{
		value = num;
	}
	
	public String toString()
	{
<<<<<<< HEAD
		return Double.toString(value);
	}
	
	public double evaluate()
	{
		return value;
=======
		return "";
>>>>>>> a51bc8828159e0d3ac48abe2d513b6e444d6df28
	}
}
