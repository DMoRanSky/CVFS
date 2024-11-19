package hk.edu.polyu.comp.comp2021.cvfs.control.operation;

import hk.edu.polyu.comp.comp2021.cvfs.model.*;
import hk.edu.polyu.comp.comp2021.cvfs.model.criterion.*;

/**
 * reversible operation: new negation
 */
public class NewNeg implements Operation {
    private CVFS cvfs;
    private Cri v;
    private String name;

    /**
     * c1 = !c2
     * @param n1: c1
     * @param n2: c2
     * @param cvfs: COMP VFS
     * @throws Exception -
     */
    public NewNeg(String n1, String n2, CVFS cvfs) throws Exception {
        this.setCvfs(cvfs);
        if (!cvfs.hasCri(n2))
            throw new Exception("Criterion " + n2 + " is not exist");
        setV(cvfs.getCri(n2));
        this.setName(n1);
    }
    @Override
    public void exe() throws Exception {
        Cri ne = new NegCri(getName(), getV());
        getCvfs().addCri(ne);
    }

    @Override
    public void rev() throws Exception {
        getCvfs().delCri(getName());
    }

    /**
     * @return COMP VFS
     */
    public CVFS getCvfs() {
        return cvfs;
    }

    /**
     * @param cvfs: COMP VFS
     */
    public void setCvfs(CVFS cvfs) {
        this.cvfs = cvfs;
    }

    /**
     * @return criterion
     */
    public Cri getV() {
        return v;
    }

    /**
     * @param v : criterion
     */
    public void setV(Cri v) {
        this.v = v;
    }

    /**
     * @return criterion name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name : cri name
     */
    public void setName(String name) {
        this.name = name;
    }
}
