/** implements 3 value logic
 * 
 * @author heejae
 *
 */
public class Logic {
	final int TRUE = 0;
	final int FALSE = 1;
	final int UNKNOWN = 2;
	
	private int type;
	
	public Logic(int type){
		this.type = type;
	}
	
	public int getVal(){
		return this.type;
	}
	
	public boolean isTrue(){
		return this.type == 0;
	}
	public boolean isFalse(){
		return this.type == 1;
	}
	public boolean isUnknown(){
		return this.type == 2;
	}
	
	public Logic AND(Logic oth){
		if(this.isUnknown()){
			if(oth.isFalse()){
				return oth;
			}else{
				//return unknown
				return this;
			}
		}else if (oth.isUnknown()){
			if(this.isFalse()){
				return this;
			}else{
				return oth;
			}
		}else{
			if(this.isTrue() && oth.isTrue()){
				return new Logic(0);
			}else{
				return new Logic(1);
			}
		}
		
	}
	
	public Logic OR(Logic oth){
		if(this.isUnknown()){
			if(oth.isTrue()){
				return oth;
			}else{
				//return unknown
				return this;
			}
		}else if (oth.isUnknown()){
			if(this.isTrue()){
				return this;
			}else{
				return oth;
			}
		}else{
			if(this.isTrue() || oth.isTrue()){
				return new Logic(0);
			}else{
				return new Logic(1);
			}
		}
	}
	
	public Logic NOT(){
		if(this.type == 2){
			return this;
		}else{
			if(this.type == 0){
				this.type = 1;
				return this;
			}else{
				this.type = 0;
				return this;
			}
		}
	}
	
	

}
