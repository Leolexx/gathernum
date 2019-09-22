package com.diff.check.gathernum;

import com.diff.check.calc.ProcessNumMng;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Slf4j
public class TestProcessNum {


    @Autowired
    ProcessNumMng processNumMng;

    /**
     * Проверка ввода и поиска чисел
     */
    @Test
    public void check() {
        log.info("check start");
        Consts.Result result = processNumMng.add("1");
        Assert.assertEquals(result, Consts.Result.OK);
        processNumMng.add("12");
        processNumMng.add("3");
        processNumMng.add("167");
        processNumMng.add("20");

        Optional<Integer> opt = processNumMng.getMax();
        boolean isPresent = opt.isPresent();
        Assert.assertTrue(isPresent);
        if (isPresent) {
            Assert.assertEquals(167, (int) opt.get());
        }

        opt = processNumMng.getMin();
        isPresent = opt.isPresent();
        Assert.assertTrue(isPresent);
        if (isPresent) {
            Assert.assertEquals(1, (int) opt.get());
        }

        Optional<Double> num = processNumMng.getAverage();
        isPresent = num.isPresent();
        Assert.assertTrue(isPresent);
        if (isPresent) {
            Assert.assertEquals(new Double(40.6), num.get());
        }
        log.info("check end");
    }

}
