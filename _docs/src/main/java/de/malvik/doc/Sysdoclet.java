package de.malvik.doc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

import java.util.Arrays;

/**
 * Created by smalvik on 30.10.2017.
 */
public class Sysdoclet {

    public static boolean start(RootDoc root){
        ClassDoc[] classDocs = root.classes();
        Arrays.stream(classDocs).forEach(classDoc -> process(classDoc));
        return true;
    }

    private static void process(ClassDoc classDoc) {
        String s = classDoc.commentText();
        System.out.println(s);
    }
}
