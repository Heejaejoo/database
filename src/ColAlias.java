
public class ColAlias {
	int type;
	//if type == 0--> col name alias, 
	//else table name alias
	private String tablename;
	private String colname;
	private String alias = null;
	
	public ColAlias(String s){
		this.colname = s;
		this.type =0;
	}
	
	public ColAlias(String t, String c){
		this.tablename = t;
		this.colname = c;
		this.type = 1;
	}
	
	public ColAlias(String t, String c, String ali){
		tablename = t;
		colname = c;
		alias = ali;
		this.type = 1;
	}
	
	public ColAlias(String c, String ali, boolean isalias){
		this.colname = c;
		this.alias = ali;
		this.type = 0;
	}
	
	public String getColName(){
		return this.colname;
	}
	
	public String getTbName(){
		return this.tablename;
	}
	
	public String getAlias(){
		return this.alias;
	}
	
	public boolean hasAlias(){
		return this.alias != null;
	}
	
	public boolean isdotted(){
		return this.type ==1;
	}
}
