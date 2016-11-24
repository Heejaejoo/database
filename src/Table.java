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
	
	private ArrayList<ArrayList<Value> > entries = new ArrayList<ArrayList<Value> >();
	
	private boolean checkColumnDuplicate(ArrayList<Column> cols){
		Set<String> set = new HashSet<String>();
		for(int i=0; i<this.columns.size(); ++i){
			set.add(cols.get(i).getName());
		}
		return cols.size() == set.size();
	}
	
	public String getName(){
		return this.tableName;
	}
	public ArrayList<Column> getColumns(){
		return this.columns;
	}
	public ArrayList<ArrayList<Value>> getEntries(){
		return this.entries;
	}

	public Table(String tn, ArrayList<Column> cols, ArrayList<PrimaryKeyConstraint> pks) throws MyException, Exception{
		this.tableName = tn;
		this.columns.addAll(cols);
		// Column Duplicate check
		if(!checkColumnDuplicate(cols)){
			throw new MyException(Messages.DuplicateColumnDefError);
		}
		
		// primary key정의가 두번이상나온 경우
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
					throw new MyException(String.format(Messages.NonExistingColumnDefError, name));
				}	
			}
			// set Primary key
			for(String pk : cons.getcols()){
				for(Column col: this.columns){
					if(col.getName().equals(pk)){
						//setting this column as not null;
						if(!col.isNotNull()){
							col.setNotNull();
						}
						col.setPK();
						this.PKcount++;
					}
				}
			}
		}	
		// handle no references			
	}
	
	//suppose 'good' input 
	public void addEntries(ArrayList<Value> valist){
		ArrayList<Value> vlist = new ArrayList<Value> ();
		vlist.addAll(valist);
		entries.add(vlist);
	}
	
	public void prettyPrint(){
		Table table = this;
		System.out.println("-------------------------------------------------");
	    System.out.printf("table_name [%s]\n", table.getName());
    	System.out.format("%32s%10s%16s%16s\n", "column_name", "type", "null", "key");
	    for(Column col : table.getColumns()){
	    	String s = "";
	    	if(col.isPK()){
	    		s = "PRI";
	    		if(col.isFK()){
		    		s += "/FOR";
		    	}
	    	}else{
	    		if(col.isFK()){
	    			s = "FOR";
	    		}
	    	}
	    	System.out.format("%32s%10s%16s%16s\n", col.getName(), col.getType().getString(), col.isNotNull()? "N": "Y", s);
	    }
	    
	    System.out.println("values:");
	    for(ArrayList<Value> val:this.entries){
	    	String s = "";
	    	for(Value v: val){
	    		s = s.concat(v.toString()).concat(" ");
	    	}
	    	System.out.println(s);
	    }
	    System.out.println("-------------------------------------------------");
	}
	
	
	
	
	
	
	
	
	
	
}
