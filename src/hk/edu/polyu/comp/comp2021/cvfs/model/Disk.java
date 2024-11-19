package hk.edu.polyu.comp.comp2021.cvfs.model;

import java.io.Serializable;

/**
 * Disk
 */
public class Disk implements Serializable {
    private int maxSize;
    private Dir root;
    private Dir cwd;

    /**
     * @param maxSize: max size of disk
     * @throws Exception -
     */
    public Disk(int maxSize) throws Exception {
        this.setMaxSize(maxSize);
        setRoot(new Dir("root", null));
        setCwd(getRoot());
    }

    /**
     * @return disk max size
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * @param maxSize: disk max size
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * @return root directory
     */
    public Dir getRoot() {
        return root;
    }

    /**
     * @param root directory
     */
    public void setRoot(Dir root) {
        this.root = root;
    }

    /**
     * @return current working directory
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
}
