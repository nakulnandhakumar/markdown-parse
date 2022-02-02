import static org.junit.Assert.*;
import org.junit.*;
// imports the JUnit classes and methods

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {
    @Test 
    // annotation defines the method below as a JUnit test
    public void addition() {
        assertEquals(2, 1 + 1);
        // assertEquals checks if the value of the first paramater is
        // equivalent to the value of the second parameter
    }

    @Test
    public void testGetLinks1() throws IOException{
        Path fileName = Path.of("test-file-1.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(List.of("https://something.com", "some-page.html"), links);
    }

    @Test
    public void testGetLinks2() throws IOException{
        Path fileName = Path.of("test-file-2.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(List.of("somewords", ""), links);
    }

    @Test
    public void testGetLinks3() throws IOException{
        Path fileName = Path.of("test-file-3.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of(), links);
    }
}