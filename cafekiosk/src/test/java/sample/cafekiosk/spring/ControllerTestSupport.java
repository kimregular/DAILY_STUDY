package sample.cafekiosk.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sample.cafekiosk.spring.api.controller.order.OrderController;
import sample.cafekiosk.spring.api.controller.product.ProductController;
import sample.cafekiosk.spring.api.service.order.OrderService;
import sample.cafekiosk.spring.api.service.product.ProductService;

@WebMvcTest(controllers = {
        OrderController.class,
        ProductController.class
})  // 컨트롤러 테스트만 하기위해 Web 관련 Bean만 띄워준다. 테스트할 컨트롤러 명시
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;  // json과 Object간 직렬화, 역직렬화를 도와주는 객체

    @MockBean  // 컨테이너에 Mockito로 만든 Mock 객체를 빈으로 등록
    protected OrderService orderService;

    @MockBean
    protected ProductService productService;
}
