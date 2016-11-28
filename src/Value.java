import java.io.Serializable;
import java.util.ArrayList;

/** 
 * Value consists of type and String
 * Type could have 4 different variations, 
 * 0 = INT, 
 * 1 = CHAR,
 * 2 = DATE, 
 * 3 = NULL, 
 *
 * if this is null data, it doesn't have data type and just possess boolean val null
 * 
 * @author heejae
 *
 */
public class Value implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2166613117753781859L;
	private int type;
	private String val;
	//needs upgrade
//	private ArrayList<Value> referencing = new ArrayList<Value>();
//	private ArrayList<String> referingTable = new ArrayList<String>();
//	private ArrayList<Value> referenced = new ArrayList<Value>();
//	private ArrayList<String> referedTable = new ArrayList<String>();
//	
	public Value(int dt, String value){
		this.type = dt;
		this.val = value;
	}
	//if null
	public Value(int dt){
		this.type = dt;
	}
	
//	public void setReferencing(String tbname, Value e){
//		this.referencing.add(e);
//		this.referingTable.add(tbname);
//	}
//	
//	public void setReferenced(String tbname, Value e){
//		this.referenced.add(e);
//		this.referedTable.add(tbname);
//	}
	
	public boolean isInt(){
		return this.type == 0;
	}
	public boolean isChar(){
		return this.type == 1;
	}
	public boolean isDate(){
		return this.type == 2;
	}
	public boolean isNull(){
		return this.type == 3;
	}
	
	public int getType(){
		return this.type;
	}
	
	public String getVal(){
		return this.val;
	}
	public void setVal(String s){
		this.val = s;
	}
	public void setNull(){
		this.type = 3;
		this.val = "";
	}
	
	//works correctly only correct input is inserted;
	//no exception handling
	public int getIntVal(){
		return Integer.parseInt(this.val);
	}
	
	public boolean equals(Value oth) throws Exception, MyException{
		if(this.isNull() || oth.isNull()){
			return false;
		}
		else{
			if(this.getType() != oth.getType()){
				throw new MyException(Messages.WhereIncomparableError);
			}
			return this.val.equals(oth.getVal());
		}
	}
	
	public boolean cmp(Value oth, String cp) throws MyException, Exception{
		if(this.getType() != oth.getType()){
			throw new MyException(Messages.WhereIncomparableError);
		}
		if(this.isInt()){
			int a = Integer.parseInt(this.getVal());
			int b = Integer.parseInt(oth.getVal());
			if(cp.equals("=")){
				return a == b;
			}else if (cp.equals("<")){
				return a<b;
			}else if (cp.equals(">")){
				return a>b;
			}else if (cp.equals("<=")){
				return a<=b;
			}else if (cp.equals(">=")){
				return a>=b;
			}else {
				return a!=b;
			}
			
		}else if (this.isChar()){
			String a = this.getVal();
			String b = oth.getVal();
			if(cp.equals("=")){
				return a.compareTo(b)==0;
			}else if (cp.equals("<")){
				return a.compareTo(b)<0;
			}else if (cp.equals(">")){
				return a.compareTo(b)>0;
			}else if (cp.equals("<=")){
				return a.compareTo(b)<=0;
			}else if (cp.equals(">=")){
				return a.compareTo(b)>=0;
			}else {
				return a.compareTo(b) !=0;
			}
		}else {
			String a = this.getVal();
			String b = oth.getVal();
			if(cp.equals("=")){
				return a.compareTo(b)==0;
			}else if (cp.equals("<")){
				return a.compareTo(b)<0;
			}else if (cp.equals(">")){
				return a.compareTo(b)>0;
			}else if (cp.equals("<=")){
				return a.compareTo(b)<=0;
			}else if (cp.equals(">=")){
				return a.compareTo(b)>=0;
			}else {
				return a.compareTo(b) !=0;
			}
		}

	}
	
	
	public String toString(){
		if(this.isNull()){
			return "null";
		}
		return this.val;
	}
	
	
}
