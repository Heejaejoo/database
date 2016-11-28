import java.util.ArrayList;


public class DropTableQuery extends Query{
	private String tableName;
	
	public DropTableQuery(String name){
		this.tableName = name;
	}
	public void execute() throws Exception, MyException{
		DBManager dbman = DBManager.dbman();
		ArrayList<Table> tblst = dbman.getAll(-1);
		for(Table tb: tblst){
			if(tb.getName() == this.tableName){
				continue;
			}
			for (Column col: tb.getColumns()){
				if (col.getReftb().size()>0){
					for(int i=0; i<col.getReftb().size(); ++i){
						String name = col.getReftb().get(i);
						if(name.equals(this.tableName)){
							throw new MyException(String.format(Messages.DropReferencedTableError, this.tableName));
						}
					}
				}
			}
		}
		
		DeleteQuery q = new DeleteQuery(this.tableName){};
		q.execute();
		if(dbman.delete(this.tableName) == 1 ){
			System.out.println(String.format(Messages.DropSuccess, this.tableName));	
		}else{
			//unhandled situation
			throw new Exception();
		}
	}
}
