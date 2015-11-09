package cn.evchar.service.expenses;

import cn.evchar.common.entity.expenses.ExpensesPrice;

import java.util.List;

/**
 * Created by 噼里啪啦嘣 on 2015/11/9.
 */
public interface ExpensesPriceService {
    List<ExpensesPrice> findExpensesPrice(ExpensesPrice expensesPrice);
}
