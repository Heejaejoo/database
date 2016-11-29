
public class TableAlias {
	private String tbname;
	private String alias = null;
	
	public TableAlias(String tbna){
		tbname  = tbna;
	}
	public TableAlias(String tbn, String al)
	{
		tbname = tbn;
		alias = al;
	}
	public boolean isAlias(){
		return this.alias != null;
	}
	
	public String gettbname(){
		return this.tbname;
	}
	
	public String getalias(){
		return this.alias;
	}
}
