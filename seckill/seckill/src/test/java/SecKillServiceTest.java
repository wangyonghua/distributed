import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SecKillExecution;
import org.seckill.entity.SecKill;
import org.seckill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SecKillServiceTest {
 private final Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private SecKillService secKillService;
 @Test
 public void testGetSecKillList() throws Exception {
     List<SecKill> list = secKillService.getSecKillList();
     logger.info("list={}",list);
 }

 @Test
 public void testGetById() throws Exception {
     SecKill secKill = secKillService.getById(1000L);
     logger.info("secKill:{}",secKill);

 }

 /**
  * 测试完整业务，注意集成测试代码完整逻辑，注意可重复执行
  * @throws Exception
  */
 @Test
 public void testSecKillLogic() throws Exception {
     long id = 1000L;
     Exposer exposer = secKillService.exportSecKillUrl(id);

     if(exposer.isExposed()){
         logger.info("exposer={}",exposer);
         long phone = 18059830432L;
         SecKillExecution secKillExecution = secKillService.executeSecKill(id,phone,exposer.getMd5());
         logger.info("secKillExecution:{}",secKillExecution);
     }else{
         //秒杀未开始
         logger.warn("exposer={}",exposer);
     }
 }

 @Test
 public void testExportSecKillUrl() throws Exception {
     long id = 1000L;
     Exposer exposer = secKillService.exportSecKillUrl(id);
     logger.info("exposer={}",exposer);

 }

 @Test
 public void testExecuteSecKill() throws Exception {
     long id = 1000L;
     long phone = 18059830452L;
     String md5 = "f1974250b060f51c4a8e48df67232d53";

     SecKillExecution secKillExecution = secKillService.executeSecKill(id,phone,md5);

     logger.info("secKillExecution:{}",secKillExecution);

 }
}