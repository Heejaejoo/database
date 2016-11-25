
public class CompOperand {
	boolean isValue;
	Value v;
	ColAlias c;
	
	public CompOperand(Value v){
		this.isValue = true;
		this.v = v;
	}
	
	public CompOperand(ColAlias c){
		this.isValue = false;
		this.c = c;
	}
	
	public boolean isValue(){
		return this.isValue;
	}
	
	public Value getVal(){
		return this.v;
	}
	
	public ColAlias getColAlias(){
		return this.c;
	}
}
