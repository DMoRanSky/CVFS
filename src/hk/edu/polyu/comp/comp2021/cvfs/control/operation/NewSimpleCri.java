package hk.edu.polyu.comp.comp2021.cvfs.control.operation;

import hk.edu.polyu.comp.comp2021.cvfs.model.*;
import hk.edu.polyu.comp.comp2021.cvfs.model.criterion.*;
import hk.edu.polyu.comp.comp2021.cvfs.utils.Assert;

/**
 * reversible operation: new simple criterion
 */
public class NewSimpleCri implements Operation {
    private String cName;
    private String aName;
    private String op;
    private String val;
    private Cri v;
    private CVFS cvfs;

    /**
     * c1 = aName operator val
     * @param cName: c1
     * @param aName: attribute name
     * @param op: operator
     * @param val: value
     * @param cvfs: COMP VFS
     * @throws Exception -
     */
    public NewSimpleCri(String cName, String aName, String op, String val, CVFS cvfs) throws Exception {
        if (!(cName.length() == 2)) throw new Exception("Criterion name length must be 2");
        Assert.checkLetter(cName);

        this.setcName(cName);
        this.setaName(aName);
        this.setOp(op);
        this.setVal(val);
        this.setCvfs(cvfs);
        if (aName.equals("name")) {
            setV(new NameCri(cName, val));
        } else if (aName.equals("type")) {
            setV(new TypeCri(cName, val));
        } else if (aName.equals("size")) {
            try {
                int nv = Integer.parseInt(val);
                setV(new SizeCri(cName, op, nv));
            } catch (NumberFormatException e) {
                throw new Exception(val + " is not a number");
            }
        } else throw new Exception("Attribute name is incorrect");
    }

    @Override
    public void exe() throws Exception {
        getCvfs().addCri(getV());
    }

    @Override
    public void rev() throws Exception {
        getCvfs().delCri(getV().getName());
    }

    /**
     * @return criterion name
     */
    public String getcName() {
        return cName;
    }

    /**
     * @param cName : cri name
     */
    public void setcName(String cName) {
        this.cName = cName;
    }

    /**
     * @return aName
     */
    public String getaName() {
        return aName;
    }

    /**
     * @param aName : attribute name
     */
    public void setaName(String aName) {
        this.aName = aName;
    }

    /**
     * @return operator
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

    /**
     * @return value
     */
    public String getVal() {
        return val;
    }

    /**
     * @param val : value
     */
    public void setVal(String val) {
        this.val = val;
    }

    /**
     * @return value
     */
    public Cri getV() {
        return v;
    }

    /**
     * @param v : value
     */
    public void setV(Cri v) {
        this.v = v;
    }

    /**
     * @return COMP VFS
     */
    public CVFS getCvfs() {
        return cvfs;
    }

    /**
     * @param cvfs : COMP VFS
     */
    public void setCvfs(CVFS cvfs) {
        this.cvfs = cvfs;
    }
}
