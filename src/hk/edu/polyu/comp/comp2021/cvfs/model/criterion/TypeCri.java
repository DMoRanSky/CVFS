package hk.edu.polyu.comp.comp2021.cvfs.model.criterion;

import hk.edu.polyu.comp.comp2021.cvfs.model.*;
import static hk.edu.polyu.comp.comp2021.cvfs.utils.Assert.RevDoubleQuote;

/**
 * Criterions that check type
 */
public class TypeCri extends Cri{
    private String val;

    /**
     * @param name: criterion name
     * @param val: type name
     * @throws Exception -
     */
    public TypeCri(String name, String val) throws Exception {
        super(name);
        this.setVal(RevDoubleQuote(val));
    }

    @Override
    public boolean eval(Files u) {
        if (u instanceof Doc)
            return ((Doc) u).getType().equals(getVal());
        else return false;
    }

    @Override
    public String getExp() {
        return "type = " + getVal();
    }

    /**
     * @return type name
     */
    public String getVal() {
        return val;
    }

    /**
     * @param val : type name
     */
    public void setVal(String val) {
        this.val = val;
    }
}
