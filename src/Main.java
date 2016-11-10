import java.util.ArrayList;

public class Main {
	  public static void main(String args[]) throws ParseException, Exception, MyException
	  {
	    sqlparser parser = new sqlparser(System.in);
	    System.out.print("DB_2009-13389> ");
	    while (true)
	    {
	      try
	      {
	        parser.command();
	      }catch (MyException e){
	    	  System.out.println(e.getMessage());
	   // 	  e.printStackTrace();
	    	  sqlparser.ReInit(System.in);
			  System.out.print("DB_2009-13389> ");
	      }
	      catch (ParseException e)
	      {
	    //	System.out.println(e.getMessage());
	   // 	e.printStackTrace();
	        System.out.println("Syntax error");
		    System.out.print("DB_2009-13389> ");
	        parser.ReInit(System.in);
	      }catch (Exception e){
	    	//  e.printStackTrace();
	    	  System.out.println("Unhandled Exception");
			  System.out.print("DB_2009-13389> ");
	    	  parser.ReInit(System.in);
	      }
	    }
	  }
}
