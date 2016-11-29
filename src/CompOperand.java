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
	
	public int validateOperand(ArrayList<Column> schema, ArrayList<String> tbnamelist) throws Exception, MyException{
		if(this.isAlias()){
			int loc =-1;
			String cn = this.getColAlias().getColName();
			int s = schema.size();
			if(this.getColAlias().isdotted()){
				String tn = this.getColAlias().getTbName();
				boolean f = false;
				for(String tblist: tbnamelist){
					if(tblist.equals(tn)){
						f = true;
					}
				}
				if(!f){
					//존재하지 않는 테이블 참조 
					throw new MyException(Messages.WhereTableNotSpecified);
				}
				for(int i=0; i<s; ++i){
					if(schema.get(i).getName().equals(cn) && schema.get(i).getTbName().equals(tn)){
						loc = i;
					}
				}
			}else{
				for(int i=0; i<s; ++i){
					if(schema.get(i).getName().equals(cn)){
						//column name 중복 
						if(loc != -1){
							throw new MyException(Messages.WhereAmbiguousReference);
						}
						loc = i;
					}
				}
			}
			if(loc == -1){
				throw new MyException(Messages.WhereColumnNotExist);
			}
			return loc;
		}
		return -1;
	}
	
	
}
