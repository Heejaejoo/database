import java.util.ArrayList;

public class CreateTableQuery {
	private String tableName;
	private ArrayList<ColumnDefinition> colDefs = new ArrayList<ColumnDefinition>();
	private ArrayList<PrimaryKeyConstraint> pkConsts = new ArrayList<PrimaryKeyConstraint>();
	private ArrayList<ReferentialConstraint> refConsts = new ArrayList<ReferentialConstraint>();
	
	public void addDefinition(String colname, int type, boolean notnull){
		this.colDefs.add(new ColumnDefinition(colname, type, notnull));
	}
	
	public void addDefinition(String colname, int type, int len, boolean notnull){
		this.colDefs.add(new ColumnDefinition(colname, type, len, notnull));
	}
	
	public void addPrimaryKeyConst(ArrayList<String> colnamelist){
		this.pkConsts.add(new PrimaryKeyConstraint(colnamelist));
	}
	
	public void addReferentialKeyConst(ArrayList<String> colnamelist, String tablename, ArrayList<String> refcolnamelist){
		this.refConsts.add(new ReferentialConstraint(colnamelist, tablename, refcolnamelist));
	}
	
	public String getTableName(){
		return this.tableName;
	}
	
	public CreateTableQuery(String name){
		this.tableName = name;
	}
	
	
}
