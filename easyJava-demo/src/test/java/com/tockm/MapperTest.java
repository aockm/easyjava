package com.tockm;

import com.tockm.entity.po.NameInfo;
import com.tockm.entity.po.PhoneInfo;
import com.tockm.entity.query.NameInfoQuery;
import com.tockm.entity.query.PhoneInfoQuery;
import com.tockm.mappers.NameInfoMapper;
import com.tockm.mappers.PhoneInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class MapperTest {
    @Resource
    private PhoneInfoMapper<PhoneInfo, PhoneInfoQuery> phoneInfoMapper;

    @Resource
    private NameInfoMapper<NameInfo, NameInfoQuery> nameInfoMapper;
    @Test
    public void selectList() {
        List<PhoneInfo> list = phoneInfoMapper.selectList(new PhoneInfoQuery());
        System.out.println(list.size());
    }

    @Test
    public void insert() {
        PhoneInfo phoneInfo = new PhoneInfo();
        phoneInfo.setPhone("1234567");
        phoneInfo.setCity("美国");
        phoneInfoMapper.insert(phoneInfo);
        System.out.println(phoneInfo.getId());

    }
    @Test
    public void update() {
        NameInfo nameInfo = new NameInfo();
        nameInfo.setName("lll");
        nameInfo.setCreateTime(new Date());
        nameInfoMapper.insertOrUpdate(nameInfo);
        System.out.println(nameInfo.getId());
    }
}
