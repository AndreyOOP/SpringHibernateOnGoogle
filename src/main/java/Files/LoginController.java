package Files;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private String LOGIN    = "test";
    private String PASSWORD = "123";

    @RequestMapping(value = "/")
    public String index(){

        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPagePost(Model model,
                                @RequestParam String login,
                                @RequestParam String password){

        if( login.contentEquals(LOGIN) && password.contentEquals(PASSWORD)){

            model.addAttribute("authorized", true);
        } else {

            model.addAttribute("authorized", false);
        }

        return "loginJSP";
    }
}
