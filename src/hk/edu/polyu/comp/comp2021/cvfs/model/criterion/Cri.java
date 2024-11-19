package hk.edu.polyu.comp.comp2021.cvfs.model.criterion;

import hk.edu.polyu.comp.comp2021.cvfs.model.Files;

import java.io.Serializable;

/**
 * Criterion
 */
public abstract class Cri implements Serializable {
    private String name;

    /**
     * @param name : Criterion name
     */
    public Cri(String name) { this.setName(name); }

    /**
     * @param u : the file needed evaluation
     * @return whether the file is meet the condition
     * @throws Exception -
     */
    abstract public boolean eval(Files u) throws Exception;

    /**
     * @return expression
     */
    abstract public String getExp();

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name : criterion name
     */
    public void setName(String name) {
        this.name = name;
    }
}