package com.Health.bmi.controller;

import com.Health.bmi.model.Data;
import com.Health.bmi.repository.DataRepository;
import com.Health.bmi.service.InterfaceImplementation.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DataController {
    @Autowired
    private DataRepository repo;
    @Autowired
    private DataService dataservice;

    @GetMapping("/registerData")
    public String getRegister_Page(Model model) {
        model.addAttribute("dataRequest", new Data());
        model.addAttribute("RegistrationPage", "Add new Product");
        return "data_page";
    }


    @GetMapping("/datas")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @Cacheable(value= "dataa")
    public String DataList(Data data, Model model, @Param("keyword") String keyword, Authentication authentication) {
        List<Data> ListOfData = dataservice.Datalist(keyword,data);
        model.addAttribute("ListOfData", ListOfData);
        model.addAttribute("keyword", keyword);

        return "Display";
    }

    @PostMapping("/saveData")
//    @CacheEvict(value = "dataa", allEntries = true)
    public String register(@ModelAttribute Data data) {
        System.out.println("register request :" + data);
        Data registeredData = dataservice.SaveData(data);
        return registeredData == null ? "error_page" : "index";
    }


    @GetMapping("/Updatedata/{id}")
//    @CachePut(value = "dataa", key = "#id")
    public String UpdateData(@PathVariable("id") Integer id, Model model) {
        Optional<Data> info = repo.findFirstById(id);
        Data dataa = info.get();
        model.addAttribute("dataa", dataa);
        System.out.println("Data Updated Successfully!!");
        return "EditPage";
    }

    @GetMapping("Deletedata/{id}")
//    @CacheEvict(value = "dataa", key = "#id")
    public String deleteData(@PathVariable("id") Integer id){
        repo.deleteById(id);
        System.out.println("Data Deleted Successfully!!");
        return"redirect:/datas";

    }
}
