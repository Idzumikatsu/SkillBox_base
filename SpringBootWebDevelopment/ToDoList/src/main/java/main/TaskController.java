package main;

import main.model.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    public String index() {
        return taskService.index();
    }

    @PostMapping("/tasks")
    public ResponseEntity<HttpStatus> add(String title, String description) {
        return taskService.add(title, description);
    }

    @GetMapping("/tasks")
    public List<Task> list() {
        return taskService.list();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> get(@PathVariable int id) {
        return taskService.get(id);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity patch(@PathVariable int id, Boolean isDone, String title, String description) {
        return taskService.patch(id, isDone, title, description);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        return taskService.delete(id);
    }
}
