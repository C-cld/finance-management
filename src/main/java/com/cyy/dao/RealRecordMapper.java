package com.cyy.dao;

import com.cyy.domain.RealRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RealRecordMapper {
    boolean updateRealRecord(RealRecord realRecord);
    Float getRealRecordByType(@Param("type") Integer type);
    List<RealRecord> getRealRecordList(@Param("type")Integer type);
}
