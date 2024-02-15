package aed.rest.RestSpring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView manejarNotFound(Exception ex){
        ModelAndView model = new ModelAndView();
        model.setViewName("error");
        model.addObject("error",ex.getMessage());
        return model;
    }





}
