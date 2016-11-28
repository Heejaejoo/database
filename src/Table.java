import java.util.ArrayList;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.io.Serializable;

public class Table implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9172272818248838214L;
	
	private String tableName;
	private ArrayList<Column> columns = new ArrayList<Column>();
	public int PKcount = 0;
	public int rownum = 0;

	private HashMap<Integer, ArrayList<Value> > entries = new HashMap<Integer, ArrayList<Value> >();
	private HashMap<Integer, ArrayList<Pair<String, Integer > > > referencing = new HashMap <Integer, ArrayList<Pair<String, Integer> > >(); 
	private HashMap<Integer, ArrayList<Pair<String, Integer> > > referenced= new HashMap <Integer, ArrayList<Pair<String, Integer> > >(); 
	
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
	
	public HashMap<Integer, ArrayList<Value>> getEntries(){
		return this.entries;
	}
	
	public HashMap<Integer, ArrayList<Pair<String, Integer > > > getReferencing(){
		return this.referencing;
	}
	
	public HashMap<Integer, ArrayList<Pair<String, Integer > > > getReferenced(){
		return this.referenced;
	}
	
	public void deReferencing(int x, String tbname, int num){
		ArrayList<Pair<String, Integer>> lst = this.referencing.get(new Integer(x));
		if(lst.size() == 1){
			this.referencing.remove(new Integer(x));
		}else{
			int siz = lst.size();
			for(int i=0; i<siz; ++i){
				if(lst.get(i).getFirst().equals(tbname) && lst.get(i).getSecond().intValue() == num){
					lst.remove(i);
					return;
				}
			}
		}
	}
	
	public void deReferenced(int x, String tbname, int num){
		ArrayList<Pair<String, Integer>> lst = this.referenced.get(new Integer(x));
		if(lst.size() == 1){
			this.referenced.remove(new Integer(x));
		}else{
			int siz = lst.size();
			for(int i=0; i<siz; ++i){
				if(lst.get(i).getFirst().equals(tbname) && lst.get(i).getSecond().intValue() == num){
					lst.remove(i);
					return;
				}
			}
		}
	}
	
	
	public void setReferencing(int x, String tbname, int num){
		Pair<String, Integer> tp = new Pair<String, Integer>(tbname, new Integer(num));
		if(this.referencing.containsKey(new Integer(x))){
			ArrayList<Pair<String, Integer> > l = this.referencing.get(new Integer(x));
			l.add(tp);
			this.referencing.put(new Integer(x), l);
		}else{
			ArrayList<Pair<String, Integer> > l = new ArrayList<Pair<String, Integer>>();
			l.add(tp);
			this.referencing.put(new Integer(x), l);
		}
	}
	
	public void setReferenced(int x, String tbname, Integer num){
		Pair<String, Integer> tp = new Pair<String, Integer>(tbname, num);
		if(this.referenced.containsKey(new Integer(x))){
			ArrayList<Pair<String, Integer> > l = this.referenced.get(new Integer(x));
			l.add(tp);
			this.referenced.put(new Integer(x), l);
		}else{
			ArrayList<Pair<String, Integer> > l = new ArrayList<Pair<String, Integer>>();
			l.add(tp);
			this.referenced.put(new Integer(x), l);
		}
	}
	
	public int getrownum(){
		return this.rownum;
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
		entries.put(this.rownum, vlist);
		this.rownum++;
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
	    for(Integer k :this.entries.keySet()){
	    	ArrayList<Value> val = this.entries.get(k);
	    	String s = "";
	    	for(Value v: val){
	    		s = s.concat(v.toString()).concat(" ");
	    	}
	    	System.out.println(s);
	    }
	    System.out.println("-------------------------------------------------");
	}
	
	
	
	
	
	
	
	
	
	
}
