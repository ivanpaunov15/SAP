package com.ivan.ShopManagement.api;

import com.ivan.ShopManagement.exceptions.ResourceNotFoundException;
import com.ivan.ShopManagement.model.*;
import com.ivan.ShopManagement.services.ChartService;
import com.ivan.ShopManagement.services.SalesRepresentativeServices;
import java.util.List;
import java.util.Map;

import com.ivan.ShopManagement.services.StatsServices;
import com.ivan.ShopManagement.services.ValidationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping({"representative/api/v1"})
@RestController
public class SalesRepresentativeController {

    private final SalesRepresentativeServices salesRepresentativeServices;

    @Autowired
    ChartService chartService;

    @Autowired
    StatsServices statsServices;

    @Autowired
    ValidationServices validationServices;

    @Autowired
    public SalesRepresentativeController(SalesRepresentativeServices salesRepresentativeServices) {
        this.salesRepresentativeServices = salesRepresentativeServices;
    }

    @RequestMapping(value = "/newClient", method=RequestMethod.POST)
    public ModelAndView addClient(@RequestParam("name") String name, @RequestParam("mail") String mail) {
        this.salesRepresentativeServices.addClient(new Client(name,mail));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("representative/added");

        return modelAndView;
    }


    @PostMapping({"/newSale"})
    public ModelAndView sellProduct(Authentication authentication,@RequestParam("clientId") long clientId,
                            @RequestParam("productId") long productId, @RequestParam("quantity") int quantity, @RequestParam("discount") float discount){
        validationServices.checkIfNegative(productId);
        validationServices.checkIfNegative(clientId);

        Client client = salesRepresentativeServices.getClientById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id :" + clientId));

        Product product = salesRepresentativeServices.getProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + productId));
        this.salesRepresentativeServices.sellProduct(authentication.getName(),clientId,productId,quantity,discount);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("representative/added");

        return modelAndView;
    }

    @GetMapping(path = "/getClientById/{id}")
    public ModelAndView getClientById(@PathVariable("id") long id, Model model){
        validationServices.checkIfNegative(id);

        Client client = salesRepresentativeServices.getClientById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id :" + id));

        model.addAttribute("client",client);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("representative/clients");

        return modelAndView;
    }

    @GetMapping({"/allClients"})
    public ModelAndView getAllClients(Model model) {
        List<Client> clients = this.salesRepresentativeServices.getAllClients();
        validationServices.checkIfListSizeIsZero(clients.size(),"Client not found");

        model.addAttribute("client",clients);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("representative/clients");

        return modelAndView;
    }

    @GetMapping({"/getSales"})
    public ModelAndView getSales(Model model, Authentication authentication){
        validationServices.checkIfStringIsEmpty(authentication.getName());

        List<Sale> sales = this.salesRepresentativeServices.getAllSalesByName(authentication.getName());
        validationServices.checkIfListSizeIsZero(sales.size(),"Sales not found");

        Map<String, Integer> graphData = chartService.getStatisticsByNameOfRepresentative(authentication.getName());
        validationServices.checkIfListSizeIsZero(graphData.size(),"No data");

        double sumOfAllSales = statsServices.getAllMoney(sales);
        validationServices.checkIfListSizeIsZero((int)sumOfAllSales,"There are no sales so income is 0");

        Sale bestSale = statsServices.bestSale(sales);
        if(bestSale==null){
            throw  new ResourceNotFoundException("Sale not found");
        }
        model.addAttribute("allMoney",sumOfAllSales);
        model.addAttribute("bestSale",bestSale);
        model.addAttribute("chartData", graphData);
        model.addAttribute("sale",sales);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("representative/sales");

        return modelAndView;

    }

    @GetMapping({"/allProducts"})
    public ModelAndView getAllProducts(Model model){
        List<Product> products = this.salesRepresentativeServices.getAllProducts();
        validationServices.checkIfListSizeIsZero(products.size(),"Products not found");

        model.addAttribute("product", products);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("representative/products");

        return modelAndView;
    }
    @RequestMapping(value="/deleteClient/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deleteClient(@PathVariable("id") long id){
        validationServices.checkIfNegative(id);

        Client client = salesRepresentativeServices.getClientById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id :" + id));
        salesRepresentativeServices.deleteClient(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("representative/deleted");

        return modelAndView;
    }

    @RequestMapping(value = "/updateClient",method =RequestMethod.POST)
    public ModelAndView updateClientById(@RequestParam("id") long id , @RequestParam("name") String name, @RequestParam("mail") String mail){

        Client client = salesRepresentativeServices.getClientById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id :" + id));
        salesRepresentativeServices.updateClient(id,new Client(name,mail));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("representative/updated");

        return modelAndView;
    }

    @RequestMapping("/sendMail")
    public ModelAndView sendMail(@RequestParam("topic") String topic, @RequestParam("text") String text){
        List<Client> list = salesRepresentativeServices.getAllClients();
        validationServices.checkIfListSizeIsZero(list.size(),"Clients not found");

        salesRepresentativeServices.sendMailsToEveryone(topic,text);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("representative/sent");

        return modelAndView;
    }

    @RequestMapping("/sendMailToClient")
    public ModelAndView sendMailToClient(@RequestParam("mail") String mail, @RequestParam("topic") String topic, @RequestParam("text") String text){
        validationServices.checkIfStringIsEmpty(mail);

        salesRepresentativeServices.sendMailToClient(mail,topic,text);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("representative/sent");

        return modelAndView;
    }
}
