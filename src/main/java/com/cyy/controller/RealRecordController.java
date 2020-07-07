package com.cyy.controller;

import com.cyy.domain.RealRecord;
import com.cyy.model.Total;
import com.cyy.service.RealRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fm")
public class RealRecordController {

    @Autowired
    RealRecordService realRecordService;

    @GetMapping("/real-total")
    public Total getRealTocal() {
        return realRecordService.getRealTocal();
    }

    /**
     * 更新实际金额
     * @param realRecord
     * @return
     */
    @PostMapping("/update-real-record")
    public boolean updateRealRecord(@RequestBody RealRecord realRecord) {
        return realRecordService.updateRealRecord(realRecord);
    }
}
