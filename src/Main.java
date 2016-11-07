
public class Main {
	  public static void main(String args[]) throws ParseException
	  {
	    sqlparser parser = new sqlparser(System.in);
	    System.out.print("DB_2009-13389> ");

	    while (true)
	    {
	      try
	      {
	        parser.command();
	      }
	      catch (Exception e)
	      {
	        parser.printMessage(0);
	        sqlparser.ReInit(System.in);
	      }
	    }
	  }
}
