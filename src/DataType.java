
public class DataType {
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
	public DataType(int num, int len){
		this.datatype = num;
		this.length = len;
	}
}
