package com.movision.controller.app.mine;

import com.movision.common.Response;
import com.movision.common.util.ShiroUtil;
import com.movision.facade.address.AddressFacade;
import com.movision.mybatis.address.entity.Address;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author zhuangyuhao
 * @Date 2017/2/27 19:58
 */
@RestController
@RequestMapping("app/mine/address")
public class AddressController {

    @Autowired
    private AddressFacade addressFacade;

    @ApiOperation(value = "获取我的地址列表", notes = "获取我的地址列表", response = Response.class)
    @RequestMapping(value = {"/get_my_address_list"}, method = RequestMethod.GET)
    public Response getMyAddressList() {
        Response response = new Response();
        List<Map<String, Object>> list = addressFacade.queryMyAddressList(ShiroUtil.getAppUserID());
        response.setData(list);
        return response;
    }

    @ApiOperation(value = "添加我的收获地址", notes = "添加我的收获地址", response = Response.class)
    @RequestMapping(value = {"/add_my_address"}, method = RequestMethod.POST)
    public Response addMyAddress(@ApiParam @ModelAttribute Address address) {
        Response response = new Response();
        addressFacade.addMyAddress(address);
        return response;
    }

    @ApiOperation(value = "编辑我的收获地址", notes = "编辑我的收获地址", response = Response.class)
    @RequestMapping(value = {"/edit_my_address"}, method = RequestMethod.POST)
    public Response editMyAddress(@ApiParam @ModelAttribute Address address) {
        Response response = new Response();
        addressFacade.updateAddress(address);
        return response;
    }

    @ApiOperation(value = "查询我的地址详情", notes = "查询我的地址详情", response = Response.class)
    @RequestMapping(value = {"/query_my_address_detail"}, method = RequestMethod.GET)
    public Response queryMyAddressDetail(@ApiParam(value = "地址id") @RequestParam String id) {
        Response response = new Response();
        Map map = addressFacade.queryAddressDetail(Integer.valueOf(id));
        response.setData(map);
        return response;
    }

}
