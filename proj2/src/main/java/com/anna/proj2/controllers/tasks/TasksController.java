package com.anna.proj2.controllers.tasks;

import com.anna.proj2.data.dao.TasksDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TasksController {

    @Autowired
    private TasksDao tasksDao;

    @GetMapping("/task1")
    public String performTask1(Model model) {

        List<String> result = tasksDao.performTask1();
        model.addAttribute("result", result);

        return "task1";
    }

    @GetMapping("/task2")
    public String performTask2(Model model) {

        //List<Object> taskResult = tasksDao.performTask2();
        //model.addAttribute("taskResult", taskResult);

        return "task2";
    }

    @GetMapping("/task3")
    public String performTask3(Model model) {

        //List<Object> taskResult = tasksDao.performTask3();
        //model.addAttribute("taskResult", taskResult);

        return "task3";
    }
}
