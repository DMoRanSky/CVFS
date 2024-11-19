package hk.edu.polyu.comp.comp2021.cvfs.model.criterion;

import hk.edu.polyu.comp.comp2021.cvfs.model.Files;

import static hk.edu.polyu.comp.comp2021.cvfs.utils.Assert.*;

/**
 * Criterions that check file name
 */
public class NameCri extends Cri{
    private String val;

    /**
     * @param name: criterion name
     * @param val: = val]
     * @throws Exception -
     */
    public NameCri(String name, String val) throws Exception {
        super(name);
        this.setVal(RevDoubleQuote(val));
    }

    @Override
    public boolean eval(Files u) {
        return u.getName().contains(getVal());
    }

    @Override
    public String getExp() {
        return "name contains " + getVal();
    }

    /**
     * @return name val
     */
    public String getVal() {
        return val;
    }

    /**
     * @param val : name val
     */
    public void setVal(String val) {
        this.val = val;
    }
}
