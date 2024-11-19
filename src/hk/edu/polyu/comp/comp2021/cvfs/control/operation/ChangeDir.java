package hk.edu.polyu.comp.comp2021.cvfs.control.operation;

import hk.edu.polyu.comp.comp2021.cvfs.model.*;

import java.io.Serializable;

/**
 * Reversible Operation: Change Direction
 */
public class ChangeDir implements Operation, Serializable {
    private Dir pr;
    private Dir nx;
    private Disk d;

    /**
     * @param name: ".." -> back else the inner folder name
     * @param cwd: current working directory
     * @param d: disk now
     * @throws Exception -
     */
    public ChangeDir(String name, Dir cwd, Disk d) throws Exception {
        this.setD(d);
        setPr(cwd);
        if (name.equals("..")) {
            if (getPr().getpDir() == null) throw new Exception("Current working directory is root");
            setNx((Dir) getPr().getpDir());
        } else {
            if (!getPr().getCh().containsKey(name)) throw new Exception("Directory " + name + " is not exist");
            Files v = getPr().getCh().get(name);
            if (!(v instanceof Dir)) throw new Exception(name + " is not a directory");
            setNx((Dir) v);
        }
    }
    @Override
    public void exe() throws Exception {
        getD().setCwd(getNx());
    }

    @Override
    public void rev() throws Exception {
        getD().setCwd(getPr());
    }

    /**
     * @return pr
     */
    public Dir getPr() {
        return pr;
    }

    /**
     * @param pr: previous working directory
     */
    public void setPr(Dir pr) {
        this.pr = pr;
    }


    /**
     * @return nx
     */
    public Dir getNx() {
        return nx;
    }

    /**
     * @param nx: next (new) working directory
     */
    public void setNx(Dir nx) {
        this.nx = nx;
    }

    /**
     * @return disk now
     */
    public Disk getD() {
        return d;
    }

    /**
     * @param d: disk now
     */
    public void setD(Disk d) {
        this.d = d;
    }
}
