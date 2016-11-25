
public class BooleanFactor {
	private BooleanTest bt;
	private boolean not = false;
	
	public BooleanFactor(BooleanTest t){
		this.bt = t;
	}
	
	public void setnot(){
		this.not = true;
	}
	
	public boolean isnot(){
		return this.not;
	}
}
