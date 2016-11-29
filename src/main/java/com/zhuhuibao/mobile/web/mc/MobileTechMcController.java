package com.zhuhuibao.mobile.web.mc;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zhuhuibao.aop.LoginAccess;
import com.zhuhuibao.common.Response;
import com.zhuhuibao.common.constant.TechConstant;
import com.zhuhuibao.mybatis.tech.service.TechCooperationService;
import com.zhuhuibao.mybatis.tech.service.TechDataService;
import com.zhuhuibao.service.MobileTechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/m/tech/mc/")
public class MobileTechMcController {
	
	@Autowired
	MobileTechService mTechSV;
	
	@Autowired
    TechDataService techDataService;
	
	@Autowired
    TechCooperationService techService;

	@LoginAccess
    @RequestMapping(value = "sel_tech_data", method = RequestMethod.GET)
    @ApiOperation(value = "搜索技术资料", notes = "搜索技术资料", response = Response.class)
    public Response findAllTechDataPager(@ApiParam(value = "一级分类") @RequestParam(required = false) String fCategory,
                                         @ApiParam(value = "标题") @RequestParam(required = false) String title,
                                         @ApiParam(value = "状态") @RequestParam(required = false) String status,
                                         @ApiParam(value = "页码") @RequestParam(required = false,defaultValue = "1") String pageNo,
                                         @ApiParam(value = "每页显示的数目") @RequestParam(required = false,defaultValue = "10") String pageSize) {
		
		return mTechSV.findAllTechDataPager(fCategory, title, status, pageNo, pageSize);
    }
	
	@RequestMapping(value = "sel_tech_data_detail", method = RequestMethod.GET)
    @ApiOperation(value = "查询技术资料详情(行业解决方案，技术文档，培训资料)", notes = "查询技术资料详情(行业解决方案，技术文档，培训资料)", response = Response.class)
    public Response selectTechDataDetail(@ApiParam(value = "技术资料ID") @RequestParam String techDataId) {
        Map<String, String> techData = techDataService.selectMCTechDataDetail(Long.parseLong(techDataId));
        Response response = new Response();
        response.setData(techData);
        return response;
    }
	
	@RequestMapping(value = "del_tech_data", method = RequestMethod.POST)
    @ApiOperation(value = "删除技术资料(行业解决方案，技术文档，培训资料)", notes = "删除技术资料(行业解决方案，技术文档，培训资料)", response = Response.class)
    public Response deleteTechData(@ApiParam(value = "技术资料ID") @RequestParam() String techDataId) {
		
        Response response = new Response();
        Map<String, Object> condition = new HashMap<>();
        condition.put("id", techDataId);
        condition.put("status", TechConstant.TechCooperationnStatus.DELETE.toString());
        int result = techDataService.deleteTechData(condition);
        return response;
    }
	
	@LoginAccess
    @RequestMapping(value = {"sel_tech_cooperation", "cg/sel_tech_cooperation", "xq/sel_tech_cooperation"}, method = RequestMethod.GET)
    @ApiOperation(value = "搜索技术合作(技术成果，技术需求)", notes = "搜索技术合作(技术成果，技术需求)", response = Response.class)
    public Response findAllTechCooperationPager(@ApiParam(value = "标题") @RequestParam(required = false) String title,
                                                @ApiParam(value = "类型：1成果，2需求") @RequestParam(required = false) String type,
                                                @ApiParam(value = "状态") @RequestParam(required = false) String status,
                                                @ApiParam(value = "页码") @RequestParam(required = false, defaultValue = "1") String pageNo,
                                                @ApiParam(value = "每页显示的数目") @RequestParam(required = false, defaultValue = "10") String pageSize) {
		
		return mTechSV.findAllTechCooperationPager(title, type, status, pageNo, pageSize);
    }
	
	@RequestMapping(value = {"sel_tech_cooperation_detail", "cg/sel_tech_cooperation_detail", "xq/sel_tech_cooperation_detail"}, method = RequestMethod.GET)
    @ApiOperation(value = "查询技术合作(技术成果，技术需求)", notes = "查询技术合作(技术成果，技术需求)", response = Response.class)
    public Response selectTechCooperationById(@ApiParam(value = "技术合作成果、需求ID") @RequestParam String techCoopId) {
        Response response = new Response();
        Map<String, String> techCoop = techService.selectMcCoopDetail(techCoopId);
        response.setData(techCoop);
        return response;
    }
	
	@LoginAccess
    @RequestMapping(value = {"del_tech_cooperation", "cg/del_tech_cooperation", "xq/del_tech_cooperation"}, method = RequestMethod.POST)
    @ApiOperation(value = "删除技术合作(技术成果，技术需求)", notes = "删除技术合作(技术成果，技术需求)", response = Response.class)
    public Response deleteTechCooperation(@ApiParam(value = "技术合作ID") @RequestParam() String techId) {

        mTechSV.batchDeleteTechCoop(techId);
        return new Response();
    }
	
	@RequestMapping(value = "sel_download_data", method = RequestMethod.GET)
    @ApiOperation(value = "我下载的技术资料", notes = "我下载的技术资料", response = Response.class)
    public Response findAllTechDataPager(@ApiParam(value = "页码") @RequestParam(required = false) String pageNo,
                                         @ApiParam(value = "每页显示的数目") @RequestParam(required = false) String pageSize) {
        
		return mTechSV.findAllDownloadTechDataPager(pageNo, pageSize);
    }
	
	@RequestMapping(value = "sel_site_tech_cooperation", method = RequestMethod.GET)
    @ApiOperation(value = "我查看的技术成果列表", notes = "我查看的技术成果列表", response = Response.class)
    public Response findSiteAllTechCooperationPager(@ApiParam(value = "系统分类") @RequestParam(required = false) String systemCategory,
                                                @ApiParam(value = "应用领域") @RequestParam(required = false) String applicationArea,
                                                @ApiParam(value = "类型：1成果，2需求") @RequestParam(required = false) String type,
                                                @ApiParam(value = "页码") @RequestParam(required = false,defaultValue = "1") String pageNo,
                                                @ApiParam(value = "每页显示的数目") @RequestParam(required = false,defaultValue = "10") String pageSize) {
		
        return mTechSV.findSiteAllTechCooperationPager(systemCategory, applicationArea, type, pageNo, pageSize);
    }
	
	
	@RequestMapping(value = "sel_site_tech_cooperation_detail", method = RequestMethod.GET)
    @ApiOperation(value = "我查看的技术成果详情", notes = "我查看的技术成果详情", response = Response.class)
    public Response previewSiteTechCooperation(@ApiParam(value = "技术需求ID") @RequestParam String techCoopId) {
        
		return mTechSV.previewSiteTechCooperation(techCoopId);
    }

    @LoginAccess
    @RequestMapping(value = "cg/del_batch_my_looked_achievement", method = RequestMethod.POST)
    @ApiOperation(value = "批量删除我查看过的技术成果", notes = "批量删除我查看过的技术成果", response = Response.class)
    public Response del_batch_my_looked_achievement(@RequestParam String ids) {
        Response response = new Response();
        String idlist[] = ids.split(",");
        for (String id : idlist) {
            techService.deleteLookedAchievement(id);
        }
        return response;
    }

}
