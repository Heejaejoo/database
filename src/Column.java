import java.io.Serializable;
import java.util.ArrayList;

public class Column implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3723635484032957157L;
	private String columnName;
	private DataType type;
	private boolean notNull;
	private boolean PK = false;
	private boolean FK = false;
	private ArrayList<String> referingTable = new ArrayList<String> ();
	private ArrayList<String> referingColumn = new ArrayList<String> (); 
	
	public String getName(){
		return this.columnName;
	}
	
	public DataType getType(){
		return this.type;
	}
	
	public boolean isNotNull(){
		return this.notNull;
	}
	public void setNotNull(){
		this.notNull = true;
	}
	
	public void printAll(){
		System.out.println("ColumnDefinition");
		System.out.printf("Name: %s\n", this.columnName);
		this.type.printAll();
		if(this.notNull){
			System.out.printf("not null\n");
		}
	}
	
	public boolean isPK(){
		return this.PK;
	}
	
	public void setPK(){
		this.PK = true;
	}
	
	public boolean isFK(){
		return this.FK;
	}
	
	public void setFK(){
		this.FK = true;
	}
	public void setReftb(String tbname){
		this.referingTable.add(tbname);
	}
	
	public void setRefcol(String colname){
		this.referingColumn.add(colname);
	}
	
	public ArrayList<String> getReftb(){
		return this.referingTable;
	}
	
	public ArrayList<String> getRefcol(){
		return this.referingColumn;
	}
	

	public Column(String name, DataType type, boolean notnull){
		this.columnName = name;
		this.type = type;
		this.notNull = notnull;
	}
	
	public boolean equals(Column another){
		if(this.columnName.equals(another.columnName)){
			return this.type.equals(another.type);
		}else{
			return false;
		}
	}
}
