package com.osintsev.market.rest.controller;

import com.osintsev.market.database.beat.BeatService;
import com.osintsev.market.database.order.OrderService;
import com.osintsev.market.database.order.OrderStatus;
import com.osintsev.market.database.user.UserService;
import com.osintsev.market.rest.dto.BeatDetailed;
import com.osintsev.market.rest.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Validated
@RequestMapping(value = "/admin")
public class AdminController {
    private final BeatService beatService;
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public AdminController(BeatService beatService, OrderService orderService, UserService userService) {
        this.beatService = beatService;
        this.orderService = orderService;
        this.userService = userService;
    }
    @GetMapping("/menu")
    public ModelAndView getMenu() {
        return new ModelAndView("menu");
    }

    @GetMapping("/orders")
    public ModelAndView getOrders() {
        ModelAndView modelAndView = new ModelAndView("orders");
        modelAndView.addObject("orders", orderService.getOrderList());
        return modelAndView;
    }

    @GetMapping("/beats")
    public ModelAndView getBeats() {
        ModelAndView modelAndView = new ModelAndView("beats");
        modelAndView.addObject("beats", beatService.getBeats().getBeatList());
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", userService.getUsers());
        return modelAndView;
    }


    @GetMapping("/order/{id}")
    public ModelAndView getOrder(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("order");
        Order order = orderService.getOrder(id);
        modelAndView.addObject("order", order);
        return modelAndView;
    }

    @PostMapping("/order/{id}/status") // PatchMapping
    public RedirectView changeOrderStatus(@PathVariable Long id, Order order) {
        System.out.println(order.getStatus());
        orderService.changeStatus(id, order.getStatus());
        return new RedirectView(String.format("/admin/order/%d", id));
    }

    @GetMapping("/order/delete/{id}")
    public RedirectView deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new RedirectView("/admin/orders");
    }

    @GetMapping("beat/create")
    public ModelAndView fillNewBeat() {
        ModelAndView modelAndView = new ModelAndView("fill-beat");
        modelAndView.addObject("beat", new BeatDetailed());
        return modelAndView;
    }

    @GetMapping(value = "beat/{id}")
    public ModelAndView getBeat(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("beat");
        modelAndView.addObject("beat", beatService.getBeatDetailed(id));
        return modelAndView;
    }

    @GetMapping(value = "/beat/delete/{id}")
    public RedirectView deleteBeat(@PathVariable Long id){
        beatService.deleteBeat(id);
        return new RedirectView("/admin/beats");
    }
    // Изменять вообще в принципе бит editBeat

    @PostMapping(value = "/beat/create")
    public RedirectView createBeat(BeatDetailed beat){
        beatService.createDetailedBeat(beat);
        return new RedirectView("/admin/beats");
    }
}
