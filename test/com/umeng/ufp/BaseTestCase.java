package com.umeng.ufp;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kewang
 *
 */

@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4    
@ContextConfiguration("classpath*:application-context/applicationContext.xml")    
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)

@Transactional
public abstract class BaseTestCase {
}
