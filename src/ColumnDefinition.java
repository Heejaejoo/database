
public class ColumnDefinition {
	private String columnName;
	private DataType type;
	private boolean notNull;
	
	public String getName(){
		return this.columnName;
	}
	
	public DataType getType(){
		return this.type;
	}
	
	public boolean isNotNull(){
		return this.notNull;
	}
	
	public ColumnDefinition(String name, int type, boolean notnull){
		this.columnName = name;
		this.type = new DataType(type);
		this.notNull = notnull;
		
	}
	
	public ColumnDefinition(String name, int type, int len, boolean notnull){
		this.columnName = name;
		this.type = new DataType(type, len);
		this.notNull = notnull;
	}
}
