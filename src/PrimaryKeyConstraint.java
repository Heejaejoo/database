import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PrimaryKeyConstraint {
	private ArrayList<String> cols = new ArrayList<String>();
	
	private boolean checkColumnDuplicate(ArrayList<String> cols){
		Set<String> set = new HashSet<String>();
		for(int i=0; i<cols.size(); ++i){
			set.add(cols.get(i));
		}
		return cols.size() != set.size();
	}
	
	public PrimaryKeyConstraint(ArrayList<String> c) throws MyException{
		//check duplicate attributes in the column list
		if(checkColumnDuplicate(c)){
			throw new MyException(Messages.DuplicateColumnDefError);
		}
		this.cols.addAll(cols);
	}
	
	public ArrayList<String> getcols(){
		return this.cols;
	}
	
	
	public void printAll(){
		System.out.println("primary key:");
		for(int i=0; i<this.cols.size(); ++i){
			System.out.println(this.cols.get(i));
		}
		System.out.println("");
	}
	
}
