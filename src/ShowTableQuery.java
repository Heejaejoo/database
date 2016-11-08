import java.util.ArrayList;

public class ShowTableQuery {
	DBManager dbman = new DBManager();
	ArrayList<Table> list = null;;
	public ShowTableQuery() throws Exception{
		list = dbman.getAll();
	}
	
	public void print(){
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
