package cn.evchar.web.controller.expenses;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.expenses.Expenses;
import cn.evchar.common.entity.expenses.ExpensesPrice;
import cn.evchar.service.expenses.ExpensesPriceService;
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
@RequestMapping("expensesPrice")
public class ExpensesPriceController extends AbstractController {
    private static Logger logger = LoggerFactory
            .getLogger(ExpensesController.class);

    @Resource
    private ExpensesPriceService expensesPriceService;

    /**
     * 费用规格查询
     */
    @RequestMapping("findPrice.action")
    @ResponseBody
    public String findExpenses(Long expID ) {
        ExpensesPrice expensesPrice=new ExpensesPrice();
        expensesPrice.setExpensesId(expID);
        return createJsonResponse(ApiCode.SUCCESS,expensesPriceService.findExpensesPrice(expensesPrice) , "查询成功！");
    }
}
