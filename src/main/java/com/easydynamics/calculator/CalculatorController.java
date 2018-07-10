package com.easydynamics.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class CalculatorController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("canvas", new CalcWrapper());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String operation(@RequestParam(required = true) String operation, @ModelAttribute("canvas") CalcWrapper wrapper) {

        try {
            wrapper.setA(NumberService.normalize(wrapper.getA()));
        } catch (IllegalArgumentException e) {
            wrapper.setResult("Argument A is invalid!");
            return "index";
        }

        try {
            wrapper.setB(NumberService.normalize(wrapper.getB()));
        } catch (IllegalArgumentException e) {
            wrapper.setResult("Argument B is invalid!");
            return "index";
        }

        if (operation.equalsIgnoreCase("add")) {
            wrapper.setResult(CalculatorService.add(wrapper.getA(), wrapper.getB()));
        } else if (operation.equalsIgnoreCase("subtract")) {
            wrapper.setResult(CalculatorService.subtract(wrapper.getA(), wrapper.getB()));
        } else if (operation.equalsIgnoreCase("multiply")) {
            wrapper.setResult(CalculatorService.multiply(wrapper.getA(), wrapper.getB()));
        } else if (operation.equalsIgnoreCase("divide")) {
            wrapper.setResult(CalculatorService.divide(wrapper.getA(), wrapper.getB()));
        } else {
            wrapper.setResult("Unknown operation!");
        }

        return "index";
    }
}
