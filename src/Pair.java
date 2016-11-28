import java.io.Serializable;

//generate generic pair data structure

public class Pair<F, S> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2423070581232886441L;
	private F first; //first member of pair
    private S second; //second member of pair

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}