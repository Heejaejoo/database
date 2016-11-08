import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class ReferentialConstraint {
	private ArrayList<String> columnNameList = new ArrayList<String>();
	private String referencedTable;
	private ArrayList<String> refColumnNameList = new ArrayList<String>();
	
	private boolean checkColumnDuplicate(ArrayList<String> cols){
		Set<String> set = new HashSet<String>();
		for(int i=0; i<cols.size(); ++i){
			set.add(cols.get(i));
		}
		return cols.size() != set.size();
	}
	
	public ReferentialConstraint(ArrayList<String> columnnamelist, String referenced, ArrayList<String> referencedList) throws MyException{
		//referenced column name duplicate check
		if(checkColumnDuplicate(columnnamelist)){
			throw new MyException(Messages.DuplicateColumnDefError);
		}
		//referenced column name duplicate check
		if(checkColumnDuplicate(referencedList)){
			throw new MyException(Messages.DuplicateColumnDefError);
		}
		this.columnNameList.addAll(columnnamelist);
		this.referencedTable = referenced;
		this.refColumnNameList.addAll(referencedList);
	}
	
	public ArrayList<String> getColumnNameList(){
		return this.columnNameList;
	}
	
	public String getReferencedTableName(){
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
