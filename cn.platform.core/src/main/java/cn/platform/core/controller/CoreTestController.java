package cn.platform.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping({"/coreTest"})
public class CoreTestController {
    private static final Logger logger = LoggerFactory.getLogger(CoreTestController.class);

    @Value("${testUrl}")
    private String url;

    @RequestMapping( value = {"/coreTestMap"}, method = {RequestMethod.GET} )
    @ResponseBody
    public Map<String, Object> coreTestMap(HttpServletRequest request, @RequestParam Map<String, Object> map) throws Exception {
        map.put("package", "core");
        map.put("testUrl",url);
        return map;
    }


    public static void main(String[] args) {
        Integer a = 11;
        Integer b = 11;
        System.out.println(a.equals(b));
        System.out.println(a == b);
    }
}