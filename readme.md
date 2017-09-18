**FastScanner**

Optimized Java Library for buffered stream scanning

It's an alternative for the default JDK Scanner with many advanced methods.
 
It supports :
 * InputStream
 * RandomFileStream which offer the setOffset method.

**Maven Dependency**
```java
<repository>
    <id>ossr</id>
    <name>ossr</name>
    <url>https://oss.sonatype.org/service/local/repositories/releases/content/</url>
</repository>
............
<dependency>
    <groupId>io.github.nassimus26</groupId>
    <artifactId>FastScanner</artifactId>
    <version>1.0.0</version> 
</dependency>
```    
How to use it :

```java
    public class FastScannerExemples {
        private FastScanner getScannerInstance(String str){
            ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer.toString().getBytes());
            return new FastScanner(inputStream);        
        }
    
        @Test
        public void readToElementRightTest() throws IOException {
            scanner = getScannerInstance("<test>value</test>\n");
            String value = new String(scanner.readToElement("</test>\n", MoveEnum.RIGHT_FROM_ELEMENT));
            Assert.assertEquals(value, "<test>value</test>\n");        
        }
    
        @Test
        public void readToElementLeftTest() throws IOException {
            scanner = getScannerInstance("<test>value</test>\n");
            String value = new String(scanner.readToElement("</test>\n", MoveEnum.LEFT_FROM_ELEMENT));
            Assert.assertEquals(value, "<test>value");        
        }
        ...
    }
```
**for more exemples see the Unit Tests.**
