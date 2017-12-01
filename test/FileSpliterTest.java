import org.junit.Test;

import static org.junit.Assert.*;

public class FileSpliterTest {

    @Test
    public void testFileSplit() throws Exception {
        FileSpliter s = new FileSpliter("RawData.txt",35);
        s.split();
    }

}