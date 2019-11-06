package api.base;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yiran.api.YiRanApiApplication;


/**
 * 测试基础类
 * @author pandaa
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes=YiRanApiApplication.class)
@Transactional
@Rollback(value=false)
public class ApiBaseJunit {
	
}
