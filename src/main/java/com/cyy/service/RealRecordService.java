package com.cyy.service;

import com.cyy.dao.RealRecordMapper;
import com.cyy.domain.RealRecord;
import com.cyy.model.FinanceTypeEnum;
import com.cyy.model.Total;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealRecordService {

    @Autowired
    RealRecordMapper realRecordMapper;

    public Total getRealTocal() {
        Total total = new Total();
        total.setInvest(realRecordMapper.getRealRecordByType(FinanceTypeEnum.INVEST.getCode()));
        total.setDeposit(realRecordMapper.getRealRecordByType(FinanceTypeEnum.DEPOSIT.getCode()));
        total.setEduFund(realRecordMapper.getRealRecordByType(FinanceTypeEnum.EDUFUND.getCode()));
        return total;
    }

    public boolean updateRealRecord(RealRecord realRecord) {
        try {
            realRecordMapper.updateRealRecord(realRecord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
