package com.arielzarate.GarageManagementSystem.interfaces.errors;

import com.arielzarate.GarageManagementSystem.interfaces.errors.exceptions.ApplicationErrorException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ApplicationErrorException.class)
    public String handleApplicationError(ApplicationErrorException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        log.warn("Application error: {} - {} [{}]", ex.getError().status(), ex.getError().message(), ex.getError().type());

        String attr = switch (ex.getError().type()) {
            case "warning" -> "warningMsg";
            case "info"    -> "infoMsg";
            case "success" -> "successMsg";
            default       -> "errorMsg";
        };
        redirectAttributes.addFlashAttribute(attr, ex.getError().message());

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }
}
