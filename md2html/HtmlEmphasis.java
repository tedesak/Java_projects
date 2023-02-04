package md2html;

public class HtmlEmphasis extends AbstractHtmlEl {
    @Override
    public String giveHtmlMarkup() {
        return super.doHtmlMarkup("em");
    }
}
