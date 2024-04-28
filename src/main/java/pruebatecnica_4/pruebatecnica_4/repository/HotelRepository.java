package pruebatecnica_4.pruebatecnica_4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pruebatecnica_4.pruebatecnica_4.model.Hotel;

@Repository
public class HotelRepository extends JpaRepository<Hotel, Long> {


}
