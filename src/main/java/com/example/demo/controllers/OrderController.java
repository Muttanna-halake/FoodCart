package com.example.demo.controllers;

import java.util.Date;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Orders;
import com.example.demo.entities.User;
import com.example.demo.services.OrderServices;

@Controller
public class OrderController {

    @Autowired
    private OrderServices orderService;

    // -----------------------------------------------------
    // 1️⃣ PLACE ORDER (UPDATED WITH IMAGE)
    // -----------------------------------------------------
    @PostMapping("/product/order")
    public String placeOrder(
            @RequestParam("oName") String name,
            @RequestParam("oPrice") double price,
            @RequestParam("oQuantity") int qty,
            @RequestParam("oImage") String image,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null)
            return "redirect:/login";

        Orders order = new Orders();
        order.setoName(name);
        order.setoPrice(price);
        order.setoQuantity(qty);
        order.setOrderDate(new Date());
        order.setTotalAmmout(price * qty);

        // ⭐ IMPORTANT: Save image to order
        order.setImage(image);

        // ⭐ Default status
        order.setStatus("ORDERED");

        order.setUser(user);

        orderService.saveOrder(order);

        model.addAttribute("amount", order.getTotalAmmout());
        return "Order_success";
    }

    // -----------------------------------------------------
    // 2️⃣ USER ORDERS PAGE (LIST ALL)
    // -----------------------------------------------------
    @GetMapping("/orders")
    public String showUserOrders(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null)
            return "redirect:/login";

        List<Orders> list = orderService.getOrdersForUser(user);

        model.addAttribute("orders", list);
        return "Your_Orders";
    }

    // -----------------------------------------------------
    // 3️⃣ TRACK ORDER PAGE
    // -----------------------------------------------------
    @GetMapping("/track/{orderId}")
    public String trackOrder(@PathVariable int orderId, Model model, HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");
        if (user == null)
            return "redirect:/login";

        Orders order = orderService.getOrders()
                .stream()
                .filter(o -> o.getoId() == orderId)
                .findFirst()
                .orElse(null);

        if (order == null) {
            model.addAttribute("error", "Order not found");
            return "Your_Orders";
        }

        model.addAttribute("order", order);
        return "Track_Order";
    }

    // @PostMapping("/cancel-order/{orderId}")
    // public String cancelOrder(@PathVariable int orderId, HttpSession session) {

    // User user = (User) session.getAttribute("loggedUser");

    // if (user == null)
    // return "redirect:/login";

    // Orders order = orderService.getOrders()
    // .stream()
    // .filter(o -> o.getoId() == orderId)
    // .findFirst()
    // .orElse(null);

    // if (order != null) {
    // order.setStatus("Cancelled");
    // orderService.saveOrder(order);
    // }

    // return "redirect:/orders";
    // }
    @PostMapping("/cancel-order/{orderId}")
    public String cancelOrder(@PathVariable int orderId,
            @RequestParam("reason") String reason,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null)
            return "redirect:/login";

        Orders order = orderService.getOrders()
                .stream()
                .filter(o -> o.getoId() == orderId)
                .findFirst()
                .orElse(null);

        if (order != null) {

            // ❌ Prevent cancelling shipped/delivered items
            if (order.getStatus().equalsIgnoreCase("Shipped") ||
                    order.getStatus().equalsIgnoreCase("Delivered")) {
                return "redirect:/orders?error=cannotCancel";
            }

            order.setStatus("Cancelled");
            order.setCancelReason(reason);
            order.setCancelDate(new Date());
            orderService.saveOrder(order);
        }

        return "redirect:/orders";
    }

    // @PostMapping("/admin/updateStatus/{orderId}")
    // public String updateOrderStatus(@PathVariable int orderId,
    // @RequestParam("status") String status) {

    // Orders order = orderService.getOrderById(orderId);

    // if (order != null) {
    // order.setStatus(status);
    // orderService.saveOrder(order);
    // }

    // return "redirect:/admin";
    // }

    @PostMapping("/place-order")
    public String placeOrder(
            @RequestParam("oName") String name,
            @RequestParam("oPrice") double price,
            @RequestParam("oQuantity") int qty,
            @RequestParam("oImage") String image,
            @RequestParam("fullName") String fullName,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("payment") String payment,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedUser");
        if (user == null)
            return "redirect:/login";

        Orders order = new Orders();
        order.setoName(name);
        order.setoPrice(price);
        order.setoQuantity(qty);
        order.setImage(image);
        order.setOrderDate(new Date());
        order.setTotalAmmout(price * qty);
        order.setStatus("ORDERED");
        order.setUser(user);

        orderService.saveOrder(order);

        model.addAttribute("amount", order.getTotalAmmout());
        model.addAttribute("orderId", order.getoId());

        return "Order_success"; // ✔ loads the HTML page
    }

}
