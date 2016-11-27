import java.util.ArrayList;
import java.util.HashMap;

public class DeleteQuery extends Query{
	
	String tableName;
	BooleanValueExpr whereclause = null;
	DBManager dbman;
	
	
	public DeleteQuery(String tbname){
		this.tableName = tbname;
	}
	public DeleteQuery(String tbname, BooleanValueExpr where)
	{
		this.tableName = tbname;
		this.whereclause = where;
	}
	
	public void execute() throws Exception, MyException{
		dbman = DBManager.dbman();
		//handle nosuch table error
		Table t = dbman.get(this.tableName, 2);
		ArrayList<Boolean> rowEval = new ArrayList<Boolean>();
		ArrayList<Column> cols = t.getColumns();
		
		
		
		
		//make hashmap
		HashMap<String, Column> schema = new HashMap<String, Column>();
		for(Column col : cols){
			schema.put(col.getName(), col);
		}
		
		//TODO: implement referential integrity

	}
}
