package hk.edu.polyu.comp.comp2021.cvfs;

import hk.edu.polyu.comp.comp2021.cvfs.control.Controller;
import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;
import hk.edu.polyu.comp.comp2021.cvfs.view.View;

/**
 * w
 */
public class Application {

    /**
     * @param args -
     */
    public static void main(String[] args){
        CVFS cvfs = new CVFS();
        // Initialize and utilize the system
        View v = new View();
        Controller c = new Controller(cvfs, v);
        c.run();
    }
}
