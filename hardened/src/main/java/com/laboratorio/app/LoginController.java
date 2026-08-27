package com.laboratorio.app;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private final JdbcTemplate jdbcTemplate;

    public LoginController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        String sql =
                "SELECT id, username, password, role, email " +
                "FROM users " +
                "WHERE username = ? " +
                "AND password = ?";

        try {

            List<Map<String, Object>> users =
                    jdbcTemplate.queryForList(
                            sql,
                            username,
                            password
                    );

            if (!users.isEmpty()) {
                model.addAttribute("users", users);
                model.addAttribute("query", sql);
                return "dashboard";
            }

            model.addAttribute(
                    "error",
                    "Usuario o contraseña incorrectos"
            );

            model.addAttribute("query", sql);

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    "Error ejecutando la consulta"
            );

            model.addAttribute("query", sql);
        }

        return "login";
    }
}
