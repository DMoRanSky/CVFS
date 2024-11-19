package hk.edu.polyu.comp.comp2021.cvfs.control.operation;

import hk.edu.polyu.comp.comp2021.cvfs.model.Dir;

/**
 * Reverible operation: rename file
 */
public class Rename implements Operation{
    private String old;
    private String ne;
    private Dir cwd;

    /**
     * @param old: old file name
     * @param ne: new file name
     * @param cwd: current wokring directory
     */
    public Rename(String old, String ne, Dir cwd) {
        this.setOld(old);
        this.setNe(ne);
        this.setCwd(cwd);
    }
    @Override
    public void exe() throws Exception {
        getCwd().rename(getOld(), getNe());
    }

    @Override
    public void rev() throws Exception {
        getCwd().rename(getNe(), getOld());
    }

    /**
     * @return old name
     */
    public String getOld() {
        return old;
    }

    /**
     * @param old : old file name
     */
    public void setOld(String old) {
        this.old = old;
    }

    /**
     * @return new file name
     */
    public String getNe() {
        return ne;
    }

    /**
     * @param ne : new file name
     */
    public void setNe(String ne) {
        this.ne = ne;
    }

    /**
     * @return current working directory
     */
    public Dir getCwd() {
        return cwd;
    }

    public void setCwd(Dir cwd) {
        this.cwd = cwd;
    }
}
