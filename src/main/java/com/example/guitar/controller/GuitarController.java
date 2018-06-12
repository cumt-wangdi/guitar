package com.example.guitar.controller;

import com.example.guitar.dao.GuitarDao;
import com.example.guitar.entity.Guitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class GuitarController {
    @Autowired
    private GuitarDao guitarDao;
    //查找所有
    @GetMapping("/")
    public String findAllGuitar(Model model) throws SQLException {

        List<Guitar> list = guitarDao.findAllGuitar();
        model.addAttribute("guitars",guitarDao.findAllGuitar());
        return "list";
    }
    //根据id删除
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) throws SQLException {
        guitarDao.deleteById(id);
        return "redirect:/";
    }
    //根据名称查找
    @PostMapping("/findByName")
    public String findByName(@RequestParam("name") String name,Model model) throws SQLException {
        model.addAttribute("guitars",guitarDao.findByName(name));
        return "list";
    }

    //添加
    @PostMapping("/add")
    public String add(Guitar guitar) throws SQLException {
        guitarDao.addGuitar(guitar);
        return "redirect:/";
    }
}
