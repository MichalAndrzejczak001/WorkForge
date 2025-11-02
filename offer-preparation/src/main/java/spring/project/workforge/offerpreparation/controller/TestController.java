package spring.project.workforge.offerpreparation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/test")
public class TestController {
    @GetMapping("/")
    ResponseEntity<String> get() {
        return ResponseEntity.ok("testing endpoint for readiness probe");
    }

    @GetMapping("/9")
    ResponseEntity<String> get2() {
        return ResponseEntity.ok("different endpoint");
    }

}
