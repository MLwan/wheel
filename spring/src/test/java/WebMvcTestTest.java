//import com.baron.spring.ioc.Application;
import com.baron.spring.ioc.testController.TestController;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


//测试失败：报错
//java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test



@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
@WebMvcTest(TestController.class)
public class WebMvcTestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testWebMvcTest(){
        try {
            mockMvc.perform(get("/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
