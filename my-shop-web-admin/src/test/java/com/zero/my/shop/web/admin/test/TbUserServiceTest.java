package com.zero.my.shop.web.admin.test;

import com.zero.my.shop.domain.TbContentCategory;
import com.zero.my.shop.domain.TbUser;
import com.zero.my.shop.web.admin.service.TbContentCategoryService;
import com.zero.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @classname: TbUserServiceTest
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-18 20:57
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private TbUserService tbUserService;
    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @Test
    public void testSelectAll() {
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser:tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("zero");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("wf5201314".getBytes()));
        tbUser.setPhone("12345678901");
        tbUser.setEmail("123456@qq.com");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.save(tbUser);
    }

    @Test
    public void testDelete() {
        tbUserService.delete(38L);
    }

    @Test
    public void testGetById() {
        TbUser user = tbUserService.getById(36L);
        System.out.println(user.getUsername());
    }

    @Test
    public void testUpdate() {
        TbUser tbUser = tbUserService.getById(39L);
        System.out.println(tbUser.getUsername());
        tbUser.setUsername("Author");
        tbUser.setPhone("111111111111");
        tbUser.setEmail("1111111@qq.com");
        tbUser.setUpdated(new Date());

        tbUserService.update(tbUser);
    }

    @Test
    public void testSave() {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("hu");
        tbUser.setPhone("222222222");
        tbUser.setEmail("1090863704@qq.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("wf5201314".getBytes()));
        tbUser.setUpdated(new Date());
        tbUserService.save(tbUser);
    }

    @Test
    public void testDao() {
        TbContentCategory tbContentCategory = tbContentCategoryService.getById(30L);
        System.out.println(tbContentCategory.toString());
//        List<TbContentCategory> tbContentCategories = tbContentCategoryService.selectAll();
//        for (TbContentCategory tbContentCategory : tbContentCategories) {
//            System.out.println(tbContentCategory.toString());
//        }
    }

    @Test
    public void testMd5() {
        System.out.println(DigestUtils.md5DigestAsHex("wf5201314".getBytes()));
    }
}
