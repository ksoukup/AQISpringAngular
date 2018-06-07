package com.fdmgroup.app;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class RedirectOnResourceNotFoundException {
	
	private static final Logger logger = LoggerFactory.getLogger(RedirectOnResourceNotFoundException.class);
	
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Object handleStaticResourceNotFound(final NoHandlerFoundException ex, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        logger.trace("Handling Exception");
    	String requestURI = req.getRequestURI();
    	if (requestURI.startsWith("/api") || requestURI.startsWith("/css") || requestURI.startsWith("/js") || requestURI.startsWith("/templates"))
            return this.getResourceNotFoundBody(ex, req);
        else {
            redirectAttributes.addFlashAttribute("errorMessage", "My Custom error message");
            return "redirect:/";
        }
    }

    private ResponseEntity<String> getResourceNotFoundBody(NoHandlerFoundException ex, HttpServletRequest req) {
        return new ResponseEntity<>("Not Found !!", HttpStatus.NOT_FOUND);
    }
}
