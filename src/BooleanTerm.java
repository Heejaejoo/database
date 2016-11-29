import java.util.ArrayList;

public class BooleanTerm {
	ArrayList<BooleanFactor> and = new ArrayList<BooleanFactor>();
	
	public BooleanTerm(){
	};
	
	public void addToList(BooleanFactor a){
		this.and.add(a);
	}
	
	public Logic evaluate(Table t, int idx, boolean e) throws Exception, MyException{
		int siz = this.and.size();
		if(siz ==0){
			throw new Exception();
		}
		Logic result = this.and.get(0).evaluate(t, idx, e);
 
		for(int i=1; i<siz; ++i){
			result = result.AND(this.and.get(i).evaluate(t, idx, e));
		}
		return result;
	}	
}
