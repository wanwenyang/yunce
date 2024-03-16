package net.xdclass.service.ui;

import net.xdclass.dto.UiOperationResultDTO;
import net.xdclass.model.UiCaseStepDO;

public interface SeleniumDispatcherService {

    UiOperationResultDTO operatioDispatcher(UiCaseStepDO uiCaseStepDO);
}
