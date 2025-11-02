package spring.project.workforge.offerpreparation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.project.workforge.offerpreparation.model.Text;
import spring.project.workforge.offerpreparation.repository.TextRepository;

@RestController
//@RequestMapping("/api/v1/test")
public class TestController {
    private final TextRepository textRepository;

    public TestController(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @GetMapping("/")
    ResponseEntity<String> get() {
        return ResponseEntity.ok("testing endpoint for readiness probe");
    }

    @GetMapping("/10")
    ResponseEntity<String> get2() {
        return ResponseEntity.ok("different endpoint");
    }

    @GetMapping("/database")
    public ResponseEntity<Text> database() {
        Text text = new Text();
        text.setText("Testing text");
        return new ResponseEntity<>(textRepository.save(text), HttpStatus.OK);
    }

}
