package com.cyy.service;

import com.cyy.dao.IndexMapper;
import com.cyy.domain.Plan;
import com.cyy.model.Total;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {
    @Autowired
    IndexMapper indexMapper;
    public List<Plan> getPlanList() {
        return indexMapper.getPlanList();
    }

    public Total getTotal() {
        return indexMapper.getTotal();
    }

    public boolean addPlan(Plan plan) {
        try {
            indexMapper.addPlan(plan);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
