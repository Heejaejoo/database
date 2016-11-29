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
			Table curt = dbman.get(curTbname, 3);
			t.cartesianProduct(curt, cur);
		}
		t.prettyPrint();
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
//		if(cnt == 0){
//			System.out.println("Empty set");
//			return;
//		}
		ArrayList<Integer> idxList = new ArrayList<Integer>();
		//select column list check
		int s = t.getColumns().size();
		ArrayList<Column> schema = t.getColumns();
		if(!star){
			for(ColAlias col: columns){
				int loc =-1;
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
						if(schema.get(i).getName().equals(cn) && schema.get(i).getTbName().equals(tn)){
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
		//finialized, need pretty print
		
		
		
		
		
		
	}
}
