import java.util.ArrayList;

public class BooleanValueExpr {
	ArrayList<BooleanTerm> list = new ArrayList<BooleanTerm>();
	
	public BooleanValueExpr(){
	};
	
	public void addToList(BooleanTerm a){
		this.list.add(a);
	}
}
