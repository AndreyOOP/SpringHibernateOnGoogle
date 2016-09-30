package Files;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private String login    = "test";
    private String password = "123";

    @RequestMapping(value = "/")
    public String index(){

        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPagePost(){
        return "loginJSP";
    }
}
