package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GirlService {

    @Autowired
    GirlRepository girlRepository;

    @Transactional
    public void insertTwo(){
        Girl girlA=new Girl();
        girlA.setName("1");
        girlA.setAge(5);
        girlRepository.save(girlA);

        Girl girlB=new Girl();
        girlB.setName("测试22");
        girlB.setAge(26);
        girlRepository.save(girlB);

    }
}
