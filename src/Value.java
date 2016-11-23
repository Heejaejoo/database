import java.io.Serializable;

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
	
	public Value(int dt, String value){
		this.type = dt;
		this.val = value;
	}
	//if null
	public Value(int dt){
		this.type = dt;
	}
	
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
	//works correctly only correct input is inserted;
	//no exception handling
	public int getIntVal(){
		return Integer.parseInt(this.val);
	}
	
}