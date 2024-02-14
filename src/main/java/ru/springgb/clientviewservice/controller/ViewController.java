package ru.springgb.clientviewservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public interface ViewController {

    String getIndex();

    String indexTask(Model model);

    String indexExec(Model model);

    String newTask(Model model);


}
