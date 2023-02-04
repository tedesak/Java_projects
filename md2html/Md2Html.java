package md2html;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Md2Html {

    private int x = 0;


    private static String markdownToHtml(final char symbPrev, final char symbActual, final char symbNext,
                                         HtmlEl strikeout, HtmlEl strong, HtmlEl emphasis, HtmlEl code,
                                         AtomInteger index, char ERROR_ELEMENT) { // :NOTE: параметры капсом (((
        final StringBuilder htmlString = new StringBuilder();
        if (symbActual == '*' || symbActual == '_') {
            if ((symbPrev == ' ' || symbPrev == ERROR_ELEMENT || symbPrev == '\n') && (symbNext == ' ' || symbNext == ERROR_ELEMENT || symbNext == '\n')) {
                htmlString.append(symbActual);
            } else if (symbActual == symbNext) {
                htmlString.append(strong.giveHtmlMarkup());
                index.inrease(1);
            } else {
                htmlString.append(emphasis.giveHtmlMarkup());
            }
        } else if (symbActual == '-' && symbNext == '-') {
            htmlString.append(strikeout.giveHtmlMarkup());
            index.inrease(1);
        } else if (symbActual == '`') {
            htmlString.append(code.giveHtmlMarkup());
        } else {
            htmlString.append(symbActual);
        }
        return htmlString.toString();
    }

    private static String workWithSpecialCharacter(final char specialCharacter) {
        if (specialCharacter == '<') {
            return "&lt;";
        } else if(specialCharacter == '>') {
            return "&gt;";
        } else {
            return "&amp;";
        }
    }

    public static void main(final String[] args) {
        try {
            final Scanner scanner = new Scanner(new FileInputStream(args[0]), StandardCharsets.UTF_8);
            try {
                final Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]),
                    StandardCharsets.UTF_8
                ));
                try {
                    final char ERROR_ELEMENT = '\\';
                    while (scanner.hasNext()) {
                        AtomInteger index;
                        final StringBuilder markdownString = new StringBuilder();
                        String checkString = scanner.nextLine();
                        while (checkString.isEmpty()) {
                            checkString = scanner.nextLine();
                        }
                        for (;!checkString.isEmpty(); checkString = scanner.nextLine()) {
                            markdownString.append(checkString);
                            markdownString.append("\n");
                            if (!scanner.hasNextLine()) {
                                break;
                            }
                        }
                        markdownString.deleteCharAt(markdownString.length() - 1);
                        int levelHeader = 0;
                        for (; markdownString.charAt(levelHeader) == '#'; levelHeader++);
                        String endString;
                        if (markdownString.charAt(levelHeader) == ' ' && levelHeader > 0) { // :NOTE: мб таб
                            index = new AtomInteger(levelHeader + 1);
                            writer.write("<h" + levelHeader + ">");
                            endString = "</h" + levelHeader + ">";
                        } else {
                            index = new AtomInteger(0);
                            levelHeader = -1;
                            writer.write("<p>");
                            endString = "</p>";
                        }
                        boolean wasLink = false;
                        HtmlStrikeout strikeout = new HtmlStrikeout();
                        HtmlStrong strong = new HtmlStrong();
                        HtmlEmphasis emphasis = new HtmlEmphasis();
                        HtmlCode code = new HtmlCode();
                        final StringBuilder linkBuilder = new StringBuilder();
                        while (index.getVal() != markdownString.length()) {
                            final char symbPrev = index.getVal() - levelHeader == 1 ? ERROR_ELEMENT : markdownString.charAt(index.getVal() - 1),
                                    symbActual = markdownString.charAt(index.getVal()),
                                    symbNext = index.getVal() + 1 == markdownString.length() ? ERROR_ELEMENT : markdownString.charAt(index.getVal() + 1); // :NOTE: pls no
                            wasLink = wasLink || symbActual == '[';
                            if (wasLink) {
                                if (symbActual == ']') {
                                    index.inrease(2);
                                    writer.write("<a href='");
                                    for (char printEl = markdownString.charAt(index.getVal()); printEl != ')'; printEl = markdownString.charAt(index.getVal())) {
                                        writer.write(printEl);
                                        index.inrease(1);
                                    }
                                    writer.write("'>" + linkBuilder.toString() + "</a>");
                                    linkBuilder.setLength(0);
                                    wasLink = false;
                                } else if (symbActual != '[') {
                                    linkBuilder.append(markdownToHtml(symbPrev, symbActual, symbNext, strikeout, strong, emphasis, code, index, ERROR_ELEMENT));
                                }
                            } else if (symbActual == '\\') {
                                writer.write(symbNext);
                                index.inrease(1);
                            } else if (symbActual == '<' || symbActual == '>' || symbActual == '&') {
                                writer.write(workWithSpecialCharacter(symbActual));
                            } else {
                                writer.write(markdownToHtml(symbPrev, symbActual, symbNext, strikeout, strong, emphasis, code, index, ERROR_ELEMENT)); // :NOTE: atom wrap
                            }
                            index.inrease(1);
                        }
                        writer.write(endString + '\n');
                    }
                } finally {
                    writer.close();
                }
            } finally {
                scanner.close();
            }
        } catch (final IOException e) {
            System.out.println("I/O error" + e.getMessage());
        }
    }
}
