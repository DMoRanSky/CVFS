package hk.edu.polyu.comp.comp2021.cvfs.control.operation;

import hk.edu.polyu.comp.comp2021.cvfs.model.Dir;
import hk.edu.polyu.comp.comp2021.cvfs.model.Disk;

/**
 * Reversible Operation: New directory
 */
public class NewDir implements Operation{
    private Dir cwd;
    private String name;
    private Disk d;

    /**
     * @param name: directory name
     * @param cwd: current working directory
     * @param d: disk
     */
    public NewDir(String name, Dir cwd, Disk d) {
        this.setCwd(cwd);
        this.setName(name);
        this.setD(d);
    }
    @Override
    public void exe() throws Exception {
        getCwd().newFile(new Dir(getName(), getCwd()), getD());
    }

    @Override
    public void rev() throws Exception {
        getCwd().delete(getName());
    }

    /**
     * @return cwd
     */
    public Dir getCwd() {
        return cwd;
    }

    /**
     * @param cwd: current working directory
     */
    public void setCwd(Dir cwd) {
        this.cwd = cwd;
    }

    /**
     * @return : folder name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name: folder name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return disk
     */
    public Disk getD() {
        return d;
    }

    /**
     * @param d : disk
     */
    public void setD(Disk d) {
        this.d = d;
    }
}
