package org.seckill.web;

import org.seckill.dto.Exposer;
import org.seckill.dto.SecKillExecution;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SecKillCloseException;
import org.seckill.service.SecKillService;
import org.seckill.service.SecKillStatEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyonghua on 2017/9/12.
 */
@RestController
@RequestMapping("/seckill/")//模块/资源
public class SecKillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SecKillService secKillService;

    @RequestMapping(name = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<SecKill> list = secKillService.getSecKillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{secKillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("secKillId") Long secKillId, Model model) {
        if (secKillId == null) {
            return "redirect:/seckill/list";
        }

        SecKill secKill = secKillService.getById(secKillId);

        if (secKill == null) {
            return "redirect:/seckill/list";
        }

        model.addAttribute("secKill", secKill);
        return "detail";
    }

    @RequestMapping(value = "/{secKillId}/exposer", method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SecKillResult<Exposer> exposer(@PathVariable("secKillId") Long secKillId) {
        SecKillResult<Exposer> result = null;

        try {
            Exposer exposer = secKillService.exportSecKillUrl(secKillId);
            result = new SecKillResult<Exposer>(true, exposer);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SecKillResult<Exposer>(false, e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/{secKillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    public SecKillResult<SecKillExecution> excute(@PathVariable("secKillId") Long secKillId,
                                                  @PathVariable("md5") String md5,
                                                  @CookieValue(value = "killPhone", required = false) Long userPhone) {
        //springmvc valid
        if (userPhone == null) {
            return new SecKillResult<SecKillExecution>(false, "未注册");
        }

        SecKillResult<SecKillExecution> result = null;

        try {
            SecKillExecution secKillExecution = secKillService.executeSecKill(secKillId, userPhone, md5);
            result = new SecKillResult<SecKillExecution>(true, secKillExecution);

        } catch (RepeatKillException e) {
            SecKillExecution secKillExecution = new SecKillExecution(secKillId, SecKillStatEnum.REPEAT);
            result = new SecKillResult<SecKillExecution>(false, secKillExecution);

        } catch (SecKillCloseException e) {
            SecKillExecution secKillExecution = new SecKillExecution(secKillId, SecKillStatEnum.END);
            result = new SecKillResult<SecKillExecution>(false, secKillExecution);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            SecKillExecution secKillExecution = new SecKillExecution(secKillId, SecKillStatEnum.INNER_ERROR);
            result = new SecKillResult<SecKillExecution>(false, secKillExecution);
        }

        return result;
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    public SecKillResult<Long> time() {
        Date now = new Date();
        return new SecKillResult<Long>(true, now.getTime());

    }
}
