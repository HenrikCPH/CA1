package facades;

import dto.CarDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CarFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    
    public List<CarDTO> getAllCars() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Car> query =  em.createQuery("SELECT m FROM Car m",Car.class);
        List<Car> cars = query.getResultList();
        List<CarDTO> carsDTOs = new ArrayList();
        cars.forEach((Car car) -> {
            carsDTOs.add(new carsDTO(car));
        });
        return carsDTOs; 
    }
     public void populate() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Car").executeUpdate();
            em.persist(new Car(1, 1997, "Ford", "E350", 3000));
            em.persist(new Car(2, 1999, "Chevy", "Venture", 4900));
            em.persist(new Car(3, 2000, "Chevy", "Venture", 5000));
            em.persist(new Car(4, 1996, "Jeep", "Grand Cherokee", 4799));
            em.persist(new Car(5,  2005, "Volvo", "V70", 44799));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    
}
    
 
   
