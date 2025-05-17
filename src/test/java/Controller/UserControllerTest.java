package Controller;

import com.e_commerce.e_commerce_demo.Services.UserService;
import com.e_commerce.e_commerce_demo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

     /*
     MocktiBean annotation tells spring to create mock instance of UserService
     and to application context so that its ite injected to controller
     */
    @MockitoBean
    private UserService userService;


    @Test
    public void createuser(){

    }


}
