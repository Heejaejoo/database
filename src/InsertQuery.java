import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InsertQuery extends Query{
	DBManager dbman;
	ArrayList<Value> valList = new ArrayList<Value>();
	ArrayList<String> colNameList  = new ArrayList<String>();
	String tableName;
	
	//variable used for execute function
	Table t;
	
	private boolean checkColumnDuplicate(ArrayList<String> cols){
		Set<String> set = new HashSet<String>();
		for(int i=0; i<cols.size(); ++i){
			set.add(cols.get(i));
		}
		return cols.size() != set.size();
	}
	
	public InsertQuery(String name, ArrayList<String> clist, ArrayList<Value> vlist) throws MyException{
		this.tableName = name;
		this.colNameList.addAll(clist);
		if(checkColumnDuplicate(clist)){
			throw new MyException(Messages.DuplicateColumnDefError);
		}
		this.valList.addAll(vlist);
	}
	
	public InsertQuery(String name, ArrayList<Value> vlist) throws MyException{
		this.tableName = name;
		this.valList.addAll(vlist);
	}
	
	private void addNullToValueList(ArrayList<Column> colList) {
		int siz = colNameList.size();
		int siz2 = colList.size();

		int [] selectarr = new int[siz2];
		Arrays.fill(selectarr, -1);
		
		//get column idx which matches colName
		String n;
		Column c;
		for(int i=0; i<siz; ++i){
			n = colNameList.get(i);
			for(int j=0; j<siz2; ++j){
				c = colList.get(i);
				if(c.getName().equals(n)){
					selectarr[j] = i;
				}
			}
		}
		Value v;
		ArrayList<Value> res = new ArrayList<Value>();
		for(int i=0; i<siz2; ++i){
			if(selectarr[i] != -1){
				int index = selectarr[i];
				v = valList.get(index); 
			}else{
				//create null value;
				v = new Value(3);
			}
			res.add(v);
		}
		//ready for filling nulls to valuelist
		this.valList.clear();
		this.valList.addAll(res);
	}
	
	//handle validation except primary key
	private void basicValidate(ArrayList<Column> colList) throws MyException{
		//when column specifying query
		//finially calls addNulltoValueList
		if(this.colNameList.size() != 0){
			if(colNameList.size() != valList.size()){
				throw new MyException(Messages.InsertTypeMismatchError);
			}
			int siz = colNameList.size();
			for(int i=0; i<siz; i++){
				String n = colNameList.get(i);
				Value v = valList.get(i);
				Column c = null;
				for(Column col:colList){
					if(col.getName().equals(n)){
						c = col;
						break;
					}
				}
				// if there is no column in the table whose name is n;
				if(c == null){
					throw new MyException(String.format(Messages.InsertColumnExistenceError, n));
				}
			}
			addNullToValueList(colList);
		}
		
		//common validation
		//size mismatch
		if(valList.size() != colList.size()){
			throw new MyException(Messages.InsertTypeMismatchError);
		}
		
		int siz = valList.size();
		Value v;
		Column c;
		for(int i=0; i<siz; i++){
			//value - column comparison
			v = valList.get(i);
			c = colList.get(i);
			if(v.getType() == 3){
				if(c.isNotNull()){
					//when insert null value to column which has not null constraint
						throw new MyException(String.format(Messages.InsertColumnNonNullableError, c.getName()));
				}
				continue;
			}else{
				DataType ctype = c.getType();
				//type mismatch
				if(ctype.getType() != v.getType()){
					throw new MyException(Messages.InsertTypeMismatchError);
				}
				//if data type of v is char
				if(v.getType() == 1){
					//length validation
					int maxlen = ctype.getlength();
					if(v.getVal().length() > maxlen){
						//needs resize
						String s = v.getVal().substring(0, maxlen);
						v.setVal(s);
					}
				}
			}
		}	
	}
	
	
	//need to implement
	private void primaryKeyValidate(ArrayList<ArrayList<Value>> rs){
		for(ArrayList<Value> record: rs){
			
		}
	}
	
	public void execute() throws Exception, MyException{
		dbman = DBManager.dbman();
		t = dbman.get(this.tableName, 2);
		ArrayList<Column> clist = t.getColumns();
		ArrayList<ArrayList<Value>> records = t.getEntries();
		basicValidate(clist);
		int siz = records.size();
		
		
		
		
		
	}
}
