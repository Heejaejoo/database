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
}
