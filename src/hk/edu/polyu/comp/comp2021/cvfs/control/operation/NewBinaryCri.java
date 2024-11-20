package hk.edu.polyu.comp.comp2021.cvfs.control.operation;

import hk.edu.polyu.comp.comp2021.cvfs.model.*;
import hk.edu.polyu.comp.comp2021.cvfs.model.criterion.*;

/**
 * Reversible Operation: new binary criterion
 */
public class NewBinaryCri implements Operation {
    private CVFS cvfs;
    private Cri v;

    /**
     * c1 = c2 op c3
     * @param n1: c1
     * @param n2: c2
     * @param op: operator
     * @param n3: c3
     * @param cvfs: whole system
     * @throws Exception -
     */
    public NewBinaryCri(String n1, String n2, String op, String n3, CVFS cvfs) throws Exception {
        this.setCvfs(cvfs);
        if (!cvfs.hasCri(n2)) {
            throw new Exception("Criterion " + n2 + " does not exist");
        }
        if (!cvfs.hasCri(n3)) {
            throw new Exception("Criterion " + n3 + " does not exist");
        }
        if (!op.equals("&&") && !op.equals("||")) {
            throw new Exception("Operator must be either '&&' or '||'");
        }
        setV(new BinaryCri(n1, cvfs.getCri(n2), cvfs.getCri(n3), op));
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
     * @return comp VFS
     */
    public CVFS getCvfs() {
        return cvfs;
    }

    /**
     * @param cvfs : comp VFS
     */
    public void setCvfs(CVFS cvfs) {
        this.cvfs = cvfs;
    }

    /**
     * @return Criterion object
     */
    public Cri getV() {
        return v;
    }

    /**
     * @param v: Criterion
     */
    public void setV(Cri v) {
        this.v = v;
    }
}
