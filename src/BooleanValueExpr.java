import java.util.ArrayList;
import java.util.HashMap;

public class BooleanValueExpr {
	ArrayList<BooleanTerm> or = new ArrayList<BooleanTerm>();
	
	public BooleanValueExpr(){
	};
	
	public void addToList(BooleanTerm a){
		this.or.add(a);
	}
	//evaluation of query 
	public Logic evaluate(Table t, int idx) throws Exception{
		int siz = this.or.size();
		if(siz == 0){
			throw new Exception();
		}
		Logic result = or.get(0).evaluate(t, idx);
		for(int i=1; i<siz; ++i){
			result = result.OR(this.or.get(i).evaluate(t, idx));
		}
		return result;
	}
}
