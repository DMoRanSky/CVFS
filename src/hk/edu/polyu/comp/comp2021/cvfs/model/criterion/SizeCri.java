package hk.edu.polyu.comp.comp2021.cvfs.model.criterion;

import hk.edu.polyu.comp.comp2021.cvfs.model.*;

/**
 * Criterion that checks size
 */
public class SizeCri extends Cri{
    private String op; private int val;
    private static final String[] ops =  {">", "<", ">=", "<=", "==", "!="};

    /**
     * @param name: criterion name
     * @param op: operator
     * @param val: size
     * @throws Exception -
     */
    public SizeCri(String name, String op, int val) throws Exception {
        super(name);
        this.setOp(op);
        this.setVal(val);
        boolean ok = false;
        for (String u : getOps())
            if (u.equals(op)) ok = true;
        if (!ok) throw new Exception("Operator (" + op + ") of size criterion: " + name + " is undefined");
    }

    /**
     * @return list of operator
     */
    public static String[] getOps() {
        return ops;
    }

    @Override
    public boolean eval(Files u) throws Exception {
        int z = u.getSize();
        if (getOp().equals(">")) return z > getVal();
        if (getOp().equals("<")) return z < getVal();
        if (getOp().equals(">=")) return z >= getVal();
        if (getOp().equals("<=")) return z <= getVal();
        if (getOp().equals("==")) return z == getVal();
        if (getOp().equals("!=")) return z != getVal();
        // impossible to happen.
        throw new Exception("Operator (" + getOp() + ") of size criterion: " + this.getName() + " is undefined");
    }

    @Override
    public String getExp() {
        return "size " + getOp() + " " + getVal();
    }

    /**
     * @return operator
     */
    public String getOp() {
        return op;
    }

    /**
     * @param op: operator
     */
    public void setOp(String op) {
        this.op = op;
    }

    /**
     * @return size
     */
    public int getVal() {
        return val;
    }

    /**
     * @param val : size
     */
    public void setVal(int val) {
        this.val = val;
    }
}
