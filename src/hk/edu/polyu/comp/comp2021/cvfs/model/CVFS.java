package hk.edu.polyu.comp.comp2021.cvfs.model;

import hk.edu.polyu.comp.comp2021.cvfs.model.criterion.Cri;
import hk.edu.polyu.comp.comp2021.cvfs.model.criterion.IsDocCri;


import java.io.Serializable;
import java.util.HashMap;

/**
 * COMP VFS
 */
public class CVFS implements Serializable {
    private Disk d;
    private HashMap<String, Cri> allCri;

    /**
     * Constructor
     */
    public CVFS(){
        setAllCri(new HashMap<>());
        getAllCri().put("IsDocument", new IsDocCri());
    }

    /**
     * @param name: name
     * @throws Exception -
     * @return criterion obj
     */
    public Cri getCri(String name) throws Exception {
        if (getAllCri().containsKey(name)){
            return getAllCri().get(name);
        } else throw new Exception("Criterion " + name + " is not exist");
    }

    /**
     * @param u: criterion object
     * @throws Exception -
     */
    public void addCri(Cri u) throws Exception {
        if (getAllCri().containsKey(u.getName()))
            throw new Exception("Criterion name " + u.getName() + " is already exist");
        getAllCri().put(u.getName(), u);
    }

    /**
     * @param u : criterion name
     * @throws Exception -
     */
    public void delCri(String u) throws Exception {
        if (!getAllCri().containsKey(u))
            throw new Exception("Criterion name " + u + " is not exist");
        getAllCri().remove(u);
    }

    /**
     * @param name: for criterion
     * @return whether has this criterion
     */
    public boolean hasCri(String name) {
        return getAllCri().containsKey(name);
    }

    /**
     * @return disk
     */
    public Disk getD() {
        return d;
    }

    /**
     * @param d : set disk
     */
    public void setD(Disk d) {
        this.d = d;
    }

    /**
     * @return all critertion (HashMap)
     */
    public HashMap<String, Cri> getAllCri() {
        return allCri;
    }

    /**
     * @param allCri: set HashMap
     */
    public void setAllCri(HashMap<String, Cri> allCri) {
        this.allCri = allCri;
    }
}
