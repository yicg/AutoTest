package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class girlController {

    @Autowired
    private GirlRepository girlReprotise;

    /**
     *查询数据库中女生的列表信息
     */
    @GetMapping(value = "/getList")
    public List getList(){

        return girlReprotise.findAll();
    }
    /** -
     * 新增一个女生
     */
    Girl g;

    @PostMapping(value = "/addInfo")
    public Girl addGirl(@RequestParam(value = "Name") String name,
                        @RequestParam(value = "Age") Integer age){

        g=new Girl();
        g.setName(name);
        g.setAge(age);
        return girlReprotise.save(g);
    }
    /**
     * 修改一个女生
     */
    @PostMapping(value = "/updataInfo/{ID}")
    public Girl updataInfo(@PathVariable(value = "ID",required = true) Integer id,
                           @RequestParam(value = "Name",required = false) String name,
                           @RequestParam(value = "Age",required = false) Integer age){
        g=new Girl();
        g.setId(id);
        g.setAge(age);
        g.setName(name);

        return girlReprotise.save(g);
    }
    /**
     * 通过一个ID，查询女生信息
     */
    @GetMapping(value = "/get/girl/{ID}")
    public Girl getGirl(@PathVariable(value = "ID",required = true) Integer id){


        return girlReprotise.findOne(id);
    }
    /**
     * 通过年龄查询女生信息
     */
    @GetMapping(value = "/grtGirlList/{Age}")
    public List grtGirlList(@PathVariable(value = "Age",required = true) Integer age){

        return girlReprotise.findByAge(age);
    }

    /**
     * 通过ID删除女生列表
     */
    @DeleteMapping(value = "/get/delete/{ID}")
    public void deleteGirl(@PathVariable(value = "ID",required = true) Integer id){

      girlReprotise.delete(id);
    }
    /**
     * 事务管理
     */
    @Autowired
    private GirlService girlService;

    @PostMapping (value = "/girls/two")
    public void getTwo(){

       girlService.insertTwo();
    }
}
