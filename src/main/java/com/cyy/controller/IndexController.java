package com.cyy.controller;

import com.cyy.domain.Plan;
import com.cyy.model.TableResult;
import com.cyy.model.Total;
import com.cyy.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fm")
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping("/plan")
    public TableResult getPlanList(int page, int limit) {
        List<Plan> planList = indexService.getPlanList();
        TableResult result = new TableResult();
        result.setCode(0);
        result.setMsg("");
        result.setData(planList);
        result.setCount(planList.size());
        return result;
    }

    @GetMapping("/total")
    public Total getTotal() {
        return indexService.getTotal();
    }

    @PostMapping("/add-plan")
    public boolean addPlan(@RequestBody Plan plan) {
        return indexService.addPlan(plan);
    }
}
