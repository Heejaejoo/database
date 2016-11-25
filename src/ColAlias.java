
public class ColAlias {
	int type;
	//if type == 0--> col name alias, 
	//else table name alias
	private String tablename;
	private String colname;
	private String alias;
	
	public ColAlias(String s){
		this.colname = s;
		this.type =0;
	}
	public ColAlias(String t, String c){
		this.tablename = t;
		this.colname = c;
		int type = 1;
	}
	public String getColName(){
		return this.colname;
	}
	
	public String getTbName(){
		return this.tablename;
	}
	
	public boolean isdotted(){
		return this.type ==1;
	}
}
