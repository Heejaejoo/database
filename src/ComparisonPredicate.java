import java.util.ArrayList;

public class ComparisonPredicate {

	CompOperand Left;
	CompOperand Right;
	String operator;
	
	public ComparisonPredicate(CompOperand a, CompOperand b, String type){
		this.Left = a; 
		this.Right = b;
		this.operator = type;
//		if(this.Left.isValue()){
//			System.out.println(this.Left.getVal().toString());
//		}else{
//			System.out.println(this.Left.getColAlias().getColName());
//		}
//		if(this.Right.isValue()){
//			System.out.println(this.Right.getVal().toString());
//		}else{
//			System.out.println(this.Right.getColAlias().getColName());
//			System.out.println(this.Right.getColAlias().getTbName());
//		}
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
	private int intl;
	private int intr;
	private void validateOperand(ArrayList<Column> schema,  ArrayList<String> tbname) throws Exception, MyException{
		intl = this.Left.validateOperand(schema, tbname);
		intr = this.Right.validateOperand(schema,  tbname);
	}
	
	public Logic evaluate(Table t, int idx, boolean empty) throws Exception, MyException{
		ArrayList<Value> record = t.getEntries().get(idx);
		ArrayList<Column> schema = t.getColumns(); 
		validateOperand(schema, t.getTableNameList());
		if(empty){
			return new Logic(0);
		}
		// if passed the test, all Column defs are present 
		if(intl>=0 && intr>=0){
			int lidx=intl, ridx=intr;
			Value lv = record.get(lidx);
			Value rv = record.get(ridx);
			if(lv.isNull() || rv.isNull()){
				return new Logic(2);
			}else{
				if(lv.cmp(rv,this.operator)){
					return new Logic(0);
				}else{
					return new Logic(1);
				}
			}
		}else if(intl == -1 && intr == -1){
			//no reason to evaluate all records, just conclude whether it is true
			if(this.Left.getVal().isNull() || this.Right.getVal().isNull()){
				return new Logic(2);
			}else{
				if(this.Left.getVal().cmp(this.Right.getVal(), this.operator)){
					return new Logic(0);
				}else{
					return new Logic(1);
				}
			}
		}else{
			//둘중 하나가 value 
			Value v;
			ColAlias c;
			//왼쪽이 v다. 
			boolean leftv = true;
			if(intl == -1){
				v = this.getLeft().getVal();
				c = this.getRight().getColAlias();
			}else{
				leftv = false;
				v = this.getRight().getVal();
				c = this.getLeft().getColAlias();
			}
			//col name이 같으면? 
			int indexx = intl == -1? intr: intl;
			Value val = record.get(indexx);
			if(v.isNull() || val.isNull()){
				return new Logic(2);
			}else{
				if(leftv){
					if(v.cmp(val, this.operator)){
						return new Logic(0);
					}else{
						return new Logic(1);
					}
				}else{
					if(val.cmp(v, this.operator)){
						return new Logic(0);
					}else{
						return new Logic(1);
					}
				}
			}
		}
	}
	
	
}
