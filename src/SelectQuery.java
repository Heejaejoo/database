import java.util.ArrayList;

public class SelectQuery extends Query{
	
	
	private boolean star = false;
	private ArrayList<TableAlias> tables = new ArrayList<TableAlias>();
	private ArrayList<ColAlias> columns = new ArrayList<ColAlias>();
	private BooleanValueExpr whereclause  = null;
	
	public void addColumns(ColAlias c){
		this.columns.add(c);
	}
	public void addTables(ArrayList<TableAlias> t){
		this.tables.addAll(t);
	}
	public void setStar(){
		this.star = true;
	}
	public void addWhere(BooleanValueExpr v){
		this.whereclause = v;
	}
	
	public ArrayList<TableAlias> getTables(){
		return this.tables;
	}
	
	public ArrayList<ColAlias> getCols(){
		return this.columns;
	}
	
	public boolean isStar(){
		return this.star;
	}
	
	public boolean hasWhere(){
		return this.whereclause != null;
	}
	
	public void execute() throws Exception, MyException{
		DBManager dbman = DBManager.dbman();
		TableAlias first = tables.get(0);
		String tn = first.gettbname();
		System.out.println(tn);

		Table t = dbman.get(tn, 2);
		for(int i=1; i<tables.size(); ++i){
			TableAlias cur = tables.get(i);
			String curTbname = cur.gettbname();
			Table curt = dbman.get(curTbname, 2);
//			System.out.println(curt.getName());
			t.cartesianProduct(curt);
		}
		
		
	}
}
