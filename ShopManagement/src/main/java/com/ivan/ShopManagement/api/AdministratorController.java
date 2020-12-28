package com.ivan.ShopManagement.api;

import com.ivan.ShopManagement.exceptions.ResourceNotFoundException;
import com.ivan.ShopManagement.model.Administrator;
import com.ivan.ShopManagement.model.Product;
import com.ivan.ShopManagement.model.Sale;
import com.ivan.ShopManagement.model.SalesRepresentative;
import com.ivan.ShopManagement.services.AdministratorServices;

import java.util.List;
import java.util.Map;

import com.ivan.ShopManagement.services.ChartService;
import com.ivan.ShopManagement.services.StatsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;

@RequestMapping({"admin/api/v1/"})
@RestController
public class AdministratorController {
    private final AdministratorServices administratorServices;

    @Autowired
    ChartService chartService;

    @Autowired
    StatsServices statsServices;

    @Autowired
    public AdministratorController(AdministratorServices administratorServices) {
        this.administratorServices = administratorServices;
    }

    @RequestMapping(value = "/newSalesRepresentative", method=RequestMethod.POST)
    public ModelAndView addSalesRepresentative(@RequestParam("name") String name, @RequestParam("password") String password,@RequestParam("mail") String mail) {
        this.administratorServices.addSalesRepresentative(new SalesRepresentative(name,password,mail));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("added");

        return modelAndView;
    }

    @RequestMapping(value = "/newAdmin", method=RequestMethod.POST)
    public ModelAndView addAdministrator(@RequestParam("admin") String name, @RequestParam("password") String password, @RequestParam("mail") String mail) {
        this.administratorServices.addAdministrator(new Administrator(name,password,mail));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("added");

        return modelAndView;
    }

    @RequestMapping(value = {"/newProduct"},method =RequestMethod.POST)
    public ModelAndView addProduct(@RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("quantity") int quantity, @RequestParam("description") String description) {
        this.administratorServices.addProduct(new Product(name,price,quantity,description));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("added");

        return modelAndView;
    }

    @GetMapping({"/getAdminById/{id}"})
    public ModelAndView getAdministratorById(@PathVariable("id") long id, Model model){
        Administrator admin =  administratorServices.getAdministratorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found with id :" + id));

        model.addAttribute("admin",admin);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/administrators");

        return modelAndView;
    }

    @GetMapping(path = "/getSalesRepresentativeById/{id}")
    public ModelAndView getSalesRepresentativeByID(@PathVariable("id") long id, Model model){
        SalesRepresentative representative = administratorServices.getSalesRepresentativeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales Representative not found with id :" + id));

        model.addAttribute("representative",representative);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/representatives");

        return modelAndView;
    }

