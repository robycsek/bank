package bank.frontend;

import bank.backend.BankService;
import bank.backend.Client;
import com.sun.jmx.remote.internal.ClientListenerInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
public class ClientsController {

    private BankService bankService;

    public ClientsController(BankService bankService) {
        this.bankService = bankService;
    }

    /*@ResponseBody//nem megy a view-ba, hanem rögtön visszamegy a böngészőbe, ha ez itt van
    @RequestMapping("/")
    public String listClients() {
        return bankService.listClients().stream().map(c -> c.getName()).collect(Collectors.joining(", "));
    }*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listClients() {
        //View
        ModelAndView modelAndView = new ModelAndView("clients");
        //Model: List<Client>
        modelAndView.addObject("clients", bankService.listClients());

        modelAndView.addObject("clientForm", new Client());//feltölti üres értékekkel a formot

        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addClient(@ModelAttribute Client client) {
        bankService.addClient(client);
        return "redirect:/";
    }


}
