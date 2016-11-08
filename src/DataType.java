
public class DataType {
	int datatype;
	int length;
	
	public int getType(){
		return this.datatype;
	}
	public int getlength(){
		return this.length;
	}

	public DataType(int num){
		this.datatype = num;
	}
	public DataType(int num, int len){
		this.datatype = num;
		this.length = len;
	}
}
