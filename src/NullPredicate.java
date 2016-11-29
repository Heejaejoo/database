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
	
	public Logic evaluate(Table t, int idx, boolean empty) throws Exception, MyException{
		ArrayList<Value> record = null;
		if(!empty)  record = t.getEntries().get(idx);
		ArrayList<Column> schema = t.getColumns(); 
		int loc =-1;
		String cn = this.getColAlias().getColName();
		int s = schema.size();
		if(this.getColAlias().isdotted()){
			String tn = this.getColAlias().getTbName();
			boolean f = false;
			for(String tblist: t.getTableNameList()){
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
		
		if(empty){
			return new Logic(0);
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
