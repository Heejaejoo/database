import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CreateTableQuery extends Query{
	private String tableName;
	private ArrayList<Column> cols = new ArrayList<Column>();
	private ArrayList<PrimaryKeyConstraint> pkConsts = new ArrayList<PrimaryKeyConstraint>();
	private ArrayList<ReferentialConstraint> refConsts = new ArrayList<ReferentialConstraint>();
	
	public void addColumn(Column t){
		this.cols.add(t);
	}
	
	public void addPrimaryKeyConst(PrimaryKeyConstraint a){
		this.pkConsts.add(a);
	}
	
	public void addReferentialKeyConst(ReferentialConstraint b){
		this.refConsts.add(b);
	}
	
	public String getTableName(){
		return this.tableName;
	}
	private void printAll(){
		System.out.printf("Table name: %s\n", this.tableName);
		for(int i=0; i<this.cols.size(); ++i){
			this.cols.get(i).printAll();
		}
		for(int i=0; i<this.pkConsts.size(); ++i){
			this.pkConsts.get(i).printAll();
		}
		for(int i=0; i<this.refConsts.size(); ++i){
			this.refConsts.get(i).printAll();
		}
	}

	public void execute() throws MyException, Exception{
		DBManager dbman = DBManager.dbman();
		if(this.refConsts.size() > 0){
			Set<String> nameset = new HashSet<String>();
			for(int i=0; i<this.cols.size(); ++i){
				nameset.add(this.cols.get(i).getName());
			}
			for(int i=0; i<refConsts.size(); ++i){
				ReferentialConstraint ref = this.refConsts.get(i);
				// check if referential attributes size are matched
				if(ref.getColumnNameList().size() != ref.getRefColumnNameList().size()){
					throw new MyException(Messages.ReferenceTypeError);
				}
				//check correct names
				for(int j=0; j<ref.getColumnNameList().size(); ++j){
					String name = ref.getColumnNameList().get(j);
					if(!nameset.contains(name)){
						throw new MyException(String.format(Messages.NonExistingColumnDefError, name));
					}
				}
				String t = ref.getReferencedTableName();
				Table reference = dbman.get(t, -1);
				
				//primary key column number check
				//System.out.printf("reference: %d\n", reference.PKcount);
				//System.out.printf("referencing: %d\n", ref.getColumnNameList().size());
				if(reference.PKcount != ref.getRefColumnNameList().size()){
					throw new MyException(Messages.ReferenceTypeError);
				}
				for(int p=0; p<ref.getColumnNameList().size(); ++p){
					String referingcol = ref.getColumnNameList().get(p);
					String referingcolname = ref.getRefColumnNameList().get(p);
					Column thistypecol = null;
					//referingcol 마다, 현재의 column과 일치하는지를 먼저 확인 

					for(Column thiscol: this.cols){
						if(thiscol.getName().equals(referingcol)){
							thistypecol = thiscol;
							thistypecol.setFK();
							break;
						}
					}
					
					//thistypecol이 foreign key로 선택된 column		
					boolean flag = false;
					for(Column refcol: reference.getColumns()){
						if(refcol.getName().equals(referingcolname)){
							System.out.printf("this %s, that %s\n", referingcol, referingcolname);
							flag = true;
							//reference하는 column이 PK가 아닌경
							if(!refcol.isPK()){
								throw new MyException(Messages.ReferenceNonPrimaryKeyError);
							}
							//reference하는 column과 type이 다른경우 
							if(!refcol.getType().equals(thistypecol.getType())){
								throw new MyException(Messages.ReferenceTypeError);
							}
							thistypecol.setReftb(reference.getName());
							thistypecol.setRefcol(refcol.getName());
							break;
						}
					}
					//referenced되는 칼럼이 존재하지 않는 경우 
					if(!flag){
						throw new MyException(Messages.ReferenceColumnExistenceError);
					}
				}	
			}	
		}
		Table a = new Table(tableName, cols, pkConsts);
		dbman.put(a);
		System.out.printf(Messages.CreateTableSuccess + "\n", a.getName());
	}
	
	public CreateTableQuery(String name){
		this.tableName = name;
	}
	
}
