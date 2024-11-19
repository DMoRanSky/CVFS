package hk.edu.polyu.comp.comp2021.cvfs.model.criterion;

import hk.edu.polyu.comp.comp2021.cvfs.model.Doc;
import hk.edu.polyu.comp.comp2021.cvfs.model.Files;

import java.io.Serializable;

/**
 * is document criterion
 */
public class IsDocCri extends Cri implements Serializable {
    /**
     * construction
     */
    public IsDocCri() {
        super("IsDocument");
    }
    @Override
    public boolean eval(Files u) {
        return u instanceof Doc;
    }

    @Override
    public String getExp() {
        return "IsDocument";
    }
}
