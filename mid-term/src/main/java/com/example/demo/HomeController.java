package com.example.demo;

import com.example.demo.ojects.Account;
import com.example.demo.ojects.Cart;
import com.example.demo.ojects.Product;
import com.example.demo.services.AccountServices;
import com.example.demo.services.CartServices;
import com.example.demo.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final ProductServices productServices;
    private final AccountServices accountServices;
    private final CartServices cartServices;

    @Autowired
    public HomeController(ProductServices productServices, AccountServices accountServices, CartServices cartServices) {
        this.productServices = productServices;
        this.accountServices = accountServices;
        this.cartServices = cartServices;
    }

    @GetMapping({"/","/home"})
    public String getHomePage(Model model, HttpSession session,
                              @RequestParam(required = false) String search,
                              @RequestParam(required = false) Long up,
                              @RequestParam(required = false) Long down,
                              @RequestParam(required = false) String brand,
                              @RequestParam(required = false) String color,
                              @RequestParam(required = false) String category) {
        List<Product> ls = new ArrayList<>();
        List<String> lsColor = productServices.getColor();
        List<String> lsBrand = productServices.getBrand();
        List<String> lsCategory = productServices.getCategory();

        if(session.getAttribute("account") == null){
            return "redirect:/login";
        } else {
            Account acc = (Account) session.getAttribute("account");
            if(search != null){
                ls = productServices.getProducts(search);
            } else if (brand != null) {
                ls = productServices.getProductByBrand(brand);
            } else if (category != null) {
                ls = productServices.getProductByCategory(category);
            } else if (color != null) {
                ls = productServices.getProductByColor(color);
            } else if (up != null && down != null) {
                ls = productServices.getProductByPrice(up, down);
            } else {ls = productServices.getProducts();}


            model.addAttribute("lsProduct", ls);
            model.addAttribute("lsColor", lsColor);
            model.addAttribute("lsBrand", lsBrand);
            model.addAttribute("lsCategory", lsCategory);
            model.addAttribute("account", acc);
            model.addAttribute("cartSize", cartServices.getCartSize(acc.getId()));
            return "homepage";
        }
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        return "login";
    }
    @PostMapping("/login")
    public String login_check(Model model, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (accountServices.checkAccount(username, password) != null) {
            session.setAttribute("account", accountServices.checkAccount(username, password));
            if(username.equals("admin") && password.equals("admin")){

                return "redirect:/admin";
            }
            return "redirect:/home";
        }
        else return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model, HttpSession session) {
        if(session.getAttribute("account") == null){
            return "redirect:/login";
        } else {
            List<Product> products = productServices.getProducts();
            model.addAttribute("products",products);
            model.addAttribute("product", new Product());
            return "adminpage";
        }
    }

    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session) {
        if(session.getAttribute("account") == null){
            return "redirect:/login";
        } else {
            Account acc = (Account) session.getAttribute("account");
            model.addAttribute("cart", cartServices.getCart(acc.getId()));
            model.addAttribute("total", cartServices.getTotal(acc.getId()));
            model.addAttribute("accId", acc.getId());
            return "cart";
        }
    }

    @GetMapping("/detail")
    public String getDetail(Model model, @RequestParam Long id, HttpSession session) {
        if(session.getAttribute("account") == null){
            return "redirect:/login";
        } else {
            model.addAttribute("account", (Account) session.getAttribute("account"));
            model.addAttribute("product", productServices.getProductById(id));
            return "productdetail";
        }
    }

    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String createAccount(@ModelAttribute("account") Account account) {
        accountServices.save(account);
        return "login";
    }

    @PostMapping("/addToCart")
    public String addToCart(Model model,
                            @RequestParam("productName") String productName,
                            @RequestParam("productPrice") Long productPrice,
                            @RequestParam("accountId") Long accountId,
                            HttpSession session) {
        if(session.getAttribute("account") == null){
            return "redirect:/login";
        } else {
            cartServices.save(new Cart(productName,productPrice,accountId));
            model.addAttribute("accId", accountId);
            return "redirect:/cart";
        }
    }

    @GetMapping("/deleteProductInCart")
    public String deleteProductInCart(Model model, @RequestParam Long id) {
        cartServices.deleteById(id);
        return "redirect:/cart";
    }

    @GetMapping("/pay")
    public String pay(Model model, @RequestParam Long id) {
        for(Cart cart : cartServices.getCart(id)) {
            cartServices.deleteCart(cart);
        };
        return "redirect:/home";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product){
        productServices.save(product);
        return "redirect:/admin";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("idDel") Long id) {
        productServices.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(Model model, @RequestParam("idEdit") Long id) {
        Product p = productServices.getProductById(id);
        model.addAttribute("product", p);
        return "edit";
    }

    @PostMapping("/doneUpdate")
    public String updateProduct(@ModelAttribute("product") Product product) {
        productServices.deleteById(product.getId());
        productServices.save(product);
        return "redirect:/admin";
    }
}
