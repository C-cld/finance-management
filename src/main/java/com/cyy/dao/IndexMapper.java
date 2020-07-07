package com.cyy.dao;

import com.cyy.domain.Plan;
import com.cyy.domain.RealRecord;
import com.cyy.model.Total;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndexMapper {
    List<Plan> getPlanList();
    Total getTotal();
    void addPlan(Plan plan);
}
