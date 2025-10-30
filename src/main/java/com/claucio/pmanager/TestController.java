package com.claucio.pmanager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {


    @GetMapping("/ok")
    public ResponseEntity<String> sayOk(){
        return ResponseEntity.ok("Hello from Spring boot");

    }

    @PostMapping("/echo")
    public ResponseEntity<String> echo(@RequestBody String value){
        StringBuilder sb = new StringBuilder(value);
        sb.reverse();
        return ResponseEntity.ok(sb.toString());
    }
}
