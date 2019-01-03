**FastScanner**

Optimized Java Library for buffered stream scanning

It's an alternative for the default JDK Scanner with many advanced methods.
 
It supports :
 * InputStream
 * RandomFileStream which offer the setOffset method.

**Maven Dependency**
```java
<dependency>
    <groupId>io.github.nassimus26</groupId>
    <artifactId>FastScanner</artifactId>
    <version>1.0.2</version> 
</dependency>
```    
How to use it :

```java
    public class FastScannerExemples {
        private static FastScanner getScannerInstance(String str){
            ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes());
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
