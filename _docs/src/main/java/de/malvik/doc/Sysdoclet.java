package de.malvik.doc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;
import io.github.swagger2markup.markup.builder.MarkupDocBuilder;
import io.github.swagger2markup.markup.builder.MarkupDocBuilders;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import static io.github.swagger2markup.markup.builder.MarkupLanguage.ASCIIDOC;

/**
 * Created by smalvik on 30.10.2017.
 */
public class Sysdoclet {

    public static boolean start(RootDoc root) {
        ClassDoc[] classDocs = root.classes();
        Arrays.stream(classDocs).forEach(classDoc -> process(classDoc));
        return true;
    }

    private static void process(ClassDoc classDoc) {
        String s = classDoc.commentText();
        MarkupDocBuilder docBuilders = MarkupDocBuilders.documentBuilder(ASCIIDOC);
        docBuilders.sectionTitleLevel2(classDoc.qualifiedTypeName());
        docBuilders.paragraph(s);
        docBuilders.writeToFile(new File("c:\\tml\\index").toPath(), Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
