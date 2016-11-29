
public class Predicate {
	boolean iscomp = true;
	ComparisonPredicate c;
	NullPredicate n;
	
	public Predicate(ComparisonPredicate c){
		this.c = c;
	}
	
	public Predicate(NullPredicate n){
		this.n = n;
		this.iscomp = false;
	}
	
	public ComparisonPredicate getCompPred(){
		return this.c;
	}
	
	public NullPredicate getNullPred(){
		return this.n;
	}
	
	public boolean iscomp(){
		return this.iscomp;
	}
	
	public Logic evaluate(Table t, int idx, boolean empty) throws Exception, MyException{
		if(this.iscomp){
			return c.evaluate(t, idx, empty);
		}else{
			return n.evaluate(t, idx, empty);
		}
	}
}
