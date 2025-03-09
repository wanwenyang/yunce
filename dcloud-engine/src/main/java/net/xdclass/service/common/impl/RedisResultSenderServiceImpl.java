package net.xdclass.service.common.impl;

import net.xdclass.dto.common.CaseInfoDTO;
import net.xdclass.enums.TestTypeEnum;
import net.xdclass.service.common.ResultSenderService;
import org.springframework.stereotype.Service;

/**
 *
 **/
//@Service
public class RedisResultSenderServiceImpl implements ResultSenderService {
    @Override
    public void sendResult(CaseInfoDTO caseInfoDTO, TestTypeEnum testTypeEnum, String result) {

    }
}
