import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CreateTableQuery{
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
		printAll();
		DBManager dbman = new DBManager();
		if(this.refConsts.size() > 0){
			Set<String> nameset = new HashSet<String>();
			for(int i=0; i<this.cols.size(); ++i){
				nameset.add(this.cols.get(i).getName());
			}
			for(int i=0; i<refConsts.size(); ++i){
				ReferentialConstraint ref = this.refConsts.get(i);
				if(this.refConsts.get(i).getColumnNameList().size() != ref.getRefColumnNameList().size()){
					throw new MyException(Messages.ReferenceTypeError);
				}
				//check correct names
				for(int j=0; j<ref.getColumnNameList().size(); ++j){
					String name = ref.getColumnNameList().get(j);
					if(!nameset.contains(name)){
						throw new MyException(Messages.NonExistingColumnDefError);
					}
				}
				String t = ref.getReferencedTableName();
				Table reference = dbman.get(t);
				if(reference == null){
					throw new MyException(Messages.ReferenceTableExistenceError);
				}
				
				//column number check
				if(reference.PKcount != ref.getRefColumnNameList().size()){
					throw new MyException(Messages.ReferenceTypeError);
				}
				for(String referingcol: ref.getColumnNameList()){
					Column thistypecol = null;
					for(Column thiscol: this.cols){
						if(thiscol.getName().equals(referingcol)){
							thistypecol = thiscol;
							thiscol.setFK();
						}
					}
					for(Column refcol: reference.getColumns()){
						boolean flag = false;
						if(refcol.getName().equals(thistypecol.getName())){
							flag = true;
							if(!refcol.isPK()){
								throw new MyException(Messages.ReferenceNonPrimaryKeyError);
							}
							if(!refcol.getType().equals(thistypecol.getType())){
								throw new MyException(Messages.ReferenceTypeError);
							}
						}
						if(!flag){
							throw new MyException(Messages.ReferenceColumnExistenceError);
						}
					}
				}	
			}	
		}
		Table a = new Table(tableName, cols, pkConsts);
		if(dbman.put(a) == 1){
			System.out.println("ss");
			System.out.printf(Messages.CreateTableSuccess, a.getName());
		}else{
			throw new MyException(Messages.DropSuccess);
		};
	}
	
	public CreateTableQuery(String name){
		this.tableName = name;
		System.out.println(tableName);
	}
	
	
}
