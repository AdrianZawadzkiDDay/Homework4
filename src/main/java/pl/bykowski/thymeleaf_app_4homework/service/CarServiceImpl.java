package pl.bykowski.thymeleaf_app_4homework.service;

import org.springframework.stereotype.Service;
import pl.bykowski.thymeleaf_app_4homework.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> cars;

    public CarServiceImpl() {
        cars = new ArrayList<>();
        cars.add(new Car(1, "Ford", "Fusion", "czarny"));
        cars.add(new Car(2, "Tesla", "S", "czarny"));
        cars.add(new Car(3, "Toyota", "Yaris Hybrid", "srebrny"));
    }


    @Override
    public List<Car> getCarsList() {
        return cars;
    }

    @Override
    public Car getCarById(int id) {
        Optional<Car> optionalCar = cars.stream().filter(car -> car.getId() == id).findFirst();
        if (optionalCar.isPresent()) {
            return optionalCar.get();
        }
        return null;
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        return cars.stream().filter(car -> car.getColor().equals(color)).collect(Collectors.toList());
    }

    @Override
    public boolean addCar(Car car) {
        if (getCarById(car.getId()) != null) {
            return false;
        }
        cars.add(car);
        return true;
    }

    @Override
    public boolean modifyEntireCar(Car newCar) {
        Car foundCar = getCarById(newCar.getId());
        if (foundCar != null) {
            cars.remove(foundCar);
            cars.add(newCar);
            return true;
        }
        return false;
    }

    @Override
    public boolean modifyCar(Car newCar) {
        Car foundCar = getCarById(newCar.getId());
        if (foundCar != null) {
            foundCar.modifyCar(newCar);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeCar(int id) {
        Car foundCar = getCarById(id);
        if (foundCar != null) {
            cars.remove(foundCar);
            return true;
        }
        return false;
    }
}
