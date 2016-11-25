
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
	}
	
	public boolean ispredicate(){
		return this.ispredicate;
	}
	
	
}
