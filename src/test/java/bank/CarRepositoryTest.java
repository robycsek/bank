package bank;

import bank.backend.Car;
import bank.backend.CarRepository;
import bank.backend.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
@Sql(statements = "delete from cars")
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testSaveAndList() {
        carRepository.save(new Car("Trabant", "601"));
        carRepository.save(new Car("Honda", "Jazz"));

        List<Car> cars = carRepository.findByBrand("Trabant");
        assertEquals(1, cars.size());
        assertEquals("Trabant", cars.get(0).getBrand());
    }
}
