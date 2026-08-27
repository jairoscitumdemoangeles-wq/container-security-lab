package com.laboratorio.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @GetMapping("/employees")
    public List<Map<String, Object>> getEmployees() {

        return List.of(
            Map.of(
                "id", 1,
                "name", "Ana",
                "department", "Development"
            ),
            Map.of(
                "id", 2,
                "name", "Carlos",
                "department", "Security"
            ),
            Map.of(
                "id", 3,
                "name", "Laura",
                "department", "Infrastructure"
            )
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {

        return Map.of(
            "status", "UP",
            "application", "container-security-lab"
        );
    }
}
