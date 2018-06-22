package bank.backend;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findByBrand(String brand);

    @Query("select c from Car c where c.brand = 'Trabant'")
    Car findMyFavourite();

}
