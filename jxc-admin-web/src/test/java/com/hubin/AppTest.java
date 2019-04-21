package com.hubin;

import static org.junit.Assert.assertTrue;

import com.hubin.services.finance.AuthItemInfoService;
import com.hubin.utils.annotation.DefineDataSourceAnnotation;
import com.hubin.utils.enums.DBTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest 
{
    @Autowired
    AuthItemInfoService authItemInfoService;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void selectAuthItemLists() {
        System.err.println("项目数量："+authItemInfoService.getItemList().size());
    }
}
