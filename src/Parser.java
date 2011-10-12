import java.util.Scanner;



public class Parser
{
	String exp ="";
	ExpressionNode root;
	
	public Parser(String expression)
	{
		exp = expression;
	}
	
	public void parse() throws ParserError
	{
		root = splitPlus(exp);
	}
	
	public ExpressionNode splitPlus(String subEx) throws ParserError
	{
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
			throw new ParserError("Error in number of brackets!");
		}
		if (prevIndex > 0)
		{
			temp.elements.add(splitMinus(subEx.substring(prevIndex)));
			return temp;
		}
		else
		{
			return splitMinus(subEx);
		}
	}
	
	public ExpressionNode splitMinus(String subEx) throws ParserError
	{
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
			throw new ParserError("Error in number of brackets!");
		}
		
		if (prevIndex > 0)
		{
			temp.elements.add(splitDiv(subEx.substring(prevIndex)));
			return temp;
		}
		else
		{
			return splitDiv(subEx);
		}
	}
	
	public ExpressionNode splitDiv(String subEx) throws ParserError
	{
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
			throw new ParserError("Error in number of brackets!");
		}
		if (prevIndex > 0)
		{
			temp.elements.add(splitMulti(subEx.substring(prevIndex)));
			return temp;
		}
		else
		{
			return splitMulti(subEx);
		}
	}
	
	public ExpressionNode splitMulti (String subEx) throws ParserError
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
			throw new ParserError("Error in number of brackets!");
		}
		if (prevIndex > 0)
		{
			temp.elements.add(constVarFunc(subEx.substring(prevIndex)));
			return temp;
		}
		else
		{
			return constVarFunc(subEx);
		}
	}
	
	public ExpressionNode constVarFunc(String subEx) throws ParserError
	{
		if (subEx.charAt(0)=='(' && subEx.charAt(subEx.length()-1)==')')
		{
			return splitPlus(subEx.substring(1,subEx.length()-1));
		}
		if (isDouble(subEx))
		{
			return new ConstNode(Double.parseDouble(subEx));
		}
		if (subEx.indexOf('(') > -1)
		{
			//Attempt to recognize as a function
			int bracket1 = subEx.indexOf('(');
			int bracket2 = subEx.lastIndexOf(')');
			return new FunctionNode(subEx.substring(0,bracket1),splitPlus(subEx.substring(bracket1+1,bracket2)));
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
				try
				{
					exp.parse();
					System.out.println(exp.root.toString());
				}
				catch (ParserError e)
				{
					System.out.println(e.getDescription());
				}

			}
		}
	}
}
