package hk.edu.polyu.comp.comp2021.cvfs.control.operation;

import hk.edu.polyu.comp.comp2021.cvfs.model.Dir;
import hk.edu.polyu.comp.comp2021.cvfs.model.Disk;
import hk.edu.polyu.comp.comp2021.cvfs.model.Doc;

/**
 * Reversible operation: new document
 */
public class NewDoc implements Operation{
    private Dir cwd;
    private String name;
    private String type;
    private String content;
    private Disk d;

    /**
     * @param name: document name
     * @param type: document type
     * @param content: document content
     * @param cwd: current working directory
     * @param d: disk
     */
    public NewDoc(String name, String type, String content, Dir cwd, Disk d) {
        this.setCwd(cwd);
        this.setName(name);
        this.setType(type);
        this.setContent(content);
        this.setD(d);
    }

    @Override
    public void exe() throws Exception {
        getCwd().newFile(new Doc(getName(), getType(), getContent(), getCwd()), getD());
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
     * @return document name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name: document name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return document type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type: document type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return document content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content: document content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return disk
     */
    public Disk getD() {
        return d;
    }

    /**
     * @param d: set disk
     */
    public void setD(Disk d) {
        this.d = d;
    }
}
