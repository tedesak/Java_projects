package md2html;

public class HtmlStrong extends AbstractHtmlEl {
    @Override
    public String giveHtmlMarkup() {
        return super.doHtmlMarkup("strong");
    }
}
