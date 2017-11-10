/**
 * The MIT License
 * Copyright (c) 2014 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.malvik.doc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Tag;
import io.github.swagger2markup.markup.builder.MarkupAdmonition;
import io.github.swagger2markup.markup.builder.MarkupBlockStyle;
import io.github.swagger2markup.markup.builder.MarkupDocBuilder;
import io.github.swagger2markup.markup.builder.MarkupDocBuilders;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.SafeMode;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import static io.github.swagger2markup.markup.builder.MarkupLanguage.ASCIIDOC;
import static org.asciidoctor.Asciidoctor.Factory.create;
import static org.asciidoctor.OptionsBuilder.options;

/**
 * Created by smalvik on 30.10.2017.
 */

public class Sysdoclet {

    private static MarkupDocBuilder docBuilders = MarkupDocBuilders.documentBuilder(ASCIIDOC);

    public static boolean start(RootDoc root) {
        docBuilders.sectionTitleLevel1("Java Design Patterns");
        process(root.classes());

        writeAdoc(docBuilders);
        printEpub3();

        return true;
    }

    private static void process(ClassDoc[] classDocs) {
        Arrays.stream(classDocs)
                .filter(c -> c.name().endsWith("App"))
                .forEach(classDoc -> {
                    String s = classDoc.commentText();
                    Tag[] tags = classDoc.tags("@pattern");
                    String pattern = tags.length > 0 ? tags[0].text() : classDoc.qualifiedName();
                    docBuilders.sectionTitleLevel1(pattern);
                    docBuilders.block(s, MarkupBlockStyle.PASSTHROUGH);

                    String qn = classDoc.qualifiedTypeName();
                    String module = pattern.toLowerCase();

                    docBuilders.text(
                            "include::"
                                    + getOutputLocation()
                                    + "/"
                                    + module
                                    + "/README.adoc[]");

                    docBuilders.newLine();

/*                    docBuilders.block(
                            "include::"
                                    + getOutputLocation()
                                    + "/"
                                    + module
                                    + "/src/main/java/"
                                    + qn.replace('.', '/') + ".java[tags=code]",
                            MarkupBlockStyle.LISTING, "App.java", MarkupAdmonition.JAVA);
                */});
    }

    private static void writeAdoc(MarkupDocBuilder docBuilders) {
        docBuilders.writeToFile(
                new File(getOutputLocation(), "jp").toPath(),
                Charset.forName("UTF-8"),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static void printEpub3() {
        Asciidoctor asciidoctor = create();

        File file = new File(getOutputLocation(), "index.adoc");
        asciidoctor.convertFile(file,
                options().safe(SafeMode.SAFE).backend("epub3").get());
    }

    private static String getOutputLocation() {
        return "C:/git/java-design-patterns";
    }
}
