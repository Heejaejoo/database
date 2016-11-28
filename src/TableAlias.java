
public class TableAlias {
	private String tbname;
	private String alias;
	
	public TableAlias(String tbna){
		tbname  = tbna;
	}
	public TableAlias(String tbn, String al)
	{
		tbname = tbn;
		alias = al;
	}
	
	public String gettbname(){
		return this.tbname;
	}
	
	public String getalias(){
		return this.alias;
	}
}
