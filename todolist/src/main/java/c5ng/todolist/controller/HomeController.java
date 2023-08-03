package c5ng.todolist.controller;

import c5ng.todolist.domain.ToDo;
import c5ng.todolist.service.ToDoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final ToDoService toDoService;

    @GetMapping("/")
    public String home(Model model) {
        log.info("여기타냐 ?");
        model.addAttribute("toDo", new ToDo());

        List<ToDo> toDos = toDoService.findToDos();
        model.addAttribute("toDos", toDos);

        return "home";
    }

    @PostMapping("/")
    public String addToDo(@RequestParam String title) {
        ToDo toDo = new ToDo();
        toDo.setTitle(title);
        toDo.setCompleted(false);
        toDo.setDeleted(false);

        toDoService.saveToDo(toDo);

        return "redirect:/";
    }


    @DeleteMapping("/delete")
    public String removeToDos() {
        log.info("여기타요오 ?");

        return "redirect:/";
    }


}
