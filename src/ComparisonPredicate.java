
public class ComparisonPredicate {

	CompOperand Left;
	CompOperand Right;
	String operator;
	
	public ComparisonPredicate(CompOperand a, CompOperand b, String type){
		this.Left = a; 
		this.Right = b;
		this.operator = type;
	}
	
	public CompOperand getLeft(){
		return this.Left;
	}
	
	public CompOperand getRight(){
		return this.Right;
	}
	
	public String getOperator(){
		return this.operator;
	}
}
