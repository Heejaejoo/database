import java.util.ArrayList;

public class NullPredicate {
	boolean type;
	//if type = 0? ISNULL, 1? ISNOTNULL
	ColAlias colalias;
	
	public NullPredicate(boolean t, ColAlias str){
		this.type = t;
		this.colalias = str;
	}
	
	public ColAlias getColAlias(){
		return this.colalias;
	}
	
	public boolean isNull(){
		return this.type;
	}
	
	public Logic evaluate(Table t, int idx) throws Exception, MyException{
		ArrayList<Value> record = t.getEntries().get(idx);
		ArrayList<Column> schema = t.getColumns(); 
		if(this.getColAlias().isdotted()){
			String tn = this.getColAlias().getTbName();
			//존재하지 않는 테이블 참조 
			if(!t.getName().equals(tn)){
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
		Value v = record.get(loc);
		boolean res = v.isNull();
		
		//check this value is null and th function
		if(this.isNull()){
			if(res){
				return new Logic(0);
			}else{
				return new Logic(1);
			}
		}else{
			if(res){
				return new Logic(1);
			}else{
				return new Logic(0);
			}
		}
	}
	
}
