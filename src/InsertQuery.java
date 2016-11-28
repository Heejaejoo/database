import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

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
				c = colList.get(j);
				if(c.getName().equals(n)){
					selectarr[j] = i;
					break;
				}
			}
		}
		Value v;
		ArrayList<Value> res = new ArrayList<Value>();
		ArrayList<String> name = new ArrayList<String> ();
		for(int i=0; i<siz2; ++i){
			String colname = colList.get(i).getName();
			name.add(colname);
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
		this.colNameList.clear();
		this.colNameList.addAll(name);
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
		
		//common validation starts
		
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
			if(v.isNull()){
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
	private void primaryKeyValidate(Table t) throws Exception, MyException{
//		for(int i=0; i<this.valList.size(); i++){
//			System.out.println(this.valList.get(i).toString());
//		}
		HashMap<Integer, ArrayList<Value> > records = t.getEntries();
		ArrayList<Column> clist = t.getColumns();
		int siz = clist.size();
		//find primary key index
//		System.out.println("size");
//		System.out.println(Integer.toString(siz));
		ArrayList<Boolean> arr = new ArrayList<Boolean>();
		int PKcount=0;
		for(int i=0; i<siz; ++i){
			if(clist.get(i).isPK()){
				arr.add(new Boolean(true));
				PKcount++;
			}else
			arr.add(new Boolean(false));
		}
		//defined when the table has primary key
		//check if there is a record which has same primary key to current valuelist
		if(PKcount>0){
			for(Integer k: records.keySet()){
				int flag = 0;
				ArrayList<Value> record = records.get(k);
				for(int j=0; j<record.size(); j++){
					if(arr.get(j).booleanValue()){
						if(record.get(j).equals(valList.get(j))){
							flag++;
						}
					}
				}
				if(flag == PKcount){
					throw new MyException(Messages.InsertDuplicatePrimaryKeyError);
				}
			}
		}
	}
	
	/**	
	 * 	called before all the validation has done, so this method is for 'good' inputs
	 * having passed primary key, basic validation
	 *  
	 */
	private boolean referentialIntegrityValidateAndUpdate(Table cur) throws Exception, MyException{
		boolean hasForeignkey = false;
		//foreignkey로 지정된 칼럼들에 대해, reference하고있는 테이블과 column들의 name들을 담고있는 map
		//foreignkeyidxmap은 현재 칼럼의 idx
		HashMap<String, ArrayList<String>> foreignKeyMap = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<Integer>> foreignKeyIdxMap = new HashMap<String, ArrayList<Integer>>();
		//check null and if has, use it as a wildcard
		HashMap<String, Boolean> hasNull = new HashMap<String, Boolean>();
		//schema of this table
		ArrayList<Column> clist = cur.getColumns();
		
		int siz = clist.size();
		Column c;
		
		//check if this table's schema has foreignkey 
		//if has, mapping referenced table name and idx to foreignkeymap
		for(int i=0; i<siz; ++i){
			c = clist.get(i);
			//check it is FK
			if(c.isFK()){
				hasForeignkey = true;
				ArrayList<String> tbNameList = c.getReftb();
				ArrayList<String> refColNameList = c.getRefcol();
				//이 칼럼의 referencing list
				
				//c의 ref list를 순회하면서 map에 push
				int refsize = tbNameList.size();
				for(int idx=0; idx<refsize; ++idx){
					//1st table, table num
					String tbn = tbNameList.get(idx);
					String coln = refColNameList.get(idx);
					if(foreignKeyMap.containsKey(tbn)){
						foreignKeyMap.get(tbn).add(coln);
						foreignKeyIdxMap.get(tbn).add(new Integer(i));
						if(this.valList.get(i).getType() == 3){
							//null value, OK!
							hasNull.replace(tbn, true);
						}
					}else{
						ArrayList<String> a = new ArrayList<String>();
						ArrayList<Integer> b = new ArrayList<Integer>();
						a.add(coln);
						b.add(new Integer(i));
						foreignKeyMap.put(tbn, a);
						foreignKeyIdxMap.put(tbn, b);
						if(this.valList.get(i).getType() == 3){
							//null value, OK!
							hasNull.put(tbn, true);
						}else{
							hasNull.put(tbn, false);
						}
					}
				}
			}
		}
		
		if(hasForeignkey){
			dbman = DBManager.dbman();
			Set<String> keyset = foreignKeyMap.keySet();
			ArrayList<Table> updatedTblist  = new ArrayList<Table>();
			Table t;
			//validate it has foreignkey
			for(String tbname: keyset){
				t = dbman.get(tbname, 2);
				if(!hasNull.get(tbname)){
					ArrayList<String> fknamelist = foreignKeyMap.get(tbname);
					ArrayList<Integer> thisidxlist = foreignKeyIdxMap.get(tbname);
					ArrayList<Integer> thatidxlist = new ArrayList<Integer>();
					ArrayList<Column> thatColList = t.getColumns();
					//construct idx list of this, that
					int s = thatColList.size();
					for(int j=0; j<fknamelist.size(); ++j){
						for(int i=0; i<s; i++){
							if(thatColList.get(i).getName().equals(fknamelist.get(j))){
								thatidxlist.add(new Integer(i));
								break;
							}
						}
					}
					
					if(thisidxlist.size() != thatidxlist.size()){
						System.out.println("something wrong on idx");
						throw new Exception();
					}
					
					HashMap<Integer, ArrayList<Value>> records = t.getEntries();
					boolean flag = false;
					//일치하는 record가 있는지 확인
					for(Integer k: records.keySet()){
						ArrayList<Value> record = records.get(k);
						int cnt = 0;
						int sizz = thisidxlist.size();
						for(int id = 0; id<sizz; ++id){
							Value thisvalue = this.valList.get(thisidxlist.get(id));
							Value thatvalue = record.get(thatidxlist.get(id));
							if(thisvalue.equals(thatvalue)){
								cnt++;
							}
						}
						//find the one!
						if(cnt == sizz){
							flag = true;
//							for(int id = 0; id<sizz; ++id){
//								Value thisvalue = this.valList.get(thisidxlist.get(id));
//								Value thatvalue = record.get(thatidxlist.get(id));
//							//delete시 주의할 것 
//								thatvalue.setReferenced(cur.getName(), thisvalue);
//								thisvalue.setReferencing(t.getName(), thatvalue);
//							}
							int x = cur.getrownum();
							cur.setReferencing(x, tbname, k);
							t.setReferenced(k, cur.getName(), new Integer(x));
							break;
						}
					}
					//일치하는 
					if(!flag){
						throw new MyException(Messages.InsertReferentialIntegrityError);
					}
					updatedTblist.add(t);
					//update that one
				}
			}
			//pass validation
			//congraturation
			for(Table tble: updatedTblist){
				String nm = tble.getName();
				dbman.delete(nm);
				dbman.put(tble);
			}
		}
		return true;
	}
	
	public void execute() throws Exception, MyException{
		dbman = DBManager.dbman();
		t = dbman.get(this.tableName, 2);
		ArrayList<Column> clist = t.getColumns();
		basicValidate(clist);
		primaryKeyValidate(t);
		if(referentialIntegrityValidateAndUpdate(t)){
			t.addEntries(this.valList);
			dbman.delete(t.getName());
			dbman.put(t);
			System.out.println(Messages.InsertResult);
			Table r = dbman.get(t.getName(), 2);
			r.prettyPrint();
		};
	}
}
