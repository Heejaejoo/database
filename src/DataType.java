import java.io.Serializable;

public class DataType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int datatype;
	int length;
	String INT = "int";
	String DATE = "date";
	
	public int getType(){
		return this.datatype;
	}
	public int getlength(){
		return this.length;
	}
	public void printAll(){
		if(this.datatype == 1){
			System.out.printf("dataType: char, length = %d\n", this.length);
		}else{
			System.out.printf("dataType: %s\n", this.datatype == 0 ? INT: DATE);
		}
	}
	public DataType(int num){
		this.datatype = num;
	}
	public DataType(int num, int len) throws MyException{
		this.datatype = num;
		this.length = len;
		if(len<=0){
			throw new MyException(Messages.CharLengthError);
		}
	}
	public String getString(){
		if(datatype == 0){
			return INT;
		}else if(datatype == 2){
			return DATE;
		}else{
			return "char(" + String.valueOf(this.length) + ")";
		}
	}
	
	public boolean equals(DataType oth){
		if(this.datatype == 1 && oth.datatype == 1){
			return this.length == oth.length;
		}else{
			return this.datatype == oth.datatype;
		}
	}
	
	
	
}
