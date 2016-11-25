/** implements 3 value logic
 * 
 * @author heejae
 *
 */
public class Logic {
	final int ISNULL = 0;
	final int ISNOTNULL = 1;
	final int NOT = 2;
	
	private int type;
	private String operand = "";
	private Value r_operand = null;
	
	public Logic(String operand, int type){
		this.operand = operand;
		this.type = type;
	}
	public Logic(String left, String right, int type){
		this.operand = left;
		
	}

}
