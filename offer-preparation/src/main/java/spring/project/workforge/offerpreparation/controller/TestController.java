package spring.project.workforge.offerpreparation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.project.workforge.offerpreparation.entity.Text;
import spring.project.workforge.offerpreparation.repository.TextRepository;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    private final TextRepository textRepository;

    @Autowired
    public TestController(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @GetMapping("/get1")
    ResponseEntity<String> get() {
        return ResponseEntity.ok("get");
    }

    @GetMapping("/get2")
    ResponseEntity<String> get2() {
        return ResponseEntity.ok("get");
    }

    @PostMapping("/post")
    ResponseEntity<Text> post() {
        Text text1 = new Text();
        text1.setText("text1");
        return new ResponseEntity<>(textRepository.save(text1), HttpStatus.OK);
    }
}