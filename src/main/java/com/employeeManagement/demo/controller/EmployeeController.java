package com.employeeManagement.demo.controller;

import com.employeeManagement.demo.entity.Employee;
import com.employeeManagement.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    //Only one constructor, @Autowired is optional.
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel){
        //get the employees from database
        List<Employee> theEmployees = employeeService.findAll();
        //add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/add")
    public String showFormForAdd(Model theModel){

        //create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee theEmployee,
                               BindingResult bindingResult,
                               Model theModel){
        System.out.println("Binding results: " + bindingResult.toString()+"\n");
        if (bindingResult.hasErrors()) {
            theModel.addAttribute("errorMessage", "Unable to update, try again.");
            return "employees/employee-form";
        }else{
            employeeService.save(theEmployee);
            theModel.addAttribute("successMessage", "Success!");
            return "employees/employee-form";
        }
    }

    @GetMapping("/update")
    public String updateEmployee(@RequestParam("employeeId")int theId, Model theModel){
        //get employee from service
        Employee theEmployee = employeeService.findById(theId);
        //set employee in the model to populate the form
        theModel.addAttribute("employee", theEmployee);
        //send over to the form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId")int theId, RedirectAttributes redirectAttributes){
        //delete employee from service
        employeeService.deleteById(theId);
        redirectAttributes.addFlashAttribute("successMessage", "Deletion Completed");
        return "redirect:/employees/list";
    }
}
