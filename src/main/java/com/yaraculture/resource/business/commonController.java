package com.yaraculture.resource.business;


import com.yaraculture.resource.business.starinfo.service.StarInfoService;
import com.yaraculture.resource.common.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/commonDelete")
public class commonController {

    @Autowired
    private StarInfoService starInfoService;


    @GetMapping(value = "/starInfo")
    public Result<Boolean> deleteById(@RequestParam("id") String id) {
        starInfoService.removeById(id);
        return Result.of(true);
    }

}

