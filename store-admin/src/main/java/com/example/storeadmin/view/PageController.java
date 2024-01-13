package com.example.storeadmin.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class PageController {

    @RequestMapping(path = {"", "/main"})
    public ModelAndView mainPage() {

        return new ModelAndView("main");
    }

    @RequestMapping("/order")
    public ModelAndView orderPage() {

        return new ModelAndView("order/order");
    }

}
