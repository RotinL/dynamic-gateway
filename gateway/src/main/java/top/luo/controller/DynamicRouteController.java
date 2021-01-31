package top.luo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luo.service.DynamicRouteService;

/**
 * 提供刷新路由接口
 *
 * @author luo
 * @date 2021-01-30 15:03
 */
@RestController
@RequestMapping("/route")
public class DynamicRouteController {

    private DynamicRouteService service;

    @Autowired
    public void setService(DynamicRouteService service) {
        this.service = service;
    }

    @GetMapping("/refresh")
    public void refresh() {
        service.refresh();
    }

}
