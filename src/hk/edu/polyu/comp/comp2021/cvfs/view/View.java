package hk.edu.polyu.comp.comp2021.cvfs.view;

import hk.edu.polyu.comp.comp2021.cvfs.model.*;
import hk.edu.polyu.comp.comp2021.cvfs.model.criterion.*;

import java.util.Collection;

/**
 * CVFS View ()
 */
public class View {
    private int num;
    private int sz;
    private void dfs(Files u, Cri c, String pr) throws Exception {
        if (c == null || c.eval(u)) {
            System.out.println(pr + u.getExp());
            setNum(getNum() + 1); setSz(getSz() + u.getSize());
        }
        if (u instanceof Dir) {
            for (Files v: ((Dir) u).getCh().values()) {
                dfs(v, c, pr + "\t");
            }
        }
    }

    /**
     * out total information
     */
    public void out() {
        System.out.printf("Total files: %d; Total sizes of listed files %d\n", getNum(), getSz());
    }


    /**
     * @param u: Directory
     * @param c: Criterion (can be null)
     * @throws Exception -
     */
    public void rList(Dir u, Cri c) throws Exception {
        setNum(0); setSz(0);
        for (Files v: u.getCh().values()) {
            dfs(v, c, "");
        }
        out();
    }

    /**
     * @param u: Directory
     * @param c: Criterion (can be null)
     * @throws Exception -
     */
    public void list(Dir u, Cri c) throws Exception {
        setNum(0); setSz(0);
        for (Files v: u.getCh().values()) {
            if (c == null || c.eval(v)) {
                System.out.println(v.getExp());
                setNum(getNum() + 1); setSz(getSz() + v.getSize());
            }
        }
        out();
    }

    /**
     * @param co: Collection of criterion
     */
    public void printCri(Collection<Cri> co) {
        for (Cri c: co) {
            System.out.println(c.getName() + " : " + c.getExp());
        }
    }

    /**
     * @return num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num of file
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return size
     */
    public int getSz() {
        return sz;
    }

    /**
     * @param sz: total size of file
     */
    public void setSz(int sz) {
        this.sz = sz;
    }
}
