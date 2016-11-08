
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
	public void printAll(){
		System.out.println("ColumnDefinition");
		System.out.printf("Name: %s\n", this.columnName);
		this.type.printAll();
		if(this.notNull){
			System.out.printf("not null\n");
		}
	}
	
	public ColumnDefinition(String name, DataType type, boolean notnull){
		this.columnName = name;
		this.type = type;
		this.notNull = notnull;
	}
}
