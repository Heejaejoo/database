import java.util.ArrayList;
import java.util.HashMap;

public class DeleteQuery extends Query{
	
	String tableName;
	BooleanValueExpr whereclause = null;
	DBManager dbman;
	
	
	public DeleteQuery(String tbname){
		this.tableName = tbname;
	}
	
	public DeleteQuery(String tbname, BooleanValueExpr where)
	{
		this.tableName = tbname;
		this.whereclause = where;
	}
	
	public void execute() throws Exception, MyException{
		dbman = DBManager.dbman();
		//handle nosuch table error
		Table t = dbman.get(this.tableName, 2);
		HashMap<Integer, Boolean> possible = new HashMap<Integer, Boolean>();
//		ArrayList<Boolean> rowEval = new ArrayList<Boolean>();
		ArrayList<Column> cols = t.getColumns();
		for(Column cls: cols){
			cls.setDotAndTbname(this.tableName);
		}
		//dummy evaluate
		//TODO: implement referential integrity
		if(this.whereclause == null){
			for(Integer i: t.getEntries().keySet()){
				possible.put(i, new Boolean(true));
			}	
		}else{
			whereclause.evaluate(t, 0, true);
			for(Integer i: t.getEntries().keySet()){
				if(whereclause.evaluate(t, i, false).isTrue()){
					possible.put(i, new Boolean(true));
				}else{
					possible.put(i, new Boolean(false));
				}
			}
		}
		
		//settting is done; 
		//check refrential integrity;
		//this value is to be set true when there is one record which violates refrential integrity
		int deletedCount = 0;
		int deleteFailedCount = 0;
		HashMap<String, Table> referencingTable = new HashMap<String, Table>();
		HashMap<String, Table> referedTable = new HashMap<String, Table>();
		ArrayList<Integer> done = new ArrayList<Integer>();
		
		for(Integer i: t.getEntries().keySet()){
			if(!possible.get(i).booleanValue()){
				//삭제할 대상이 아닌 경우 
				continue;
			}
			//check this table is referenced
			boolean flag = false;
			if(t.getReferenced().containsKey(i)){
				ArrayList<Pair<String, Integer>> referenced = t.getReferenced().get(i);
				ArrayList<Integer> intrecord = new ArrayList<Integer>();
				ArrayList<String> strrecord = new ArrayList<String>();
				ArrayList<Integer> intrecord2 = new ArrayList<Integer>();
				for(Pair<String, Integer> refering: referenced){
					String tn = refering.getFirst();
					Integer idx = refering.getSecond();
//					System.out.println(tn + " of " + idx.toString() + "is refering this column " + i.intValue());
					Table referingt;
					
					if(referedTable.containsKey(tn))
					{
						referingt = referedTable.get(tn);
					}else{
						referingt = dbman.get(tn, 2);
					}
					
					ArrayList<Value> referingTuple = referingt.getEntries().get(idx);
					ArrayList<Column> referingClist = referingt.getColumns();
					int ssizz = referingClist.size();
					HashMap<String, ArrayList<String>> foreignKeyMap = new HashMap<String, ArrayList<String>>();
					HashMap<String, ArrayList<Integer>> foreignKeyIdxMap = new HashMap<String, ArrayList<Integer>>();
					
					for(int p =0; p<ssizz; p++){
						Column c = referingClist.get(p);
						ArrayList<String> tbNameList = c.getReftb();
						ArrayList<String> refColNameList = c.getRefcol();
						
						//이 칼럼의 referencing list
						//c의 ref list를 순회하면서 map에 push
						int refsize = tbNameList.size();
						for(int idxx=0; idxx<refsize; ++idxx){
							//1st table, table num
							String tbn = tbNameList.get(idxx);
							String coln = refColNameList.get(idxx);
							if(foreignKeyMap.containsKey(tbn)){
								foreignKeyMap.get(tbn).add(coln);
								foreignKeyIdxMap.get(tbn).add(new Integer(p));
							}else{
								ArrayList<String> a = new ArrayList<String>();
								ArrayList<Integer> b = new ArrayList<Integer>();
								a.add(coln);
								b.add(new Integer(p));
								foreignKeyMap.put(tbn, a);
								foreignKeyIdxMap.put(tbn, b);
							}
						}
					}
					
					ArrayList<Integer> thisidxlist = foreignKeyIdxMap.get(t.getName());
					//construct idx list of this, that
					int thisidxsize = thisidxlist.size();
					for(int j=0; j<thisidxsize; ++j){
						if(referingClist.get(thisidxlist.get(j)).isNotNull()){
							flag = true;
							break;
						}
					}
					if(flag){
						//notnull이 있는 경우// 
						break;
					}
					//not null 없고, 다 바꿀수 있을 때 참조하는 튜플의 값을 다 널로 바꾼다 
					for(int l=0; l<thisidxlist.size(); ++l){
						int thisid = thisidxlist.get(l);
						referingTuple.get(thisid).setNull();
					}
//					t.deReferenced(i, referingt.getName(), idx);
					//concurrent modification prevention
					intrecord.add(i);
					strrecord.add(referingt.getName());
					intrecord2.add(idx);
					referingt.deReferencing(idx, t.getName(), i);
					referedTable.put(referingt.getName(), referingt);
				}
				for(int ii=0; ii<intrecord.size(); ++ii){
					t.deReferenced(intrecord.get(ii), strrecord.get(ii), intrecord2.get(ii));
				}
			}
			
			if(flag){
				deleteFailedCount++;
				continue;
			}else {
				for(String tn: referedTable.keySet()){
					dbman.delete(tn);
					dbman.put(referedTable.get(tn));
				}
				referedTable.clear();
			}
			
			//check referencing
			//refering 하는 것은 
			if(t.getReferencing().containsKey(i)){
				ArrayList<Pair<String, Integer>> referencing = t.getReferencing().get(i);
				ArrayList<Integer> intrecord = new ArrayList<Integer>();
				ArrayList<String> strrecord = new ArrayList<String>();
				ArrayList<Integer> intrecord2 = new ArrayList<Integer>();
				for(Pair<String, Integer> reference: referencing){
					String tn = reference.getFirst();
					Integer idx = reference.getSecond();
					Table referedt;
					boolean f = false;
					if(referencingTable.containsKey(tn))
					{
						referedt = referencingTable.get(tn);
					}else{
						referedt = dbman.get(tn, 2);
						f = true;
					}
					intrecord.add(i);
					strrecord.add(tn);
					intrecord2.add(idx);
					referedt.deReferenced(idx, t.getName(), i);
						//successfully updated;
					referencingTable.put(tn, referedt);
				}
				for(int ii=0; ii<intrecord.size(); ++ii){
					t.deReferencing(intrecord.get(ii), strrecord.get(ii), intrecord2.get(ii));
				}
			}
			deletedCount++;
			done.add(i);
		}
		for(String tn: referencingTable.keySet()){
			dbman.delete(tn);
			dbman.put(referencingTable.get(tn));
		}
		for(Integer i: done){
			t.getEntries().remove(i);
		}
		//Done
		dbman.delete(t.getName());
		dbman.put(t);
		System.out.println(String.format(Messages.DeleteResult, deletedCount));
		if(deleteFailedCount>0){
			System.out.println(String.format(Messages.DeleteReferentialIntegrityPassed, deleteFailedCount));
		}
	}
}
