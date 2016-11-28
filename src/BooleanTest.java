
public class BooleanTest {
	private boolean ispredicate = true;
	private Predicate pred;
	private BooleanValueExpr expr;
	//Value expr needs to be implemented
	
	public BooleanTest(Predicate pred){
		this.pred = pred;
	}
	
	public BooleanTest(BooleanValueExpr exp){
		this.expr = exp;
		this.ispredicate = false;
	}
	
	public boolean isPredicate(){
		return this.ispredicate;
	}
	public Logic evaluate(Table t, int idx) throws Exception, MyException{
		if(this.isPredicate()){
			return this.pred.evaluate(t, idx);
		}else
		{
			return this.expr.evaluate(t, idx);
		}
	}
	
}
