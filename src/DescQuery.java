
public class DescQuery extends Query{
	private String tableName;
	public DescQuery(String name){
		this.tableName = name;
	}
	public void execute() throws MyException, Exception{
		DBManager dbman = DBManager.dbman();
	    Table table = dbman.get(this.tableName, 2);
	    System.out.println("-------------------------------------------------");
	    System.out.printf("table_name [%s]\n", table.getName());
    	System.out.format("%32s%10s%16s%16s\n", "column_name", "type", "null", "key");
	    for(Column col : table.getColumns()){
	    	String s = "";
	    	if(col.isPK()){
	    		s = "PRI";
	    		if(col.isFK()){
		    		s += "/FOR";
		    	}
	    	}else{
	    		if(col.isFK()){
	    			s = "FOR";
	    		}
	    	}
	    	System.out.format("%32s%10s%16s%16s\n", col.getName(), col.getType().getString(), col.isNotNull()? "N": "Y", s);
	    }
	    System.out.println("-------------------------------------------------");
	}
}
