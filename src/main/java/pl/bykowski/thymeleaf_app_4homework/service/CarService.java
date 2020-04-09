package pl.bykowski.thymeleaf_app_4homework.service;

import pl.bykowski.thymeleaf_app_4homework.model.Car;
import java.util.List;

public interface CarService {

    List<Car> getCarsList();
    Car getCarById(int id);
    List<Car> getCarsByColor(String color);
    boolean addCar(Car car);
    boolean modifyEntireCar(Car car);
    boolean modifyCar(Car car);
    boolean removeCar(int id);
}
