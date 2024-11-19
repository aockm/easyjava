package com.tockm;

import com.tockm.entity.po.NameInfo;
import com.tockm.entity.po.PhoneInfo;
import com.tockm.entity.query.NameInfoQuery;
import com.tockm.entity.query.PhoneInfoQuery;
import com.tockm.mappers.NameInfoMapper;
import com.tockm.mappers.PhoneInfoMapper;
import com.tockm.service.NameInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = RunDemoApplication.class)
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

    @Test
    public void insertBatch() {
        List<NameInfo> list = new ArrayList();
        NameInfo nameInfo = new NameInfo();
        nameInfo.setName("lll2122");
        nameInfo.setCreateTime(new Date());

        NameInfo nameInfo1 = new NameInfo();
        nameInfo1.setName("231ewq");
        nameInfo1.setCreateTime(new Date());
        list.add(nameInfo);
        list.add(nameInfo1);
        nameInfoMapper.insertOrUpdateBatch(list);
    }

    @Test
    public void selectById() {
        PhoneInfo phoneInfo = phoneInfoMapper.selectById(1);
        System.out.println(phoneInfo);
        NameInfo nameInfo = nameInfoMapper.selectById(6);
        System.out.println(nameInfo.getCreateTime());
        System.out.println(nameInfo);
    }
    @Test
    public void updateById() {

        NameInfo nameInfo = new NameInfo();
        nameInfo.setTitle("423");
        nameInfo.setCreateTime(new Date());
        nameInfoMapper.updateById(nameInfo,1);
    }
    @Test
    public void deleteById() {
        nameInfoMapper.deleteByName("lll2122");
    }

    @Resource
    private NameInfoService nameInfoService;
    @Test
    public void findListByParam() {
        NameInfoQuery nameInfoQuery = new NameInfoQuery();
        nameInfoQuery.setPageSize(1);
        List<NameInfo> listByParam = nameInfoService.findListByParam(nameInfoQuery);
        System.out.println(listByParam);
    }
}
