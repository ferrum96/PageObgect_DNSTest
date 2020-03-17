import org.junit.Test;
import ru.aplana.objpajes.TestProperties;

public class FirstTest {


    @Test
    public void testProperties(){
        TestProperties.getInstance();
        TestProperties.getInstance();
        TestProperties.getInstance();
        TestProperties.getInstance();
        TestProperties.getInstance();
        TestProperties.getInstance();
        System.out.println(TestProperties.getInstance().getProperty("url"));

    }
}
