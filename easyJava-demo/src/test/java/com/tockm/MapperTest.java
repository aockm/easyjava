package com.tockm;

import com.tockm.entity.po.PhoneInfo;
import com.tockm.entity.query.PhoneInfoQuery;
import com.tockm.mappers.PhoneInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class MapperTest {
    @Resource
    private PhoneInfoMapper<PhoneInfo, PhoneInfoQuery> phoneInfoMapper;
    @Test
    public void selectList() {
        List<PhoneInfo> list = phoneInfoMapper.selectList(new PhoneInfoQuery());
        System.out.println(list.size());
    }
}
