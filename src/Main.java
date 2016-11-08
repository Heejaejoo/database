import java.util.ArrayList;

public class Main {
	  public static void main(String args[]) throws ParseException
	  {
	    sqlparser parser = new sqlparser(System.in);
	    System.out.print("DB_2009-13389> ");
	    ArrayList<String> parsedResult = new ArrayList();
	    
	    while (true)
	    {
	      try
	      {
	        parser.command();
	      }catch (MyException e){
	    	  System.out.println(e.getMessage());
	    	  e.printStackTrace();
	    	  sqlparser.ReInit(System.in);
	      }
	      catch (Exception e)
	      {
	    	System.out.println(e.getMessage());
	    	e.printStackTrace();
	        parser.printMessage(0);
	        parser.ReInit(System.in);
	      }
	    }
	  }
}
