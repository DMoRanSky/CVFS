package hk.edu.polyu.comp.comp2021.cvfs.model.criterion;

import hk.edu.polyu.comp.comp2021.cvfs.model.Files;

/**
 * Binary Criterion
 */
public class BinaryCri extends Cri {
    private Cri a;
    private Cri b; private String op;

    /**
     * c(name) = a op b
     * @param name: criterion name
     * @param a -
     * @param b -
     * @param op -
     */
    public BinaryCri(String name, Cri a, Cri b, String op) {
        super(name);
        this.setA(a);
        this.setB(b);
        this.setOp(op);

    }
    @Override
    public boolean eval(Files u) throws Exception {
        if (getOp().equals("&&")) return getA().eval(u) && getB().eval(u);
        if (getOp().equals("||")) return getA().eval(u) || getB().eval(u);
        throw new Exception("Operator (" + getOp() + ") of binary criterion: " + getName() + " is undefined");
    }

    @Override
    public String getExp() {
        return "(" + getA().getExp() + ") " + getOp() + " (" + getB().getExp() + ")";
    }

    /**
     * @return cri a
     */
    public Cri getA() {
        return a;
    }

    /**
     * @param a: cri a
     */
    public void setA(Cri a) {
        this.a = a;
    }

    /**
     * @return cri b
     */
    public Cri getB() {
        return b;
    }

    /**
     * @param b : cri b
     */
    public void setB(Cri b) {
        this.b = b;
    }

    /**
     * @return op
     */
    public String getOp() {
        return op;
    }

    /**
     * @param op : operator
     */
    public void setOp(String op) {
        this.op = op;
    }
}
