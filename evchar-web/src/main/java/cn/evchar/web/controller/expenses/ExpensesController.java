package cn.evchar.web.controller.expenses;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.expenses.Expenses;
import cn.evchar.common.requestparam.ExpansesRequestParam;
import cn.evchar.service.expenses.ExpensesService;
import cn.evchar.web.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 噼里啪啦嘣 on 2015/11/9.
 */
@Controller
@RequestMapping("expenses")
public class ExpensesController extends AbstractController {
    private static Logger logger = LoggerFactory
            .getLogger(ExpensesController.class);
    @Resource
    private ExpensesService expensesService;
    /**
     * 计算费用
     */
    @RequestMapping("getCalculationExpenses.action")
    @ResponseBody
    public String getCalculationExpenses(ExpansesRequestParam expansesRequestParam) {
        return createJsonResponse(ApiCode.SUCCESS, expensesService.findExpensesByTime(expansesRequestParam.getStartTime(),expansesRequestParam.getEndTime(),expansesRequestParam.getSpecId()), "查询成功！");
    }
    /**
     * 费用规格查询
     */
    @RequestMapping("findExpenses.action")
    @ResponseBody
    public String findExpenses( ) {
        Expenses expenses=new Expenses();
        expenses.setParentId(0l);
        return createJsonResponse(ApiCode.SUCCESS,expensesService.findExpenses(expenses) , "查询成功！");
    }
}
