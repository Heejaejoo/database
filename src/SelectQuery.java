import java.util.ArrayList;
import java.util.HashMap;

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
		Table t = dbman.get(tn, 3);
		t.initCart(first);
		for(int i=1; i<tables.size(); ++i){
			TableAlias cur = tables.get(i);
			String curTbname = cur.gettbname();
			System.out.println(curTbname);
			Table curt = dbman.get(curTbname, 3);
			t.cartesianProduct(curt, cur);
		}
//		t.prettyPrint();
		HashMap<Integer, Boolean> possible = new HashMap<Integer, Boolean>();
		int cnt = 0;
		if(this.whereclause == null){
			for(Integer i: t.getEntries().keySet()){
				possible.put(i, new Boolean(true));
				cnt++;
			}	
		}else{
			whereclause.evaluate(t, 0, true);
			for(Integer i: t.getEntries().keySet()){
				if(whereclause.evaluate(t, i, false).isTrue()){
					possible.put(i, new Boolean(true));
					cnt++;
				}else{
					possible.put(i, new Boolean(false));
				}
			}
		}
		
		ArrayList<Integer> idxList = new ArrayList<Integer>();
		//select column list check
		int s = t.getColumns().size();
		ArrayList<Column> schema = t.getColumns();
		if(!star){
			for(ColAlias col: columns){
				int loc =-1;
//				System.out.println(col.toString());
				String cn = col.getColName();
				String ttn = col.getTbName();
				if(col.isdotted()){
					boolean f = false;
					for(String tblist: t.getTableNameList()){
						if(tblist.equals(ttn)){
							f = true;
						}
					}
					
					if(!f){
						//존재하지 않는 테이블 참조 
						throw new MyException(String.format(Messages.SelectColumnResolveError, cn));
					}
					for(int i=0; i<s; ++i){
						if(schema.get(i).getName().equals(cn) && schema.get(i).getTbName().equals(ttn)){
							loc = i;
						}
					}
					
				}else{
					for(int i=0; i<s; ++i){
						if(schema.get(i).getName().equals(cn)){
							//column name 중복 
							if(loc != -1){
								throw new MyException(String.format(Messages.SelectColumnResolveError, cn));
							}
							loc = i;
						}
					}
				}
				
				if(loc == -1){
					throw new MyException(String.format(Messages.SelectColumnResolveError, cn));
				}
				
				idxList.add(new Integer(loc));
			}
		}else{
			for(int i=0; i<s; ++i){
				idxList.add(new Integer(i));
			}
		}
		
		//record가 없는 경우  
		if(cnt == 0){
			System.out.println("Empty set");
			return;
		}
		
		//finialized, need pretty print
		
    	int poslen = idxList.size();
    	int arr[] = new int[poslen];
    	if(star){
        	for(int i=0; i<poslen; ++i){
    			arr[i] = schema.get(i).getName().length();
        	}
    	}else{
        	for(int i=0; i<poslen; ++i){
        		if(columns.get(i).hasAlias()){
    				arr[i] = columns.get(i).getAlias().length();
    			}else{
    				arr[i] = columns.get(i).getColName().length();
    			}
        	}
    	}
    	
    	for(Integer i: t.getEntries().keySet()){
	    	if(possible.get(i).booleanValue()){
	    		ArrayList<Value> vv = t.getEntries().get(i);
	    		for(int j=0; j<idxList.size(); ++j){
	    			int k= idxList.get(j).intValue();
	    			arr[j] = vv.get(k).toString().length() > arr[j] ? vv.get(k).toString().length() : arr[j];
	    		}
	    	}
	    }
    	String ss2 = "";
    	for(int i=0; i<poslen; ++i){
    		ss2+="+";
    		for(int j=0; j<arr[i]; ++j){
    			ss2+="-";
    		}
    	}
    	ss2+="+";
    	System.out.println(ss2);

    	
    	String ss = "|";
    	if(star){
		    for(int i=0; i<schema.size(); ++i){
		    	int l = schema.get(i).getName().length();
		    	int blank = arr[i]-l;
		    	ss += schema.get(i).getName();
		    	for(int j=0; j<blank; ++j){
		    		ss += " ";
		    	}
		    	ss += "|";
		    }
    	}else {
    		for(int i=0; i<idxList.size(); ++i){
    			String rr;
    			if(columns.get(i).hasAlias()){
    				rr = columns.get(i).getAlias();
    			}else{
    				rr = columns.get(i).getColName();
    			}
    			int l = rr.length();
		    	int blank = arr[i]-l;
		    	ss += rr;
		    	for(int j=0; j<blank; ++j){
		    		ss += " ";
		    	}
		    	ss += "|";
		    }
    	}    	
    	System.out.println(ss);
    	System.out.println(ss2);    	
    	for(Integer i: t.getEntries().keySet()){
	    	if(possible.get(i).booleanValue()){
	    		ss = "|";
	    		ArrayList<Value> vv = t.getEntries().get(i);
	    		for(int j=0; j<idxList.size(); ++j){
	    			String rr;
	    			int k= idxList.get(j).intValue();
	    			rr = vv.get(k).toString();
	    			int l = rr.length();
			    	int blank = arr[j]-l;
			    	ss += rr;
			    	for(int pk=0; pk<blank; ++pk){
			    		ss += " ";
			    	}
	    			ss += "|";
	    		}
	    		System.out.println(ss);
	    	}
    	}
    	System.out.println(ss2);
	}
}
