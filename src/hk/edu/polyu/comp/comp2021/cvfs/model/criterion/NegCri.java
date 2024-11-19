package hk.edu.polyu.comp.comp2021.cvfs.model.criterion;

import hk.edu.polyu.comp.comp2021.cvfs.model.Files;

/**
 * Negation Criterion
 */
public class NegCri extends Cri{
    private Cri v;

    /**
     * @param name : c1 name
     * @param v: c2 object
     */
    public NegCri(String name, Cri v) {
        super(name);
        this.setV(v);
    }

    @Override
    public boolean eval(Files u) throws Exception {
        return !getV().eval(u);
    }

    @Override
    public String getExp() {
        return "not (" + getV().getExp() + ")";
    }

    /**
     * @return c2
     */
    public Cri getV() {
        return v;
    }

    /**
     * @param v c2
     */
    public void setV(Cri v) {
        this.v = v;
    }
}
