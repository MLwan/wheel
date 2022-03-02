import com.baron.spring.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JUnitSpringTest {
    @Test
    public void test() {
        System.out.println("@RunWith提供测试SpringRunner运行器(Spring提供的测试运行器，它会创建测试运行所需的Spring应用上下文)，指导Junit如何运行测试");
    }
}
