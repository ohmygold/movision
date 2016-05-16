package com.zhuhuibao.business.memCenter.PriceManage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wordnik.swagger.annotations.ApiOperation;
import com.zhuhuibao.common.MsgCodeConstant;
import com.zhuhuibao.common.util.ShiroUtil;
import com.zhuhuibao.utils.file.FileUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhuhuibao.common.ApiConstants;
import com.zhuhuibao.common.Constant;
import com.zhuhuibao.common.JsonResult;
import com.zhuhuibao.mybatis.memCenter.entity.AskPrice;
import com.zhuhuibao.mybatis.memCenter.entity.AskPriceSimpleBean;
import com.zhuhuibao.mybatis.memCenter.entity.OfferPrice;
import com.zhuhuibao.mybatis.memCenter.service.OfferPriceService;
import com.zhuhuibao.shiro.realm.ShiroRealm.ShiroUser;
import com.zhuhuibao.utils.JsonUtils;
import com.zhuhuibao.utils.pagination.model.Paging;
import com.zhuhuibao.utils.pagination.util.StringUtils;

@RestController
@RequestMapping("/rest/price/")
public class OfferPriceController {
	
	private static final Logger log = LoggerFactory.getLogger(OfferPriceController.class);
	
	@Resource
	private OfferPriceService offerService;
	
	@Autowired
    ApiConstants ApiConstants;

	@ApiOperation(value="我要报价(清单和单一产品)",notes="我要报价(清单和单一产品)",response = JsonResult.class)
	@RequestMapping(value="addOfferPrice", method = RequestMethod.POST)
	public JsonResult addOfferPrice(OfferPrice price) throws JsonGenerationException, JsonMappingException, IOException
	{
		log.info("add offer price");
		JsonResult jsonResult = new JsonResult();
		Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession(false);
        if(session != null)
        {
        	ShiroUser principal = (ShiroUser)session.getAttribute("member");
        	price.setCreateid(new Long(principal.getId()));
			jsonResult = offerService.addOfferPrice(price);
        }
		return jsonResult;
	}

	@ApiOperation(value="询价需求功能：查询所有正在询价中的信息（分页）",notes="询价需求功能：查询所有正在询价中的信息（分页）",response = JsonResult.class)
	@RequestMapping(value="queryAskingPriceInfo",method = RequestMethod.GET)
	public JsonResult queryAskingPriceInfo(AskPrice price,
										   @RequestParam(required = false) String pageNo,
										   @RequestParam(required = false) String pageSize) throws JsonGenerationException, JsonMappingException, IOException
	{
		JsonResult jsonResult = new JsonResult();
		Long createID= ShiroUtil.getCreateID();
		if(createID != null) {
			price.setCreateid(String.valueOf(createID));
			if (StringUtils.isEmpty(pageNo)) {
				pageNo = "1";
			}
			if (StringUtils.isEmpty(pageSize)) {
				pageSize = "10";
			}
			if(price.getTitle() != null && !price.getTitle().equals(""))
			{
				price.setTitle(price.getTitle().replace("_","\\_"));
			}
			Paging<AskPriceSimpleBean> pager = new Paging<AskPriceSimpleBean>(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			List<AskPriceSimpleBean> priceList = offerService.findAllAskingPriceInfo(pager, price);
			pager.result(priceList);
			jsonResult.setData(pager);
		}
        return jsonResult;
	}

	@ApiOperation(value="我的报价：根据条件查询自己所有报价信息（分页）",notes="我的报价：根据条件查询自己所有报价信息（分页）",response = JsonResult.class)
	@RequestMapping(value="queryOfferedPriceInfo",method = RequestMethod.GET)
	public JsonResult queryOfferedPriceInfo(@RequestParam(required = false) String title,
											@RequestParam(required = false) String startDate,
											@RequestParam(required = false) String endDate,
											@RequestParam(required = false) String pageNo,
											@RequestParam(required = false) String pageSize) throws JsonGenerationException, JsonMappingException, IOException
	{
		JsonResult jsonResult = new JsonResult();
		Long createID= ShiroUtil.getCreateID();
		if(createID != null ) {
			if (StringUtils.isEmpty(pageNo)) {
				pageNo = "1";
			}
			if (StringUtils.isEmpty(pageSize)) {
				pageSize = "10";
			}
			Paging<AskPriceSimpleBean> pager = new Paging<AskPriceSimpleBean>(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			Map<String, String> priceMap = new HashMap<String, String>();
			if(title != null && !title.equals(""))
			{
				priceMap.put("title", title.replace("_","\\_"));
			}
			priceMap.put("startDate", startDate);
			priceMap.put("endDate", endDate);
			priceMap.put("createid", String.valueOf(createID));
			List<AskPriceSimpleBean> priceList = offerService.findAllOfferedPriceInfo(pager, priceMap);
			pager.result(priceList);
			jsonResult.setData(pager);
		}
        return jsonResult;
	}

	@ApiOperation(value="公开，定向，单一产品报价查询",notes="公开，定向，单一产品报价查询",response = JsonResult.class)
	@RequestMapping(value="queryOfferPriceInfoByID", method = RequestMethod.GET)
	public JsonResult queryOfferPriceInfoByID(@RequestParam Long id) throws JsonGenerationException, JsonMappingException, IOException
	{
		log.info("query offer priece info by id ");
		JsonResult jsonResult = offerService.queryOfferPriceInfoByID(id);
		return jsonResult;
	}

	@ApiOperation(value="下载报价单，询价单",notes="下载报价单，询价单",response = JsonResult.class)
	@RequestMapping(value="downloadBill", method = RequestMethod.GET)
	public JsonResult downloadBill(HttpServletResponse response,@RequestParam Long id,@RequestParam String type) throws JsonGenerationException, JsonMappingException, IOException
	{
		JsonResult jsonResult = new JsonResult();
		log.info("query offer priece info by id ");
		try {
			String fileurl = offerService.downloadBill(id, type);
			response.setDateHeader("Expires", 0);
			response.setHeader("Cache-Control",
					"no-store, no-cache, must-revalidate");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Content-disposition", "attachment;filename=" + fileurl);
			response.setContentType("application/octet-stream");
			fileurl = ApiConstants.getUploadDoc() + Constant.upload_price_document_url + "/" + fileurl;
			jsonResult = FileUtil.downloadFile(response, fileurl);
		}
		catch(Exception e)
		{
			log.error("download bill error! ",e);
		}
		return jsonResult;
	}

	@ApiOperation(value="查看某条询价信息的所有报价信息",notes="查看某条询价信息的所有报价信息",response = JsonResult.class)
	@RequestMapping(value="queryAllOfferPriceByAskID", method = RequestMethod.GET)
	public JsonResult queryAllOfferPriceByAskID(@RequestParam Long id) throws JsonGenerationException, JsonMappingException, IOException
	{
		log.info("query all offer priece by askid ");
		JsonResult jsonResult = offerService.queryAllOfferPriceByAskID(id);
		return jsonResult;
	}

	@ApiOperation(value="查看回复的具体某条报价信息(清单,单一产品)",notes="查看回复的具体某条报价信息(清单,单一产品)",response = JsonResult.class)
	@RequestMapping(value="queryOfferPriceByID", method = RequestMethod.GET)
	public JsonResult queryOfferPriceByID(@RequestParam Long id) throws JsonGenerationException, JsonMappingException, IOException
	{
		log.info("query offer priece info by id ");
		JsonResult jsonResult = offerService.queryOfferPriceByID(id);
		return jsonResult;
	}
}
