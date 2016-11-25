import java.util.ArrayList;

public class BooleanTerm {
	ArrayList<BooleanFactor> list = new ArrayList<BooleanFactor>();
	
	public BooleanTerm(){
	};
	
	public void addToList(BooleanFactor a){
		this.list.add(a);
	}
}
