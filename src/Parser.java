import java.util.Scanner;



public class Parser
{
	String exp ="";
	ExpressionNode root;
	
	public Parser(String expression)
	{
		exp = expression;
	}
	
	public void parse()
	{
		root = splitPlusMinus(exp);
	}
	
	public ExpressionNode splitPlusMinus(String subEx)
	{
		if (subEx.indexOf("+")==-1 && subEx.indexOf("-")==-1)
		{
			return splitDiv(subEx);
		}
		OperandNode temp = new OperandNode('+');
		int prevIndex =0;
		int bracketCount = 0;
		
		for (int i= 1;i<subEx.length();i++)	//Start at 1, because if the first char is a minus or a plus, it doesn't matter
		{
			
			if (subEx.charAt(i)=='+' && bracketCount == 0)
			{
				temp.elements.add(splitDiv(subEx.substring(prevIndex,i)));
				prevIndex = i+1;
			}
			if (subEx.charAt(i)=='-' && bracketCount == 0)
			{
				temp.elements.add(splitDiv(subEx.substring(prevIndex,i)));
				prevIndex = i;
			}
			if (subEx.charAt(i)=='(')
				bracketCount+=1;
			if (subEx.charAt(i)==')')
				bracketCount-=1;
		}
		
		if (bracketCount!=0)
		{
			System.out.println("Error in number of brackets!");
		}
		
		temp.elements.add(splitDiv(subEx.substring(prevIndex)));
		return temp;
	}
	
	public ExpressionNode splitDiv(String subEx)
	{
		if (subEx.indexOf("/")==-1)
		{
			return splitMulti(subEx);
		}
		OperandNode temp = new OperandNode('/');	//Since this method is called directly after + and - separation, I assume everything should be a product
		int prevIndex =0;
		int bracketCount = 0;
		
		for (int i= 1;i<subEx.length();i++)	//Start at 1, because if the first char is a minus or a plus, it doesn't matter
		{
			
			if (subEx.charAt(i)=='/' && bracketCount == 0)
			{
				temp.elements.add(splitMulti(subEx.substring(prevIndex,i)));
				prevIndex = i+1;
			}
			if (subEx.charAt(i)=='(')
				bracketCount+=1;
			if (subEx.charAt(i)==')')
				bracketCount-=1;
		}
		
		if (bracketCount!=0)
		{
			System.out.println("Error in number of brackets!");
		}
		
		temp.elements.add(splitMulti(subEx.substring(prevIndex)));
		return temp;
	}
	
	public ExpressionNode splitMulti (String subEx)
	{
		if (subEx.indexOf("*")==-1)
		{
			return constVarFunc(subEx);
		}
		OperandNode temp = new OperandNode('*');	//Since this method is called directly after + and - separation, I assume everything should be a product
		int prevIndex =0;
		int bracketCount = 0;
		
		for (int i= 0;i<subEx.length();i++)	//Start at 1, because if the first char is a minus or a plus, it doesn't matter
		{
			
			if (subEx.charAt(i)=='*' && bracketCount == 0)
			{
				System.out.println("Sub:" +subEx.substring(prevIndex,i));
				temp.elements.add(constVarFunc(subEx.substring(prevIndex,i)));
				prevIndex = i+1;
			}
			//if (subEx.charAt(i)=='(')
			//{
				//read until the last bracket is found.
				//while (bracketCount>0 && i<subEx.length())
				//{
					//i++;
					if(subEx.charAt(i) =='(')
						bracketCount++;
					if (subEx.charAt(i) == ')')
						bracketCount--;
				//}
			//}
			
			
			//i++;
		}
		
		if (bracketCount!=0)
		{
			System.out.println("Error in number of brackets!");
		}
		
		temp.elements.add(constVarFunc(subEx.substring(prevIndex)));
		return temp;
	}
	
	public ExpressionNode constVarFunc(String subEx)
	{
		return new ConstNode(Double.parseDouble(subEx));
	}
	
	
	public static void main(String[] args)
	{
		String expression = "100/25+14*2-8";
		System.out.println(expression);
		Parser x = new Parser(expression);
		x.parse();
		System.out.println(x.root);
		System.out.println(x.root.evaluate());
	}
	
	
}
