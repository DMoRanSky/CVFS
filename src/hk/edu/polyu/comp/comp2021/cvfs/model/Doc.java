package hk.edu.polyu.comp.comp2021.cvfs.model;


import java.io.Serializable;

import static hk.edu.polyu.comp.comp2021.cvfs.utils.Assert.AssertType;

/**
 * Document
 */
public class Doc extends Files implements Serializable {
    /**
     * initial size
     */
    public static final int INIT_SIZE = 40;
    private String type;
    private String content;

    /**
     * @param name: Document name
     * @param type: Document type
     * @param content: Document content
     * @param pDir: parent directory
     * @throws Exception -
     */
    public Doc(String name, String type, String content, Dir pDir) throws Exception {
        super(name, INIT_SIZE + content.length() * 2, pDir);
        AssertType(type);
        this.setType(type);
        this.setContent(content);
    }

    @Override
    public String getExp() {
        return getName() + "." + getType() + " (size: " + getSize() + ")";
    }

    /**
     * @return Document type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type: for document
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
     * @param content of document
     */
    public void setContent(String content) {
        this.content = content;
    }
}
