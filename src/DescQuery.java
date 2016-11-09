
public class DescQuery extends Query{
	private String tableName;
	public DescQuery(String name){
		this.tableName = name;
	}
	
	public void execute() throws MyException, Exception{
		DBManager dbman = DBManager.dbman();
	    Table table = dbman.get(this.tableName, 1);
	    if(table == null)
	        throw new MyException(Messages.NoSuchTable);
	    System.out.println("-------------------------------------------------");
	    System.out.printf("table_name [%s]\n", table.getName());
	    System.out.println("column_name			type			null			key");
	    for(Column col : table.getColumns()){
	    	String s = col.isPK()? "PK" : "";
	    	s += col.isFK()? "/FK": "";
	    	System.out.printf("%s			%s			%s			%s\n", col.getName(), col.getType().getString(), col.isNotNull()? "Y": "N", s);
	    }
	    System.out.println("-------------------------------------------------");
	}
}
