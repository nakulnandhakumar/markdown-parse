// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);

            if(nextOpenBracket == -1 || nextCloseBracket == -1 || openParen == -1 || closeParen == -1){
                break;
            }
            // Checks to see if links are present in document or if all lnks have already been printed

            if (nextCloseBracket + 1 == openParen) {
                if (openParen + 1 != closeParen) {
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
                // makes sure that there is text inside the parentheses and that an empty string isn't added to the list
            }
            // Only adds text inside parentheses if it is actually a link (parentheses must be right after closed brackets)
            
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}