package Tools;

public class PW_Integer {
	private int integer;
	
	public PW_Integer(int value)
	{
		integer = value;
	}
	
	public int getValue(){ return integer; }
	
	public void or(int other)
	{
		integer = integer | other; 
	}
}
