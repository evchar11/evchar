package cn.evchar.service.expenses;

import cn.evchar.common.entity.expenses.Expenses;
import cn.evchar.common.entity.expenses.ExpensesResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by 噼里啪啦嘣 on 2015/11/9.
 */
public interface ExpensesService {
    List<ExpensesResponseBody> findExpensesByTime(Date startTime,Date endTime,Long spec);
    List<Expenses> findExpenses(Expenses expenses);
}
