package com.movision.controller.app;

import com.movision.common.Response;
import com.movision.facade.address.AddressFacade;
import com.movision.facade.order.OrderAppFacade;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shuxf
 * @Date 2017/3/3 20:41
 */
@RestController
@RequestMapping("/app/orders/")
public class AppOrdersController {

    @Autowired
    private OrderAppFacade orderAppFacade;

    @Autowired
    private AddressFacade addressFacade;

    @ApiOperation(value = "计算配送距离和运费", notes = "根据前台App提交的地址经纬度，计算配送距离和运费", response = Response.class)
    @RequestMapping(value = "calculateLogisticsfee", method = RequestMethod.POST)
    public Response calculateLogisticsfee(@ApiParam(value = "经度") @RequestParam String lng,
                                          @ApiParam(value = "纬度") @RequestParam String lat) {
        Response response = new Response();

        double fee = addressFacade.calculateLogisticsfee(lng, lat);

        if (response.getCode() == 200) {
            response.setMessage("计算成功");
            response.setData(fee);
        } else {
            response.setMessage("计算失败");
        }
        return response;
    }

    @ApiOperation(value = "APP提交订单接口", notes = "用于用户提交订单的接口", response = Response.class)
    @RequestMapping(value = "commitOrder", method = RequestMethod.POST)
    public Response commitOrder(@ApiParam(value = "地址id") @RequestParam String addressid,
                                @ApiParam(value = "购物车id") @RequestParam String cartids,
                                @ApiParam(value = "配送方式（0 自提 1 快递（送货上门））") @RequestParam String takeway,

                                @ApiParam(value = "开票种类：1 普通发票 2 增值税发票") @RequestParam String kind,
                                @ApiParam(value = "状态：1 个人 2企业（普通发票时为必填）") @RequestParam(required = false) String onlystatue,
                                @ApiParam(value = "发票抬头（个人时取姓名 企业和增值税时取企业名称）（普通发票时为必填）") @RequestParam(required = false) String head,
                                @ApiParam(value = "开票内容") @RequestParam String content,
                                @ApiParam(value = "发票邮寄地址id") @RequestParam String invoiceaddressid,
                                @ApiParam(value = "企业名称(增值税发票时为必填)") @RequestParam(required = false) String companyname,
                                @ApiParam(value = "注册地址(增值税发票时为必填)") @RequestParam(required = false) String rigaddress,
                                @ApiParam(value = "注册电话(增值税发票时为必填)") @RequestParam(required = false) String rigphone,
                                @ApiParam(value = "开户银行(增值税发票时为必填)") @RequestParam(required = false) String bank,
                                @ApiParam(value = "银行账户（卡号）(增值税发票时为必填)") @RequestParam(required = false) String banknum,
                                @ApiParam(value = "纳税识别码(增值税发票时为必填)") @RequestParam(required = false) String code,

                                @ApiParam(value = "优惠券id(选填)") @RequestParam(required = false) String couponid,
                                @ApiParam(value = "使用积分数(选填)") @RequestParam(required = false) String points,
                                @ApiParam(value = "买家留言（订单备注,选填）") @RequestParam(required = false) String message,
                                @ApiParam(value = "快递费") @RequestParam String logisticsfee,
                                @ApiParam(value = "订单总额") @RequestParam String totalprice
    ) {
        Response response = new Response();

        int flag = orderAppFacade.commitOrder(addressid, cartids, takeway, kind, onlystatue, head, content, invoiceaddressid,
                companyname, rigaddress, rigphone, bank, banknum, code, couponid, points, message, logisticsfee, totalprice);

        if (flag == 1) {
            response.setCode(200);
            response.setMessage("订单提交成功");
        } else if (flag == -1) {
            response.setCode(300);
            response.setMessage("订单提交失败");
        }
        return response;
    }
}