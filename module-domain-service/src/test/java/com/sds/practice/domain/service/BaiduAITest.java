//package com.sds.practice.domain.service;
//
//import com.baidu.aip.nlp.AipNlp;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.Test;
//
//import java.util.HashMap;
//
///**
// * @Project : best-practice-project
// * @Package Name : com.sds.practice.domain.service
// * @Description : TODO
// * @Author : tanchang
// * @Create Date : 2018年07月27日 下午12:04
// * @ModificationHistory Who   When     What
// * ------------    --------------    ---------------------------------
// */
//public class BaiduAITest {
//    //设置APPID/AK/SK
//    public static final String APP_ID = "11596066";
//    public static final String API_KEY = "i8dqvYAXajj6WZHyrXYlMue7";
//    public static final String SECRET_KEY = "c8uHn56Poqo2PMGfOwZiDRlrluGGnbnm";
//
//    @Test
//    public void test001() throws JSONException {
//        // 初始化一个AipNlp
//        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
//
//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
//
//        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
////        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
////        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
//
//        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
//        // 也可以直接通过jvm启动参数设置此环境变量
////        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
//
//        // 调用接口
////        String text = "百度是一家高科技公司";
////        JSONObject res = client.lexer(text, null);
//
//        JSONObject res = client.simnet("所有大专及大专以上学历都必须在学信网可查才是国家承认学历。教育部规定的国民教育系列包括：统招（普通高等教育），自考，成教（或成人高考）以及电大（也叫开放性大学），远程教育（也叫网络教育）；其中除了统招都是成人教育学历且在学信网可查属于国家承认的成人教育学历。另外非国民教育系列中０８年之后毕业的中央党校学历也在学信网可查，属于国家承认的成人教育学历",
//                "大专在学信网可查是国家承认学历",null);
//        System.out.println(res.toString(2));
//
//    }
//
//    @Test
//    public void test002() throws JSONException {
//        // 初始化一个AipNlp
//        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
//
//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
////        String text = "嗯~~~ 是啊，是这样的，我们确实不能保证考试能够通过，但是我们能够积极为您提供各种考试培训课程和开始计划";
////        String text = "算了，不说了";
////        String text = "我靠，你是怎么想的";
////        String text = "我靠，我要骂人了";
////        String text = "我不喜欢你";
//        String text = "你好，很高兴认识你，我给你介绍一下我们的服务吧：）";
//        // 传入可选参数调用接口
//        HashMap<String, Object> options = new HashMap<String, Object>();
//
//        // 情感倾向分析
//        JSONObject res = client.sentimentClassify(text, options);
//        System.out.println(res.toString(2));
//    }
//
//}
