package hk.edu.polyu.comp.comp2021.cvfs.control;

import hk.edu.polyu.comp.comp2021.cvfs.control.operation.*;
import hk.edu.polyu.comp.comp2021.cvfs.model.*;
import hk.edu.polyu.comp.comp2021.cvfs.model.criterion.*;
import hk.edu.polyu.comp.comp2021.cvfs.view.*;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 */
public class Controller {
    private CVFS s;
    private boolean clrRedo;
    private Stack<Operation> un;
    private Stack<Operation> re;
    private View v;

    /**
     * @param cvfs : COMP VFS
     * @param v: View Module (From MVC design)
     */
    public Controller(CVFS cvfs, View v) {
        this.setS(cvfs);
        this.setV(v);
        setUn(new Stack<>());
        setRe(new Stack<>());
    }

    /**
     * push a operation into undo stack
     * @param u : Reversible operation
     * @throws Exception -
     */
    public void push(Operation u) throws Exception {
        getUn().push(u);
        u.exe(); getUn().push(u); setClrRedo(true);
    }

    /**
     * Core control function
     */
    public void run() {
        Scanner readLine = new Scanner(System.in);
        while (true) {
            String err = "Your input command is invalid.";
            try {
                setClrRedo(false);
                String lines = readLine.nextLine();
                Scanner sc = new Scanner(lines);
                String c = sc.next();
                if (c.equals("newDisk")) {
                    err = "The format is wrong, you should write: 'newDisk v' (v must be a number)";
                    int v = sc.nextInt();
                    getS().setD(new Disk(v));
                    getUn().clear();
                    getRe().clear();
                } else if (c.equals("newDoc")) {
                    err = "The format is wrong, you should write: 'newDoc name type content'";
                    err += "\n(name, type must be string)";
                    String dn = sc.next(), dt = sc.next(), dc = sc.nextLine();
                    push(new NewDoc(dn, dt, dc, getS().getD().getCwd(), getS().getD()));
                } else if (c.equals("newDir")) {
                    err = "The format is wrong, you should write: 'newDir name'";
                    err += "\n(name must be string)";
                    String dn = sc.next();
                    push(new NewDir(dn, getS().getD().getCwd(), getS().getD()));
                } else if (c.equals("delete")) {
                    err = "The format is wrong, you should write: 'delete name'";
                    err += "\n(name must be string)";
                    String n = sc.next();
                    push(new Delete(n, getS().getD().getCwd(), getS().getD()));
                } else if (c.equals("rename")) {
                    err = "The format is wrong, you should write: 'rename oldName newName'";
                    err += "\n(name must be string, no suffix)";
                    String old = sc.next(), ne = sc.next();
                    push(new Rename(old, ne, getS().getD().getCwd()));
                } else if (c.equals("changeDir")) {
                    err = "The format is wrong, you should write: 'changeDir v'";
                    err += "\n(v must be file name or '..')";
                    String n = sc.next();
                    push(new ChangeDir(n, getS().getD().getCwd(), getS().getD()));
                } else if (c.equals("list")) {
                    getV().list(getS().getD().getCwd(), null);
                } else if (c.equals("rList")) {
                    getV().rList(getS().getD().getCwd(), null);
                } else if (c.equals("newSimpleCri")) {
                    err = "The format is wrong, you should write: 'newSimpleCri criterionName attributeName operator value'";
                    String cn = sc.next(), an = sc.next(), op = sc.next(), val = sc.next();
                    push(new NewSimpleCri(cn, an, op, val, getS()));
                } else if (c.equals("newNegation")) {
                    err = "The format is wrong, you should write: 'newNegation c1Name c2Name'";
                    err += "\n(c1 = !c2)";
                    String c1 = sc.next(), c2 = sc.next();
                    push(new NewNeg(c1, c2, getS()));
                } else if (c.equals("newBinaryCri")) {
                    try {
                        String c1 = sc.next(), c2 = sc.next(), op = sc.next(), c3 = sc.next();
                        if (getS().hasCri(c1)) {
                            throw new Exception("Criterion name " + c1 + " is already exist");
                        }
                        if (!getS().hasCri(c2) || !getS().hasCri(c3)) {
                            throw new Exception("Criterion not found");
                        }
                        push(new NewBinaryCri(c1, c2, op, c3, getS()));
                    } catch (Exception e) {
                        if (e.getMessage() != null && e.getMessage().contains("already exist")) {
                            System.out.println(e.getMessage());
                        } else if (e.getMessage() != null && e.getMessage().contains("Criterion not found")) {
                            System.out.println("System error: Criterion not found");
                        } else {
                            System.out.println("The format is wrong, you should write: 'newBinaryCri c1Name c2Name operator c3Name'");
                            System.out.println("(c1 = c2 op c3)");
                        }
                    }
                } else if (c.equals("printAllCriteria")) {
                    getV().printCri(getS().getAllCri().values());
                } else if (c.equals("search")) {
                    err = "The format is wrong, you should write: 'search criterionName'";
                    String n = sc.next();
                    Cri cr = getS().getCri(n);
                    getV().list(getS().getD().getCwd(), cr);
                } else if (c.equals("rSearch")) {
                    err = "The format is wrong, you should write: 'rSearch criterionName'";
                    String n = sc.next();
                    Cri cr = getS().getCri(n);
                    getV().rList(getS().getD().getCwd(), cr);
                } else if (c.equals("save")) {
                    String path = sc.nextLine();
                    save(path);
                } else if (c.equals("load")) {
                    String path = sc.nextLine();
                    load(path);
                } else if (c.equals("undo")) {
                    Operation u = getUn().pop();
                    u.rev();
                    getRe().push(u);
                } else if (c.equals("redo")) {
                    Operation u = getRe().pop();
                    u.exe();
                    getUn().push(u);
                } else if (c.equals("quit")) {
                    break;
                } else {
                    throw new Exception("Unexpected command name");
                }
                if (isClrRedo()) getRe().clear();
            } catch (Exception e) {
                System.out.println(err);
                System.out.println("System error: " + e.getMessage());
            }
        }
    }

    /**
     * @param path : save path (can be empty)
     * @throws Exception -
     */
    public void save(String path) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(getS());
    }

    /**
     * @param path : load path (like: D:/a.bin, must be exist)
     * @throws Exception -
     */
    public void load(String path) throws Exception {
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        setS((CVFS) in.readObject());
        getUn().clear(); getRe().clear();
    }

    /**
     * @return COMP VFS
     */
    public CVFS getS() {
        return s;
    }

    /**
     * @param s : COMP VFS
     */
    public void setS(CVFS s) {
        this.s = s;
    }

    /**
     * @return : whether we need to clear redo stack
     */
    public boolean isClrRedo() {
        return clrRedo;
    }

    /**
     * @param clrRedo: whether we need to clear redo stack
     */
    public void setClrRedo(boolean clrRedo) {
        this.clrRedo = clrRedo;
    }

    /**
     * @return undo stack
     */
    public Stack<Operation> getUn() {
        return un;
    }

    /**
     * @param un : undo stack
     */
    public void setUn(Stack<Operation> un) {
        this.un = un;
    }

    /**
     * @return get redo stack
     */
    public Stack<Operation> getRe() {
        return re;
    }

    /**
     * @param re : redo stack
     */
    public void setRe(Stack<Operation> re) {
        this.re = re;
    }

    /**
     * @return view
     */
    public View getV() {
        return v;
    }

    /**
     * @param v : view
     */
    public void setV(View v) {
        this.v = v;
    }
}
