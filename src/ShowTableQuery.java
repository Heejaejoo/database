import java.util.ArrayList;

public class ShowTableQuery extends Query{
	DBManager dbman = DBManager.dbman();
	ArrayList<Table> list = null;;
	public ShowTableQuery() throws Exception{
		list = dbman.getAll(0);
	}
	
	public void execute(){
		if(list != null){
		System.out.println("----------------");
			for(Table tb : list){
				System.out.println(tb.getName());
			}
		System.out.println("----------------");
		}else{
			System.out.println(Messages.ShowTablesNoTable);
		}
	}
}
