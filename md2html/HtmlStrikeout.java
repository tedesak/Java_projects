package md2html;

public class HtmlStrikeout extends AbstractHtmlEl {
    @Override
    public String giveHtmlMarkup() {
        return super.doHtmlMarkup("s");
    }
}
