package ru.springgb.clientviewservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springgb.cliententity.model.Task;
import ru.springgb.clienttask.service.TaskService;

import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/index")
public class ViewController {

    private TaskService taskService;


    //private final ExecutorService executorService;

//    @Autowired
//    public TaskViewController(TaskService taskService, ExecutorService executorService) {
//        this.taskService = taskService;
//        this.executorService = executorService;
//    }
    //главная
    @GetMapping
    public String getIndex() {
        return "index";
    }

    //списки тасок и исполнителей
    @GetMapping("/tasks")
    public String indexTask(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }
//    @GetMapping("/executors")
//    public String indexExec(Model model) {
//        model.addAttribute("executors", executorService.findAll());
//        return "executors";
//    }


/////////////// получение списков задач и исполнителей
    @GetMapping("/tasks/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        return "new";
    }
    @PostMapping("/tasks")
    public String create(@ModelAttribute("task") @Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "new";
        }
        taskService.createTask(task);
        return "redirect:tasks";
    }
//    @GetMapping("/executors/newExecutor")
//    public String newExecutor(Model model) {
//        model.addAttribute("executor", new Executor());
//        return "newExecutor";
//    }
//    @PostMapping("/executors")
//    public String createExecutors(@ModelAttribute("executor") @Valid Executor executor, BindingResult result) {
//        if (result.hasErrors()) {
//            return "newExecutor";
//        }
//        executorService.save(executor);
//        return "redirect:executors";
//    }
    /////////////////////


/////////// по id
    @GetMapping("/tasks/{id}")
    public String getTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "taskProfile";
    }

//    @GetMapping("/executors/{id}")
//    public String getExecutor(@PathVariable Long id, Model model) {
//        model.addAttribute("executor", executorService.findById(id));
//        return "executorProfile";
//    }
//////////////

    ////////изменение
//    @PostMapping("/tasks/update/{id}")
//    private String updateTaskValid(@PathVariable("id") Long id, @ModelAttribute @Valid Task task, BindingResult result) {
//        if (result.hasErrors()) {
//            return "updateTask";
//        }
//        Task task1= taskService.updateTask(task.getId(),task);
//        taskService.save(task1);
//        return "redirect:/index/tasks";
//    }
    @GetMapping("/tasks/updateTask/{id}")
    public String updateTask(@PathVariable(value = "id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "updateTask";
    }

//    @PostMapping("/executors/updateExecutor/{id}")
//    private String updateExecutorValid(@PathVariable("id") Long id,
//                                       @ModelAttribute @Valid Executor executor, BindingResult result) {
//        if (result.hasErrors()) {
//            return "updateExecutor";
//        }
//        executor.setId(id);
//        executorService.apdateExecutor(executor);
//        return "redirect:/index/executors";
//    }

//    @GetMapping("/executors/update/{id}")
//    public String updateExecutor(@PathVariable(value = "id") Long id, Model model) {
//        Executor executor = executorService.findById(id);
//        model.addAttribute("executor", executor);
//        return "updateExecutor";
//    }
    /////////

    /////добавление по id

//    @PostMapping("/tasks/executors/{id}")
//    public String addExecutorInTask(@PathVariable Long id,@ModelAttribute("executor") @Valid Executor executor){
//        taskService.createExecutorForTask(id,executor);
//        return "redirect:/index/tasks";
//    }
//
//    @PostMapping("/executors/tasks/{id}")
//    public String addTaskInExecutor(@PathVariable Long id,@ModelAttribute("task") @Valid Task task){
//        taskService.createTaskForExecutor(id,task);
//        return "redirect:/index/executors";
//    }



    ///////

/////////// удаление
//    @GetMapping("/tasks/delete/{id}")
//    private String deleteTask(@PathVariable("id") Long id) {
//        taskService.deleteById(id);
//        return "redirect:/index/tasks";
//    }

//    @GetMapping("/executors/delete/{id}")
//    private String deleteExecutor(@PathVariable("id") Long id) {
//        executorService.deleteById(id);
//        return "redirect:/index/executors";
//    }
    ///////////////////

    @GetMapping({"/searchTask","/searchTask{status}"})
    public String searchTaskByTitle(@ModelAttribute("status") @RequestParam("status") Optional<String> status,
                                    Model model,Task task){
        System.out.println(status);

        if(status.isPresent()) {
            List<Task> taskList = taskService.getTaskStatus(status.get());
            model.addAttribute("task",task);
            model.addAttribute("task2", taskList);
            return "tasks";
        }
        else
        {
            return "task/searchTask";}
    }



}
