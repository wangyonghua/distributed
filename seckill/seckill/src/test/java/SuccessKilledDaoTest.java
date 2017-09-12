import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {
    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInertSuccessKilled() throws Exception {
        int result = successKilledDao.inertSuccessKilled(1000L, 28059830451L);
        System.out.println(result);

    }

    @Test
    public void testQueryByIdWithSecKill() throws Exception {
        SuccessKilled successKilled = successKilledDao.queryByIdWithSecKill(1000l);
        System.out.println(successKilled.toString());
    }
}

