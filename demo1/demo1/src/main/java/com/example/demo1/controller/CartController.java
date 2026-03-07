package com.example.demo1.controller;

import com.example.demo1.service.CartService;
import com.example.demo1.service.ProductService;
import com.example.demo1.service.OrderService;
import com.example.demo1.model.Order;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;

    public CartController(CartService cartService, ProductService productService, OrderService orderService) {
        this.cartService = cartService;
        this.productService = productService;
        this.orderService = orderService;
    }

    // Pass cart size to all pages via @ModelAttribute
    @ModelAttribute("cartQuantity")
    public int cartQuantity(HttpSession session) {
        return cartService.getCart(session).stream().mapToInt(item -> item.getQuantity()).sum();
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        model.addAttribute("cartItems", cartService.getCart(session));
        model.addAttribute("totalPrice", cartService.getTotalCartPrice(session));
        return "cart/view";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity,
            HttpSession session) {
        productService.getProductById(productId).ifPresent(product -> {
            cartService.addToCart(session, product, quantity);
        });
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Long productId, @RequestParam int quantity, HttpSession session) {
        cartService.updateQuantity(session, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long productId, HttpSession session) {
        cartService.removeFromCart(session, productId);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(
            HttpSession session,
            @RequestParam String gender,
            @RequestParam String fullName,
            @RequestParam String phone,
            @RequestParam String deliveryMethod,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String district,
            @RequestParam(required = false) String ward,
            @RequestParam(required = false) String addressDetail,
            @RequestParam(required = false) String storeLocation,
            @RequestParam(required = false) String note,
            @RequestParam(required = false) String otherReceiver,
            @RequestParam(required = false) String companyInvoice,
            RedirectAttributes redirectAttributes) {
            
        try {
            Order order = orderService.createOrderFromCart(session, gender, fullName, phone, 
                                                           deliveryMethod, city, district, ward, 
                                                           addressDetail, storeLocation, note);
            
            redirectAttributes.addFlashAttribute("successMessage", "Đặt hàng thành công! Mã đơn hàng của bạn là #" + order.getId() + ". Cộng " + order.getPoints() + " điểm vào tài khoản.");
            return "redirect:/"; // redirect to home or a success page
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi đặt hàng: " + e.getMessage());
            return "redirect:/cart";
        }
    }
}
