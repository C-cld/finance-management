package com.cyy.service;

import com.cyy.dao.PlanMapper;
import com.cyy.domain.Plan;
import com.cyy.model.Total;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {
    @Autowired
    PlanMapper planMapper;
    public List<Plan> getPlanList() {
        return planMapper.getPlanList();
    }

    public Total getTotal() {
        return planMapper.getTotal();
    }

    public boolean addPlan(Plan plan) {
        try {
            planMapper.addPlan(plan);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
