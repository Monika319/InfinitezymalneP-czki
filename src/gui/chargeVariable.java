package gui;


/**
 * Generic version of the Charge class.
 * For adding charges to listview
 *
 * @param <T> the type of the value
 */
public class chargeVariable<T> {
    // T stands for "Type"
    private T t;

    public chargeVariable(T t) {
        this.t=t;
    }

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
    public boolean checkIfString(){
        if (this.t.getClass().getName().equals("java.lang.String")) return true;
        else return  false;
    }


    public String setString() {
        if (this.t.getClass().getName().equals("java.lang.String")) return (String) t;
        else return  t.toString();
      //  else return (T) Integer.toString(0);
    }
}