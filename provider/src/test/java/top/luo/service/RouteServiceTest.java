package top.luo.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author luo
 * @date 2021-01-30 17:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RouteServiceTest {

    private RouteService service;

    @Autowired
    public void setService(RouteService service) {
        this.service = service;
    }

    @Test
    public void listAll() {
        System.out.println(service.listAll());
    }

}
