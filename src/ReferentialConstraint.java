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

}
