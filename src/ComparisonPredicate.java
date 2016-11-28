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
	
	private void validateOperand(ArrayList<Column> schema, ArrayList<Value> record, String tbname) throws Exception, MyException{
		this.Left.validateOperand(schema, record, tbname);
		this.Right.validateOperand(schema, record, tbname);
	}
	
	public Logic evaluate(Table t, int idx) throws Exception, MyException{
		ArrayList<Value> record = t.getEntries().get(idx);
		ArrayList<Column> schema = t.getColumns(); 
		validateOperand(schema, record, t.getName());
		int siz = record.size();
		if(this.Left.isAlias() && this.Right.isAlias()){
			Value v; 
			Column c;
			int lidx=-1, ridx=-1;
			String lname = this.getLeft().getColAlias().getColName();
			String rname = this.getRight().getColAlias().getColName();
			
			//colname만 같으면 된다고가정한다.
			//두개의 이름이 같으면 항상 true
			if(lname.equals(rname)){
				return new Logic(0);
			}
			
			for(int i=0; i<siz; ++i){
				v = record.get(i);
				c = schema.get(i);
				if(c.getName().equals(lname)){
					lidx = i;
				}else if(c.getName().equals(rname))
				{
					ridx = i;
				}
			}
			
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
		}else if(this.Left.isValue() && this.Right.isValue()){
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
			if(this.Left.isValue()){
				v = this.getLeft().getVal();
				c = this.getRight().getColAlias();
			}else{
				leftv = false;
				v = this.getRight().getVal();
				c = this.getLeft().getColAlias();
			}
			//col name이 같으면? 
			String cname = c.getColName();
			int indexx =-1;
			for(int i=0; i<siz; ++i){
				Column col = schema.get(i);
				if(col.getName().equals(cname)){
					indexx = i;
				}
			}
			 
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
