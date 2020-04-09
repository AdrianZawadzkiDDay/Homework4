package pl.bykowski.thymeleaf_app_4homework.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bykowski.thymeleaf_app_4homework.model.Car;
import pl.bykowski.thymeleaf_app_4homework.service.CarService;

import java.util.List;

@Controller
@RequestMapping(value = "/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String getCars(Model model) {
        List<Car> carList = carService.getCarsList();
        model.addAttribute("cars", carList);
        model.addAttribute("newCar", new Car());
        return "cars";
    }

    @GetMapping("/get-car/{id}")
    public String getCarById(@ModelAttribute Car car, Model model) {
        Car carById = carService.getCarById(car.getId());
        model.addAttribute("car", carById);
        if (carById != null) {
        }
        return "car-by-id";
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color) {
        List<Car> carList = carService.getCarsByColor(color);
        return new ResponseEntity(carList, HttpStatus.OK);
    }

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute Car newCar) {
        if (carService.addCar(newCar)) {
        }
        return "redirect:/cars";
    }

    @PostMapping("/modify")
    public String modifyEntireCar(@ModelAttribute Car newCar) {
        if (carService.modifyEntireCar(newCar)) {
        }
        return "redirect:/cars";
    }

    @PatchMapping
    public ResponseEntity modifyCar(@RequestBody Car newCar) {
        if (carService.modifyCar(newCar)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}")
    public String removeCar(@PathVariable int id) {
        if(carService.removeCar(id)){
        }
        return "redirect:/cars";
    }

}
