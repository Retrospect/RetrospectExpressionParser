
public class ParserError extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String d;
	
	public ParserError (String description)
	{
		d = description;
	}

	public String getDescription()
	{
		return d;
	}
}
