import java.util.ArrayList;

public class PrimaryKeyConstraint {
	private ArrayList<String> columnNameList = new ArrayList<String>();
	
	public PrimaryKeyConstraint(ArrayList<String> columnnamelist){
		this.columnNameList.addAll(columnnamelist);
	}
	
	public ArrayList<String> getColumnNameList(){
		return this.columnNameList;
	}
	
	public void printAll(){
		System.out.println("primary key:");
		for(int i=0; i<this.columnNameList.size(); ++i){
			System.out.println(this.columnNameList.get(i));
		}
		System.out.println("");
	}
	
}
