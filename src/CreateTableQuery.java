import java.util.ArrayList;

public class CreateTableQuery {
	private String tableName;
	private ArrayList<ColumnDefinition> colDefs = new ArrayList<ColumnDefinition>();
	private ArrayList<PrimaryKeyConstraint> pkConsts = new ArrayList<PrimaryKeyConstraint>();
	private ArrayList<ReferentialConstraint> refConsts = new ArrayList<ReferentialConstraint>();
	
	public void addDefinition(ColumnDefinition t){
		this.colDefs.add(t);
	}
	
	public void addPrimaryKeyConst(PrimaryKeyConstraint a){
		this.pkConsts.add(a);
	}
	
	public void addReferentialKeyConst(ReferentialConstraint b){
		this.refConsts.add(b);
	}
	
	public String getTableName(){
		return this.tableName;
	}
	
	public void printAll(){
		System.out.printf("Table name: %s\n", this.tableName);
		for(int i=0; i<this.colDefs.size(); ++i){
			this.colDefs.get(i).printAll();
		}
		for(int i=0; i<this.pkConsts.size(); ++i){
			this.pkConsts.get(i).printAll();
		}
		for(int i=0; i<this.refConsts.size(); ++i){
			this.refConsts.get(i).printAll();
		}
	}
	
	public CreateTableQuery(String name){
		this.tableName = name;
	}
	
	
}
