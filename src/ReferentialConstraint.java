import java.util.ArrayList;
public class ReferentialConstraint {
	private ArrayList<String> columnNameList = new ArrayList<String>();
	private String referencedTable;
	private ArrayList<String> refColumnNameList = new ArrayList<String>();
	
	public ReferentialConstraint(ArrayList<String> columnnamelist, String referenced, ArrayList<String> referencedList){
		this.columnNameList.addAll(columnnamelist);
		this.referencedTable = referenced;
		this.refColumnNameList.addAll(referencedList);
	}
	
	public ArrayList<String> getColumnNameList(){
		return this.columnNameList;
	}
	
	public String getReferencedTable(){
		return this.referencedTable;
	}
	
	public ArrayList<String> getRefColumnNameList(){
		return this.refColumnNameList;
	}
	public void printAll(){
		System.out.println("foreign key:");
		for(int i=0; i<this.columnNameList.size(); ++i){
			System.out.println(this.columnNameList.get(i));
		}
		System.out.printf("Referenced table %s\n", this.referencedTable);
		for(int i=0; i<this.refColumnNameList.size(); ++i){
			System.out.println(this.refColumnNameList.get(i));
		}
	}
}
