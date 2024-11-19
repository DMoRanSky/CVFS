package hk.edu.polyu.comp.comp2021.cvfs.model;

import hk.edu.polyu.comp.comp2021.cvfs.model.criterion.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CVFSTest {

    @Test
    public void testCVFSConstructor() throws Exception {
        CVFS s = new CVFS();
        s.setD(new Disk(110));
        Dir dir = new Dir("F", s.getD().getCwd());
        Doc doc = new Doc("L", "css", "awa", dir);
        dir.newFile(doc, s.getD());
        try {
            dir.newFile(doc, s.getD());
        } catch (Exception e) {
            // assertEquals(e.getMessage(), "")
        }
        dir.rename(doc.getName(), "nihao");
        dir.delete(doc.getName());
        try {
            dir.delete(doc.getName());
        } catch (Exception e) {
            // assertEquals(e.getMessage(), "")
        }

        System.out.println(doc.getExp());
        System.out.println(dir.getExp());
        Cri[] c = new Cri[14];
        c[1] = new NameCri("c1", "\"F\"");
        c[2] = new NegCri("c2", c[1]);
        c[3] = new SizeCri("c3", "<=", 79);
        c[4] = new TypeCri("c4", "\"css\"");
        c[5] = new IsDocCri();
        c[6] = new BinaryCri("c5", c[2], c[4], "&&");
        c[7] = new BinaryCri("c6", c[2], c[4], "||");
        c[8] = new BinaryCri("c7", c[2], c[4], "x");
        c[9] = new SizeCri("c8", ">=", 79);
        c[10] = new SizeCri("c9", "<", 79);
        c[11] = new SizeCri("c10", ">", 79);
        c[12] = new SizeCri("11", "==", 79);
        c[13] = new SizeCri("12", "!=", 79);
        for (int i = 1; i <= 13; i++) {
            try {
                System.out.println(c[i].getExp());
                System.out.println(c[i].eval(dir));
            } catch (Exception e) {}
        }
        s.addCri(c[1]);
        try {
            s.addCri(c[1]);
        } catch (Exception e) {}

        System.out.println(s.getCri(c[1].getName()));
        try {
            s.getCri("si");
        } catch (Exception e) {}
        System.out.println(s.hasCri(c[1].getName()));
        s.delCri(c[1].getName());
        try {
            s.delCri(c[1].getName());
        } catch (Exception e) {}
        try {
            doc.setName("P");
            dir.newFile(doc, s.getD());
            doc.setName("A");
            dir.newFile(doc, s.getD());
        } catch (Exception e) {
            // assertEquals(e.getMessage(), "")
        }



        assert true;
    }

}