package com.cws.utils;

import com.cws.configure.PushConfigure;
import com.cws.pojo.Weather;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * PushUtil
 * 编写推送类
 * @author cws
 * @date 2022/8/22 21:40
 */
@Slf4j
public class PushUtil {
    /**
     * 消息推送主要业务代码
     */
    public static String push() {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(PushConfigure.getAppId());
        wxStorage.setSecret(PushConfigure.getSecret());
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        // 推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(PushConfigure.getUserId())
                .templateId(PushConfigure.getTemplateId())
                .build();
        // 配置你的信息
        long loveDays = MemoryDayUtil.calculationLianAi(PushConfigure.getLoveDate());
        long birthdays = MemoryDayUtil.calculationBirthday(PushConfigure.getBirthday());
        Weather weather = WeatherUtil.getWeather();
        if (weather == null) {
            templateMessage.addData(new WxMpTemplateData("weather", "***", "#00FFFF"));
        } else {
            templateMessage.addData(new WxMpTemplateData("remork", "\uD83D\uDC92", "#00FFFF"));
            templateMessage.addData(new WxMpTemplateData("date", weather.getDate() + "  " + weather.getWeek(), "#00BFFF"));
            templateMessage.addData(new WxMpTemplateData("weather", weather.getText_now(), "#00FFFF"));
            templateMessage.addData(new WxMpTemplateData("low", weather.getLow() + "", "#173177"));
            templateMessage.addData(new WxMpTemplateData("temp", weather.getTemp() + "", "#EE212D"));
            templateMessage.addData(new WxMpTemplateData("high", weather.getHigh() + "", "#FF6347"));
            templateMessage.addData(new WxMpTemplateData("city", weather.getCity() + "", "#173177"));
        }

        templateMessage.addData(new WxMpTemplateData("loveDays", loveDays + "", "#FF1493"));
        templateMessage.addData(new WxMpTemplateData("birthdays", birthdays + "", "#FFA500"));

//        String remark = "亲爱的乖乖宝贝，早上好!记得要吃早餐哦，今天也要开心哦 =^_^= ";
//        if (loveDays % 365 == 0) {
//            remark = "\n今天是恋爱" + (loveDays / 365) + "周年纪念日!";
//        }
//        if (birthdays == 0) {
//            remark = "\n今天是生日,生日快乐呀!";
//        }
//        if (loveDays % 365 == 0 && birthdays == 0) {
//            remark = "\n今天是生日,也是恋爱" + (loveDays / 365) + "周年纪念日!";
//        }
//
//        templateMessage.addData(new WxMpTemplateData("remark", remark, "#FF1493"));
        templateMessage.addData(new WxMpTemplateData("rainbow", RainbowUtil.getRainbow(), "#FF69B4"));
        System.out.println(templateMessage.toJson());
        try {
            WxMpTemplateMsgService templateMsgService = wxMpService.getTemplateMsgService();
            System.out.println(templateMsgService.getAllPrivateTemplate().toString());
            templateMsgService.sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            log.info("推送失败：" + e.getMessage());
            return "推送失败：" + e.getMessage();
        }
        log.info("推送成功!");
        return "推送成功!";
    }
}
