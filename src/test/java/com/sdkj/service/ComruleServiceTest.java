package com.sdkj.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sdkj.pmodel.ArticleModel;
import com.sdkj.pmodel.ColumnModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.util.ZJ_JacksonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
// 使用junit4进行测试
@ContextConfiguration({ "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
// 加载配置文件
public class ComruleServiceTest {
	@Autowired
	private IconService iconService;
	@Autowired
	private ColumnService columnService;
	@Autowired
	private ArticleService articleService;

	@Test
	// 标明是测试方法
	@Transactional
	// 标明此方法需使用事务
	@Rollback(false)
	// 标明使用完此方法后事务不回滚,true时为回滚
	public void testDatagrid() {
		PageModel page = new PageModel();
		EasyuiDatagrid datagrid = iconService.datagrid(page);
		System.out.println(ZJ_JacksonUtils.toJson(datagrid));
	}

	@Test
	// 标明是测试方法
	@Transactional
	// 标明此方法需使用事务
	@Rollback(false)
	// 标明使用完此方法后事务不回滚,true时为回滚
	public void aa() {
		List<ColumnModel> list1 = columnService.columnListFirst();
		List<ColumnModel> list2 = columnService.columnListSecond();
		for (ColumnModel columnModel : list1) {
			for (ColumnModel column2 : list2) {
				if (column2.getPid().equals(columnModel.getId())) {
					List li = columnModel.getList();
					// System.out.println(ZJ_JacksonUtils.toJson(li));
					li.add(column2);
					// System.out.println(ZJ_JacksonUtils.toJson(li));
				}
			}
		}
		 System.out.println(ZJ_JacksonUtils.toJson(list2));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void gsxw() {
	
		ColumnModel topcolumn = columnService.columnByArticleId("5IERJW4O7SO08");
		System.out.println(ZJ_JacksonUtils.toJson(topcolumn));
	}

}
