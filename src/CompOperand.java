import java.util.ArrayList;

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
	
	public boolean isAlias(){
		return !this.isValue;
	}
	
	public Value getVal(){
		return this.v;
	}
	
	public ColAlias getColAlias(){
		return this.c;
	}
	
	public void validateOperand(ArrayList<Column> schema, ArrayList<Value> record, String t) throws Exception, MyException{
		if(this.isAlias()){
			if(this.getColAlias().isdotted()){
				String tn = this.getColAlias().getTbName();
				//존재하지 않는 테이블 참조 
				if(!t.equals(tn)){
					throw new MyException(Messages.WhereTableNotSpecified);
				}
			}
			String cn = this.getColAlias().getColName();
			int s = schema.size();
			int loc = -1;
			for(int i=0; i<s; ++i){
				if(schema.get(i).getName().equals(cn)){
					//column name 중복 
					if(loc != -1){
						throw new MyException(Messages.WhereAmbiguousReference);
					}
					loc = i;
				}
			}
			if(loc == -1){
				throw new MyException(Messages.WhereColumnNotExist);
			}
		}
	}
	
	
}
