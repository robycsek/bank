package bank.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String seyHello() {
        return "<html><body>Hello Spring MVC!" +LocalDateTime.now() + "</body></html>";

    }
}
