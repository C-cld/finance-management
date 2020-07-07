package com.cyy.controller;

import com.cyy.domain.Plan;
import com.cyy.domain.RealRecord;
import com.cyy.model.TableResult;
import com.cyy.model.Total;
import com.cyy.service.RealRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/real-record")
public class RealRecordController {

    @Autowired
    RealRecordService realRecordService;

    @GetMapping
    public TableResult getRealRecordList(int page, int limit, int type) {
        List<RealRecord> realRecordList = realRecordService.getRealRecordList(type);
        TableResult result = new TableResult();
        result.setCode(0);
        result.setMsg("");
        result.setData(realRecordList);
        result.setCount(realRecordList.size());
        return result;
    }

    @GetMapping("/real-total")
    public Total getRealTocal() {
        return realRecordService.getRealTocal();
    }

    /**
     * 更新实际金额
     * @param realRecord
     * @return
     */
    @PostMapping
    public boolean updateRealRecord(@RequestBody RealRecord realRecord) {
        return realRecordService.updateRealRecord(realRecord);
    }
}
