package hk.edu.polyu.comp.comp2021.cvfs.control.operation;

import hk.edu.polyu.comp.comp2021.cvfs.model.Dir;
import hk.edu.polyu.comp.comp2021.cvfs.model.Disk;
import hk.edu.polyu.comp.comp2021.cvfs.model.Files;

/**
 * Reversible Operation: Delete a file
 */
public class Delete implements Operation{
    private String name;
    private Files u;
    private Dir cwd;
    private Disk d;

    /**
     * @param name : file name
     * @param cwd: current working directory
     * @param d: now disk
     * @throws Exception -
     */
    public Delete(String name, Dir cwd, Disk d) throws Exception {
        this.setName(name);
        this.setCwd(cwd);
        if (!cwd.getCh().containsKey(name))
            throw new Exception("file " + name + " is not exist");
        this.setU(cwd.getCh().get(name));
        this.setD(d);
    }
    @Override
    public void exe() throws Exception {
        getCwd().delete(getName());
    }

    @Override
    public void rev() throws Exception {
        getCwd().newFile(getU(), getD());
    }

    /**
     * @return file name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name : file name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return file object (to restore, which need to restore all information)
     */
    public Files getU() {
        return u;
    }

    /**
     * @param u: file object
     */
    public void setU(Files u) {
        this.u = u;
    }

    /**
     * @return current working directory
     */
    public Dir getCwd() {
        return cwd;
    }

    /**
     * @param cwd : current working directory
     */
    public void setCwd(Dir cwd) {
        this.cwd = cwd;
    }

    /**
     * @return disk now
     */
    public Disk getD() {
        return d;
    }

    /**
     * @param d : disk now
     */
    public void setD(Disk d) {
        this.d = d;
    }
}
