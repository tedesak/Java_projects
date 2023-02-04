package md2html;

public class HtmlCode extends AbstractHtmlEl {
    @Override
    public String giveHtmlMarkup() {
        return super.doHtmlMarkup("code");
    }
}
