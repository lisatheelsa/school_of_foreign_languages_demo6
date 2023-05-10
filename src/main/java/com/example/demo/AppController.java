package com.example.demo;
import java.util.List;

import com.example.demo.authorization.UserInfo;
import com.example.demo.authorization.UserService;
import com.example.demo.news.Record;
import com.example.demo.news.RecordService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired; // аннотация
import org.springframework.data.repository.query.Param; // привязываем наши параметры к передаче данных из sql запроса
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;  //определяет наш класс, как управляющий
import org.springframework.ui.Model; // интерфейс, необходимый для взаимодействия контроллера и конфигуратора Model u Controller, а также для добавления всех элементов в веб-интерфейс
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView; // метод, позволяющий указывать названия html страниц, которые мы подвязываем контроллеру

@Controller
public class AppController {
    @Autowired
    private StudentService service;
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;

    @RequestMapping("/") // сразу открывается главная страница
    public String viewHomePage(Model model, @Param("keyword") String keyword) { // метод модели
        List<Student> listCargos = service.listAll(keyword);
        model.addAttribute("listCargo", listCargos); // добавляем переменную, которую будем использовать в веб-интерфейсе
        model.addAttribute("keyword", keyword);
        return "index";
    }
    @RequestMapping("/new") // создаем контроллер по добавлению студента
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showNewConferenceForm(Model model) {
        Student cargo = new Student();
        model.addAttribute("cargo", cargo);
        return "new_student";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCargo(@ModelAttribute("cargo") Student cargo) { // метод сохранения студентов
        System.out.println(cargo);
        service.save(cargo);
        return "redirect:/";
    }
    @RequestMapping("/edit/{id}") // контроллер для редактирования студентов по ключу
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView showEditCargoForm(@PathVariable(name="id") Long id) {
        ModelAndView mav = new ModelAndView("edit_student");
        Student cargo = service.get(id);
        mav.addObject("Cargo", cargo);
        return mav; // возвращаем страницу с данными о студентах по id
    }
    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteCargo(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String addNewUser(@ModelAttribute UserInfo userInfo, @RequestParam String name, @RequestParam String roles, HttpSession session) {
        System.out.println("UserInfo object is: " + userInfo); // Debugging code
        userService.addUser(userInfo);
        session.setAttribute("username", name);
        session.setAttribute("roles", roles);
        return "redirect:/";
    }
    @GetMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        // logout the current user
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "privetik";
    }

    @GetMapping("/login_page") // is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
    public String showLogin() {// add any additional objects you want to pass to the HTML page
        return "login_page";
    }
    @PostMapping("/login_page")
    public String SuccessLogin(@RequestParam String username, HttpSession session) {
        System.out.println("Username is: " + username); // Debugging code
        // Authenticate the user and set the "username" attribute in the model
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//        HttpSession session = request.getSession(true);
        session.setAttribute("username", currentUser);
//        session.setAttribute("password", password);
        return "redirect:/";
    }


    @RequestMapping("/autoblog")
    public String viewAutoblog(Model model, @Param("keyword") String keyword) { // метод модели
        List<Record> listRecord = recordService.listAllRecords(keyword);
        model.addAttribute("listRecord", listRecord); // добавляем переменную, которую будем использовать в веб-интерфейсе
        model.addAttribute("keyword", keyword);
        return "autoblog";
    }

    @RequestMapping(value = "/saveRecord", method = RequestMethod.POST)
    public String saveRecord(@ModelAttribute("record") Record record) { // метод сохранения студентов
        System.out.println(record);
        recordService.save(record);
        return "redirect:/autoblog";
    }

    @RequestMapping("/newRecord") // создаем контроллер по добавлению студента
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showRecordForm(Model model) {
        Record record = new Record();
        model.addAttribute("record", record);
        return "new_record";
    }

    @RequestMapping("/editRecord/{id}") // контроллер для редактирования студентов по ключу
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView showEditRecordForm(@PathVariable(name="id") Long id) {
        ModelAndView mav = new ModelAndView("edit_record");
        Record record = recordService.get(id);
        mav.addObject("Record", record);
        return mav; // возвращаем страницу с данными о студентах по id
    }
    @RequestMapping("/deleteRecord/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteRecord(@PathVariable(name = "id") Long id) {
        recordService.delete(id);
        return "redirect:/autoblog";
    }
}
