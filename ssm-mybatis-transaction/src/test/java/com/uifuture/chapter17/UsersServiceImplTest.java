package com.uifuture.chapter17;

import com.uifuture.chapter17.service.IUsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.IOException;

@Slf4j
public class UsersServiceImplTest extends BaseTest {

    @Autowired
    private IUsersService usersService;


    //事务
    @Autowired
    private DataSourceTransactionManager transactionManager;

    /**
     * 不使用事务的测试
     *
     * @throws IOException
     */
    @Test
    public void updateMoneyByUsername() throws IOException {
        //a转账给b100元，不使用事务
        usersService.updateMoneyByUsername(-100 * 100, "a");
        usersService.updateMoneyByUsername(100 * 100, "b");
    }

    /**
     * 不使用事务的测试，步骤之间增加错误计算
     *
     * @throws IOException
     */
    @Test
    public void updateMoneyByUsername2() throws IOException {
        //a转账给b100元，不使用事务
        usersService.updateMoneyByUsername(-100 * 100, "a");
        //运行分母为0的除法运算
        int size = 100 / 0;
        usersService.updateMoneyByUsername(100 * 100, "b");
    }

    /**
     * 使用编程式事务的测试，步骤之间增加错误计算
     *
     * @throws IOException
     */
    @Test
    public void updateMoneyByUsername3() throws IOException {
        //开启事务
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            //a转账给b100元
            usersService.updateMoneyByUsername(-100 * 100, "a");
            //运行分母为0的除法运算
            int size = 100 / 0;
            usersService.updateMoneyByUsername(100 * 100, "b");

            //事务提交
            transactionManager.commit(transaction);
        } catch (Exception e) {
            log.error("转账异常，事务回滚", e);
            //回滚
            transactionManager.rollback(transaction);
        }
    }

    /**
     * 注解实现事务处理
     *
     * @throws IOException
     */
    @Test
    public void updateMoneyByUsername4() throws IOException {
        usersService.transfer("a", "b", 100);
    }

}