    @GetMapping(path = "/getProductById/{id}")
    public ModelAndView getProductById(@PathVariable("id") long id, Model model){
       Product product = administratorServices.getProductById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));

        model.addAttribute("product",product);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/products");

        return modelAndView;
    }

    @GetMapping(path = "/getSaleById/{id}")
    public ModelAndView getSaleById(@PathVariable("id") long id,Model model){
        Sale sale =  administratorServices.getSaleById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with id :" + id));

        model.addAttribute("sale",sale);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/sale");

        return modelAndView;
    }

    @GetMapping(path = "/getSaleByRepresentativeId/{id}")
    public ModelAndView getSaleByRepresentativeId(@PathVariable("id") long id, Model model){
        List<Sale> sale = administratorServices.getSalesByRepresentativeId(id);
        if(sale.size()==0){
            throw  new ResourceNotFoundException("Sale not found with id :" + id);
        }

        Map<String, Integer> graphData = chartService.getStatisticsByRepresentativeId(id);
        if(graphData.size()==0){
            throw  new ResourceNotFoundException("No data");
        }
        double sumOfAllSales = statsServices.getAllMoney(sale);
        if(sumOfAllSales<=0){
            throw  new ResourceNotFoundException("There are no sales so income is 0");
        }
        Sale bestSale = statsServices.bestSale(sale);
        if(bestSale==null){
            throw  new ResourceNotFoundException("Sale not found");
        }
        model.addAttribute("allMoney",sumOfAllSales);
        model.addAttribute("bestSale",bestSale);
        model.addAttribute("chartData", graphData);
        model.addAttribute("sale",sale);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/sales");

        return modelAndView;
    }

    @GetMapping(path = "/getSaleBetweenPeriod/{first}/{second}")
    public ModelAndView getSaleBetweenPeriod(@PathVariable("first") String firstDate, @PathVariable("second") String secondDate, Model model){
        List<Sale> sale = administratorServices.getSalesBetweenPeriod(firstDate,secondDate);
        if(sale.size()==0){
            throw  new ResourceNotFoundException("Sale not found");
        }
        Map<String, Integer> graphData = chartService.getStatisticsForPeriod(firstDate, secondDate);
        if(graphData.size()==0){
            throw  new ResourceNotFoundException("No data");
        }
        double sumOfAllSales = statsServices.getAllMoney(sale);
        if(sumOfAllSales<=0){
            throw  new ResourceNotFoundException("There are no sales so income is 0");
        }
        Sale bestSale = statsServices.bestSale(sale);
        if(bestSale==null){
            throw  new ResourceNotFoundException("Sale not found");
        }
        model.addAttribute("allMoney",sumOfAllSales);
        model.addAttribute("bestSale",bestSale);
        model.addAttribute("chartData", graphData);
        model.addAttribute("sale",sale);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/sales");

        return modelAndView;
    }

    @GetMapping(path = "/getSalesOfClient/{name}")
    public ModelAndView getSalesOfClient(@PathVariable("name") String name,Model model){
        List<Sale> sales = administratorServices.getClientSales(name);
        if(sales.size()==0){
            throw  new ResourceNotFoundException("Sale not found");
        }
        Map<String, Integer> graphData = chartService.getStatisticsByNameOfClient(name);
        if(graphData.size()==0){
            throw  new ResourceNotFoundException("No data");
        }

        double sumOfAllSales = statsServices.getAllMoney(sales);
        if(sumOfAllSales<=0){
            throw  new ResourceNotFoundException("There are no sales so income is 0");
        }
        Sale bestSale = statsServices.bestSale(sales);
        if(bestSale==null){
            throw  new ResourceNotFoundException("Sale not found");
        }
        model.addAttribute("allMoney",sumOfAllSales);
        model.addAttribute("bestSale",bestSale);
        model.addAttribute("chartData", graphData);
        model.addAttribute("sale",sales);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/sales");

        return modelAndView;
    }

    @GetMapping("/allSalesRepresentatives")
    public ModelAndView getAllSalesRepresentative(Model model) {
        List<SalesRepresentative> representatives = this.administratorServices.getAllSalesRepresentative();
        if(representatives.size()==0){
            throw  new ResourceNotFoundException("Sales Representative not found");
        }
        model.addAttribute("representative",representatives);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/representatives");

        return modelAndView;
    }

    @GetMapping({"/allAdmins"})
    public ModelAndView getAllAdministrators(Model model) {
        List<Administrator> administrators = this.administratorServices.getAllAdministrators();
        if(administrators.size()==0){
            throw  new ResourceNotFoundException("Administrator not found");
        }
        model.addAttribute("admin",administrators);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/administrators");

        return modelAndView;
    }

    @GetMapping({"/allProducts"})
    public ModelAndView getAllProducts(Model model) {
        List<Product> products = this.administratorServices.getAllProduct();
        if(products.size()==0){
            throw  new ResourceNotFoundException("Product not found");
        }
        model.addAttribute("product",products);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/products");

        return modelAndView;
    }

    @GetMapping("/allSales")
    public ModelAndView getAllSales(Model model){
        List<Sale> sales = this.administratorServices.getAllSales();
        if(sales.size()==0){
            throw  new ResourceNotFoundException("Sale not found");
        }
        Map<String, Integer> graphData = chartService.getStatisticsForAllSales();
        if(graphData.size()==0){
            throw  new ResourceNotFoundException("No data");
        }

        double sumOfAllSales = statsServices.getAllMoney(sales);
        if(sumOfAllSales<=0){
            throw  new ResourceNotFoundException("There are no sales so income is 0");
        }
        Sale bestSale = statsServices.bestSale(sales);
        if(bestSale==null){
            throw  new ResourceNotFoundException("Sale not found");
        }
        model.addAttribute("allMoney",sumOfAllSales);
        model.addAttribute("bestSale",bestSale);
        model.addAttribute("chartData", graphData);
        model.addAttribute("sale",sales);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/sales");

        return modelAndView;
    }

    @RequestMapping(value="/deleteSalesRepresentative/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deleteSalesRepresentativeById(@PathVariable("id") long id){
        SalesRepresentative salesRepresentative = administratorServices.getSalesRepresentativeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales Representative not found with id :" + id));;
        administratorServices.deleteSalesRepresentative(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleted");

        return modelAndView;
    }

    @RequestMapping(value="/deleteProduct/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deleteProductById(@PathVariable("id") long id){
        Product product = administratorServices.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
        administratorServices.deleteProduct(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleted");

        return modelAndView;
    }

    @RequestMapping(value = "/updateSalesRepresentative",method =RequestMethod.POST)
    public ModelAndView updateSalesRepresentativeById(@RequestParam("id") long id,@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("mail") String mail){
        SalesRepresentative salesRepresentative = administratorServices.getSalesRepresentativeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales Representative not found with id :" + id));
        administratorServices.updateSalesRepresentative(id,new SalesRepresentative(name,password, mail));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updated");

        return modelAndView;
    }

    @RequestMapping(value = "/updateProduct",method =RequestMethod.POST)
    public ModelAndView updateProductById(@RequestParam("id") long id , @RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("quantity") int quantity, @RequestParam("description") String description){
        Product product = administratorServices.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
        administratorServices.updateProduct(id, new Product(name,price,quantity,description));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updated");

        return modelAndView;
    }


}
