package io.github.swagger2markup.markup.builder;

/**
 * Created by smalvik on 03.11.2017.
 */
public enum MarkupAdmonition {
    NOTE("NOTE"),
    TIP("TIP"),
    IMPORTANT("IMPORTANT"),
    CAUTION("CAUTION"),
    WARNING("WARNING"),
    JAVA("source, java");

    private final String ad;

    private MarkupAdmonition(String ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return ad;
    }
}
