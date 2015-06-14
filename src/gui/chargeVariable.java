package gui;

/**
 * Generic version of the Charge class.
 * For adding charges(as various types) to listview
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
        return this.t.getClass().getName().equals("java.lang.String");
    }


    public String setString() {
        if (this.t.getClass().getName().equals("java.lang.String")) return (String) t;
        else return  t.toString();
    }
}