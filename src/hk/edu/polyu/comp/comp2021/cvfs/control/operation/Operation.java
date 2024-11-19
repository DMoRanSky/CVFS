package hk.edu.polyu.comp.comp2021.cvfs.control.operation;

/**
 * Reversible operator
 */
public interface Operation {
    /**
     * execute operation
     * @throws Exception -
     */
    void exe() throws Exception;

    /**
     * run reverible operation
     * @throws Exception -
     */
    void rev() throws Exception;
}
