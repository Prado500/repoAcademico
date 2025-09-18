package dal.example.POCEmpleado.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
        @GetMapping(value = "/healthCheck")
        public String healthCheck(){
            return "Status Ok!";
        }

    @GetMapping(value = "/getSalary")
        public double getSalary(double baseSalary){

            return baseSalary * 1.2;
        }

}
