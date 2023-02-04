package md2html;

public abstract class AbstractHtmlEl implements HtmlEl {
    boolean wasIt = false;

    protected String doHtmlMarkup(String s) {
        wasIt = !wasIt;
        return wasIt ? "<" + s + ">" : "</" + s + ">";
    }
    @Override
    public abstract String giveHtmlMarkup();
}
