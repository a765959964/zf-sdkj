package com.sdkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdkj.pmodel.Online;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.service.OnlineService;

@RequestMapping("/online")
@Controller
public class OnlineController {
	@Autowired
	private OnlineService onlineService;

	@RequestMapping("/datagrid")
	@ResponseBody
	public EasyuiDatagrid datagrid(Online online) {
		return onlineService.datagrid(online);
	}

}
