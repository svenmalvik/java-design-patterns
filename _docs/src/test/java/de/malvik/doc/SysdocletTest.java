package de.malvik.doc;

import org.junit.Test;

import java.io.File;

/**
 * Created by smalvik on 01.11.2017.
 */
public class SysdocletTest {

    @Test
    public void invokeDoclet() {
        final EasyDoclet easyDoclet = new EasyDoclet(
                new File("C:\\git\\java-design-patterns\\abstract-factory"),
                new File("C:\\git\\java-design-patterns\\abstract-factory\\src\\main\\java\\com\\iluwatar\\abstractfactory\\App.java"));

        Sysdoclet.start(easyDoclet.getRootDoc());
    }
}