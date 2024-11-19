package hk.edu.polyu.comp.comp2021.cvfs.model;


import java.util.HashMap;

/**
 * Directory
 */
public class Dir extends Files{
    /**
     * initial size
     */
    public static final int INIT_SIZE = 40;
    private HashMap<String, Files> ch;

    /**
     * @param u: Directory object
     * @param v: size += v
     */
    void upd(Dir u, int v) {
        if (u == null) return;
        u.setSize(u.getSize() + v);
        upd((Dir) u.getpDir(), v);
    }

    /**
     * @param name: Directory name
     * @param pDir: parent directory
     * @throws Exception -
     */
    public Dir(String name, Dir pDir) throws Exception {
        super(name, pDir == null ? 0 : INIT_SIZE, pDir);
        setCh(new HashMap<>());
    }



    /**
     * @param f: file objeect
     * @param d: disk object
     * @throws Exception -
     */
    public void newFile(Files f, Disk d) throws Exception {
        if (getCh().containsKey(f.getName()))
            throw new Exception("file: " + f.getName() + " is already exist");
        if (d.getRoot().getSize() + f.getSize() > d.getMaxSize())
            throw new Exception("file: " + f.getName() + " is too big that exceed max size of disk");
        getCh().put(f.getName(), f);
        upd(this, f.getSize());

    }

    /**
     * @param name: file name
     * @throws Exception -
     */
    public void delete(String name) throws Exception {
        if (!getCh().containsKey(name))
            throw new Exception("file: " + name + " is not exist");
        int sz = getCh().get(name).getSize();
        getCh().remove(name);
        upd(this, -sz);
    }

    /**
     * @param old: old file name
     * @param ne: new file name
     * @throws Exception -
     */
    public void rename(String old, String ne) throws Exception {
        if (!getCh().containsKey(old))
            throw new Exception("file: " + old + " is not exist");
        Files v = getCh().get(old);
        getCh().remove(old);
        v.setName(ne);
        getCh().put(ne, v);
    }

    @Override
    public String getExp() {
        return getName() + " (size: " + getSize() + ")";
    }

    /**
     * @return list of child files (HashMap)
     */
    public HashMap<String, Files> getCh() {
        return ch;
    }

    /**
     * @param ch :childs (files contained in this folder)
     */
    public void setCh(HashMap<String, Files> ch) {
        this.ch = ch;
    }
}
