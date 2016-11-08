import java.util.ArrayList;

public class PrimaryKeyConstraint {
	private ArrayList<String> columnNameList = new ArrayList<String>();
	
	public PrimaryKeyConstraint(ArrayList<String> columnnamelist){
		this.columnNameList.addAll(columnnamelist);
	}
	
	public ArrayList<String> getColumnNameList(){
		return this.columnNameList;
	}
	
}
