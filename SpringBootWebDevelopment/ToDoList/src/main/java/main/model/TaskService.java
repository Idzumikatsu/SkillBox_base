package main.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public String index() {
        var formatter = DateTimeFormatter
                .ofPattern("EEEE, dd.MM.yyyy.\nHH:mm:ss a");
        return LocalDateTime.now().format(formatter);
    }

    public ResponseEntity<HttpStatus> add(String title, String description) {
        if (title.isEmpty() || description.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        taskRepository.save(new Task(title, description));
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    public List<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    public ResponseEntity<Task> get(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity patch(int id, Boolean isDone, String title, String description) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.get();
        if (isDone != null){
            task.setDone(isDone);
        }
        if (title != null) {
            task.setTitle(title);
        }
        if (description != null) {
            task.setDescription(description);
        }
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    public ResponseEntity delete(int id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
