
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
	
	public Logic evaluate(Table t, int idx, boolean e) throws Exception, MyException{
		if(this.isnot()){
			return bt.evaluate(t, idx, e).NOT();
		}else{
			return bt.evaluate(t, idx, e);
		}
	}
}
