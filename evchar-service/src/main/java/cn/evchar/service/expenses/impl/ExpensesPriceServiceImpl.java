package cn.evchar.service.expenses.impl;

import cn.evchar.common.entity.expenses.ExpensesPrice;
import cn.evchar.mybatisDao.dao.ExpensesPriceMapper;
import cn.evchar.service.expenses.ExpensesPriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 噼里啪啦嘣 on 2015/11/9.
 */
@Service("expensesPriceService")
public class ExpensesPriceServiceImpl implements ExpensesPriceService{
    @Resource
    private ExpensesPriceMapper expensesPriceMapper;
    @Override
    public List<ExpensesPrice> findExpensesPrice(ExpensesPrice expensesPrice) {
        return expensesPriceMapper.select(expensesPrice);
    }
}
