package Files;

import com.google.appengine.api.utils.SystemProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private EntityManager em;

    @RequestMapping(value = "/")
    public String index(){

//        em initialization, should be singleton but just for test
        Map<String, String> properties = new HashMap();

        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {

            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
            properties.put("javax.persistence.jdbc.url"   , System.getProperty("cloudsql.url"));
        } else {
            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            properties.put("javax.persistence.jdbc.url"   , System.getProperty("cloudsql.url.dev"));
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Demo", properties);
        em = emf.createEntityManager();

        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPagePost(Model model,
                                @RequestParam String login,
                                @RequestParam String password){

        TableEntity te = hibernateDbAccess(login);

        if( te == null)
            return "loginJSP";

        if( password.contentEquals( te.getPassword())){

            model.addAttribute("authorized", true);
            model.addAttribute("log"       , login);
            model.addAttribute("pass"      , password);
        } else {

            model.addAttribute("authorized", false);
        }

        return "loginJSP";
    }

    private TableEntity hibernateDbAccess(String login){

//        em.getTransaction().begin();

        TableEntity res = em.find(TableEntity.class, login);

//        em.getTransaction().commit();
        return res;
    }

}
