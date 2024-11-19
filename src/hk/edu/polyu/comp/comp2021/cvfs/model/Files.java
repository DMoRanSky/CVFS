package hk.edu.polyu.comp.comp2021.cvfs.model;

import java.io.Serializable;

import static hk.edu.polyu.comp.comp2021.cvfs.utils.Assert.*;

/**
 * Files (including directory and document)
 */
abstract public class Files implements Serializable {
    private String name;
    private int size;
    private Files pDir;

    /**
     * @param name: File name
     * @param size: File size
     * @param pDir: parent directory
     * @throws Exception -
     */
    public Files(String name, int size, Files pDir) throws Exception {
        AssertDigitLetter(name);
        AssertLength(name, 10);
        this.setName(name);
        this.setSize(size);
        this.setpDir(pDir);
    }

    /**
     * @return get expression string
     */
    abstract public String getExp();

    /**
     * @return file name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name: file name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return file size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size : file size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return parent directory
     */
    public Files getpDir() {
        return pDir;
    }

    /**
     * @param pDir : parent directory
     */
    public void setpDir(Files pDir) {
        this.pDir = pDir;
    }
}
