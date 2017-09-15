import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.scanner.FastScanner;
import org.scanner.MoveEnum;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Optimus on 24/05/2017.
 */
public class SimpleScannerTest {
    static int nbrItem = 10000;
    private FastScanner scanner;

    @Before
    public void setup(){
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<nbrItem;i++)
            buffer.append("<test"+i+">").append("value"+i).append("</test"+i+">\n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer.toString().getBytes());
        scanner = new FastScanner(inputStream);
    }

    @Test
    public void readToElementRightTest() throws IOException {
        for (int i=0;i<nbrItem;i++){
            String value = new String(scanner.readToElement("</test"+i+">\n", MoveEnum.RIGHT_FROM_ELEMENT));
            Assert.assertEquals(value, "<test"+i+">value"+i+"</test"+i+">\n");
        }
    }

    @Test
    public void readToElementLeftTest() throws IOException {
        for (int i=0;i<nbrItem;i++){
            String value = new String(scanner.readToElement("</test"+i+">\n", MoveEnum.LEFT_FROM_ELEMENT));
            Assert.assertEquals("<test"+i+">value"+i, value );
            value = new String(scanner.readToElement("</test"+i+">\n", MoveEnum.RIGHT_FROM_ELEMENT));
            Assert.assertEquals("</test"+i+">\n", value);
        }
    }

    @Test
    public void moveToElementLeftTest() throws IOException {
        for (int i=0;i<nbrItem-1;i++){
            scanner.moveToNextElement("</test"+i+">\n", MoveEnum.LEFT_FROM_ELEMENT);
            i++;
            String value = new String(scanner.readToElement("</test"+i+">\n", MoveEnum.RIGHT_FROM_ELEMENT));
            Assert.assertEquals(value, "</test"+(i-1)+">\n"+"<test"+i+">value"+i+"</test"+i+">\n");
        }
    }

    @Test
    public void moveToElementRightTest() throws IOException {
        for (int i=0;i<nbrItem-1;i++){
            scanner.moveToNextElement("</test"+i+">\n", MoveEnum.RIGHT_FROM_ELEMENT);
            i++;
            String value = new String(scanner.readToElement("</test"+i+">\n", MoveEnum.RIGHT_FROM_ELEMENT));
            Assert.assertEquals("<test"+i+">value"+i+"</test"+i+">\n", value);
        }
    }

    @Test
    public void readNextLineTest() throws IOException {
        for (int i=0;i<nbrItem-1;i++){
            String value = new String (scanner.nextLine(false));
            Assert.assertEquals("<test"+i+">value"+i+"</test"+i+">\n", value);
        }
    }

    @Test
    public void retrieveNextTokenRight() throws IOException {
        for (int i=0;i<nbrItem-1;i++){
            String value = new String (scanner.retrieveNextToken("<test"+i+">",
                                        "</test"+i+">\n", MoveEnum.RIGHT_FROM_ELEMENT));
            Assert.assertEquals("value"+i, value);
            i++;
            value = new String (scanner.readToElement("</test"+i+">\n", MoveEnum.RIGHT_FROM_ELEMENT));
            Assert.assertEquals("<test"+i+">value"+i+"</test"+i+">\n", value);
        }
    }

    @Test
    public void retrieveNextTokenLeft() throws IOException {
        for (int i=0;i<nbrItem-1;i++){
            String value = new String (scanner.retrieveNextToken("<test"+i+">",
                    "</test"+i+">\n", MoveEnum.LEFT_FROM_ELEMENT));
            Assert.assertEquals("value"+i, value);
            value = new String (scanner.readToElement("</test"+i+">\n", MoveEnum.RIGHT_FROM_ELEMENT));
            Assert.assertEquals("<test"+i+">value"+i+"</test"+i+">\n", value);
        }
    }

    @Test
    public void cleanLineTest() throws IOException {
        Assert.assertEquals("ab", new String (FastScanner.cleanLine("ab\r\n".getBytes())));
        Assert.assertEquals("ab", new String (FastScanner.cleanLine("ab\n".getBytes())));
    }

}
