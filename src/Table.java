import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;

public class Table implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9172272818248838214L;
	private String tableName;
	private ArrayList<Column> columns = new ArrayList<Column>();
	public int PKcount = 0;

	private boolean checkColumnDuplicate(ArrayList<Column> cols){
		Set<Column> set = new HashSet<Column>();
		for(int i=0; i<this.columns.size(); ++i){
			set.add(cols.get(i));
		}
		return cols.size() != set.size();
	}
	
	public String getName(){
		return this.tableName;
	}
	public ArrayList<Column> getColumns(){
		return this.columns;
	}
	public Table(String tn, ArrayList<Column> cols, ArrayList<PrimaryKeyConstraint> pks) throws MyException, Exception{
		this.tableName = tn;
		this.columns.addAll(cols);
		if(checkColumnDuplicate(cols)){
			throw new MyException(Messages.DuplicateColumnDefError);
		}
		// primary key정의가 두번이상나온 경
		if(pks.size()>1){
			throw new MyException(Messages.DuplicatePrimaryKeyDefError);
		}
		
		Set<String> nameset = new HashSet<String>();
		for(int i=0; i<this.columns.size(); ++i){
			nameset.add(this.columns.get(i).getName());
		}
		
		//handle no pk constraints
		if(pks.size() > 0){
			PrimaryKeyConstraint cons = pks.get(0);
			for(int i=0; i<cons.getcols().size(); ++i){
				String name = cons.getcols().get(i);
				if(!nameset.contains(name)){
					throw new MyException(Messages.NonExistingColumnDefError);
				}	
			}
			// set Primary key
			for(String pk : cons.getcols()){
				for(Column col: this.columns){
					if(!col.isNotNull()){
						throw new MyException(Messages.PrimaryKeyNullError);
					}
					if(col.getName().equals(pk)){
						col.setPK();
						this.PKcount++;
					}
				}
			}
		}
		
		// handle no references
				
	}
	

	
}
