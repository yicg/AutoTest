package hello;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GirlRepository extends JpaRepository<Girl,Integer> {
        public List findByAge(Integer age);

}
