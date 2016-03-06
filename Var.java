package dv.utils;

/*
 * Custom type for logger
 * Var<Number> tmp = new Var<Number>(123.456);
 * Var<String[][]> tmp = new Var<String[][]>(new String[][] {{"a", "b"}, {"c", "d"}});
 * List<String[]> list = new ArrayList<String[]>();
 * list.add(new String[] {"qwer", "asdf"});
 * list.add(new String[] {"zxvc"});
 * Var<List<String[]>> t4 = new Var<List<String[]>>(list);
 */
public class Var<T> {
    private T __element;
    public String type;

    public T get() {
        return this.__element;
    }

    public void set(T val) {
        this.__element = val;
    }

    public Var(T val) {
        this.__element = val;
        try {
            this.type = val.getClass().toString();
        } catch (Exception ex) {
            this.type = "null";
        }
    }
}