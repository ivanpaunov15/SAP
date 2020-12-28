package com.ivan.ShopManagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

    AdministratorController administratorController;

    @GetMapping("/")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/admin/accessdenied")
    public String viewAdminDeniedPage(){
        return "accessdenied";
    }

    @GetMapping("/representative/accessdenied")
    public String viewRepresentativeDeniedPage(){
        return "accessdenied";
    }

    @GetMapping("admin/api/dashboard")
    public String viewAdminDashboard(){
        return "admin/dashboard";
    }

    @RequestMapping("/admin/login")
    public String adminLoginPage(){
        return "admin/admin_login";
    }

    @RequestMapping(value = "/admin/api/v1/newAdmin", method = RequestMethod.GET)
    public String adminAddAdminPage(){
        return "admin/newAdmin";
    }

    @RequestMapping(value = "/admin/api/v1/newProduct", method = RequestMethod.GET)
    public String adminAddProductPage(){
        return "admin/newProduct";
    }

    @RequestMapping(value = "/admin/api/v1/newSalesRepresentative", method = RequestMethod.GET)
    public String adminAddRepresentativePage(){
        return "admin/newSaleRepresentative";
    }

    @RequestMapping(value = "/admin/api/v1/updateSalesRepresentative", method = RequestMethod.GET)
    public String adminUpdateRepresentativePage(){
        return "admin/updateRepresentative";
    }

    @RequestMapping(value = "/admin/api/v1/updateProduct", method = RequestMethod.GET)
    public String adminUpdateProductPage(){
        return "admin/updateProduct";
    }

    @GetMapping("representative/api/dashboard")
    public String viewRepresentativeDashboard(Model model){
        return "representative/representative_dashboard";
    }

    @RequestMapping("/representative/login")
    public String representativeLoginPage(){
        return "representative/representative_login";
    }

    @RequestMapping(value = "/representative/api/v1/newClient", method = RequestMethod.GET)
    public String representativeAddClientPage(){
        return "representative/newClient";
    }

    @RequestMapping(value = "/representative/api/v1/newSale", method = RequestMethod.GET)
    public String representativeAddSalePage(){
        return "representative/newSale";
    }

    @RequestMapping(value = "/representative/api/v1/updateClient", method = RequestMethod.GET)
    public String adminUpdateClientPage(){
        return "representative/updateClient";
    }

    @RequestMapping(value = "/representative/api/v1/sendMail", method = RequestMethod.GET)
    public String adminSendMailPage(){
        return "representative/sendMail";
    }
    @RequestMapping(value = "/representative/api/v1/sendMailToClient", method = RequestMethod.GET)
    public String adminSendMailToClientPage(){
        return "representative/sendMailToClient";
    }

}
