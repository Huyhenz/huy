package com.example.demo1.service;

import com.example.demo1.model.*;
import com.example.demo1.repository.OrderRepository;
import com.example.demo1.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartService = cartService;
    }

    @Transactional
    public Order createOrderFromCart(HttpSession session,
                                     String gender, String fullName, String phone,
                                     String deliveryMethod, String city, String district, String ward,
                                     String addressDetail, String storeLocation, String note) {

        List<CartItem> cartItems = cartService.getCart(session);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // 1. Create and Save User (guest record)
        User user = new User();
        user.setFullName(fullName);
        user.setPhone(phone);
        user.setGender(gender);
        user = userRepository.save(user);

        // 2. Calculate Totals
        double totalPrice = cartService.getTotalCartPrice(session);
        int totalQty = cartItems.stream().mapToInt(CartItem::getQuantity).sum();
        
        // Shipping Logic
        double shippingFee = 30000;
        if (totalQty >= 2 || totalPrice > 1000000) {
            shippingFee = 0;
        }
        
        double finalTotal = totalPrice + shippingFee;
        int loyaltyPoints = (int) Math.floor(finalTotal / 10000.0);

        // 3. Create Order
        Order order = new Order();
        order.setUser(user);
        order.setCustomerName(fullName);
        order.setPhone(phone);
        order.setGender(gender);
        
        order.setDeliveryMethod(deliveryMethod);
        if ("home".equals(deliveryMethod)) {
            order.setCity(city);
            order.setDistrict(district);
            order.setWard(ward);
            order.setAddressDetail(addressDetail);
        } else {
            order.setStoreLocation(storeLocation);
        }
        order.setNote(note);

        order.setTotalPrice(totalPrice);
        order.setShippingFee(shippingFee);
        order.setPoints(loyaltyPoints);

        // 4. Create Order Details
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            detail.setPrice(item.getProduct().getCurrentPrice() != null ? item.getProduct().getCurrentPrice() : 0.0);
            orderDetails.add(detail);
        }
        order.setOrderDetails(orderDetails);

        // Save order (cascades User and OrderDetails)
        Order savedOrder = orderRepository.save(order);

        // Clear cart
        cartService.clearCart(session);

        return savedOrder;
    }
}
