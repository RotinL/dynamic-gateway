package top.luo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EchoController
 *
 * @author luo
 * @date 2021-01-30 14:03
 */
@RestController
@RequestMapping("echo")
public class EchoController {

    @GetMapping
    public String echo(String name) {
        return String.format("echo: %s", name);
    }

}
