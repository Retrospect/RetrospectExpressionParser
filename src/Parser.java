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
		root = splitPlus(exp);
	}
	
	public ExpressionNode splitPlus(String subEx)
	{
		if (subEx.indexOf("+")==-1)
		{
			return splitMinus(subEx);
		}
		OperandNode temp = new OperandNode('+');
		int prevIndex =0;
		int bracketCount = 0;
		
		for (int i= 0;i<subEx.length();i++)	//Start at 1, because if the first char is a minus or a plus, it doesn't matter
		{
			
			if (subEx.charAt(i)=='+' && bracketCount == 0)
			{
				temp.elements.add(splitDiv(subEx.substring(prevIndex,i)));
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
		
		temp.elements.add(splitMinus(subEx.substring(prevIndex)));
		return temp;
	}
	
	public ExpressionNode splitMinus(String subEx)
	{
		if (subEx.indexOf("-")==-1)
		{
			return splitDiv(subEx);
		}
		OperandNode temp = new OperandNode('-');
		int prevIndex =0;
		int bracketCount = 0;
		
		for (int i= 0;i<subEx.length();i++)	//Start at 1, because if the first char is a minus or a plus, it doesn't matter
		{
			if (subEx.charAt(i)=='-' && bracketCount == 0)
			{
				temp.elements.add(splitDiv(subEx.substring(prevIndex,i)));
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
		
		for (int i=0;i<subEx.length();i++)	//Start at 1, because if the first char is a minus or a plus, it doesn't matter
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
		
		int i = 0;
		while (i<subEx.length())
		{
			
			if (subEx.charAt(i)=='*' && bracketCount == 0)
			{
				temp.elements.add(constVarFunc(subEx.substring(prevIndex,i)));
				prevIndex = i+1;
			}
			if (subEx.charAt(i)=='(')
				bracketCount+=1;
			if (subEx.charAt(i)==')')
				bracketCount-=1;
			
			i++;
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
		
		if (subEx.charAt(0)=='(' && subEx.charAt(subEx.length()-1)==')')
		{
			return splitPlus(subEx.substring(1,subEx.length()-1));
		}
		if (isDouble(subEx))
		{
			return new ConstNode(Double.parseDouble(subEx));
		}
		return new VariableNode(subEx);
	}
	
	public boolean isDouble( String input )  
	{  
	   try  
	   {  
	      Double.parseDouble( input );  
	      return true;
	   }  
	   catch (NumberFormatException a)
	   {  
	      return false;  
	   }  
	}  
	
	
	public static void main(String[] args)
	{
		String input = "";
		Scanner inReader = new Scanner(System.in);
		
		//Write textbase interface
		while (input.equalsIgnoreCase("exit") == false)
		{
			System.out.print(">> ");
			input = inReader.next();
			if (input.equalsIgnoreCase("exit"))
				break;
			//Next, decide what to do.
			if(input.indexOf("=") >0)
			{
				//Now it is is variable
			}
			else
			{
				//Just evaluate
				Parser exp = new Parser(input);
				exp.parse();
				System.out.println(exp.root.toString());
			}
		}
	}
}
