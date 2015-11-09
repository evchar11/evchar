package cn.evchar.service.expenses.impl;

import cn.evchar.common.entity.expenses.Expenses;
import cn.evchar.common.entity.expenses.ExpensesPrice;
import cn.evchar.common.entity.expenses.ExpensesResponseBody;
import cn.evchar.common.util.DateUtils;
import cn.evchar.mybatisDao.dao.ExpensesMapper;
import cn.evchar.mybatisDao.dao.ExpensesPriceMapper;
import cn.evchar.service.expenses.ExpensesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 噼里啪啦嘣 on 2015/11/9.
 */
@Service("expensesService")
public class ExpensesServiceImpl implements ExpensesService{
    @Resource
    private ExpensesMapper expensesMapper;
    @Resource
    private ExpensesPriceMapper expensesPriceMapper;
    @Override
    public List<ExpensesResponseBody> findExpensesByTime(Date startTime, Date endTime,Long spec) {
        List<ExpensesResponseBody> expensesResponseBodyList=new ArrayList<>();
        Expenses expenses=new Expenses();
        expenses.setParentId(spec);
        List<Expenses> expensesList=expensesMapper.select(expenses);
        ExpensesPrice expensesPrice;
        for (Expenses e:expensesList){
            Date start=new Date();
            start.setTime(startTime.getTime());
            expensesPrice=new ExpensesPrice();
            expensesPrice.setExpensesId(e.getId());
            List<ExpensesPrice> expensesPriceList=expensesPriceMapper.select(expensesPrice);
            for (ExpensesPrice ex:expensesPriceList){
                if (start.getHours()>=ex.getStartTime().getHours()&&start.getHours()<ex.getEndTime().getHours()){
                    ExpensesResponseBody expensesResponseBody=new ExpensesResponseBody();
                    expensesResponseBody.setId(e.getId());
                    expensesResponseBody.setName(e.getName());
                    expensesResponseBody.setParentId(e.getParentId());
                    Date endate=new Date();
                    endate.setTime(start.getTime());
                    endate.setHours(ex.getEndTime().getHours());
                    endate.setMinutes(ex.getEndTime().getMinutes());
                    Date starttime=new Date();
                    starttime.setTime(start.getTime());
                    expensesResponseBody.setStartTime(starttime);

                    if (endate.getTime()>=endTime.getTime()){
                        expensesResponseBody.setUseHour(DateUtils.getHours(start, endTime));
                        Date endtime=new Date();
                        endtime.setTime(endTime.getTime());
                        expensesResponseBody.setEndTime(endtime);
                    }else{
                        expensesResponseBody.setUseHour(DateUtils.getHours(start, endate));
                        Date endtime=new Date();
                        endtime.setTime(endate.getTime());
                        expensesResponseBody.setEndTime(endtime);
                    }
                    expensesResponseBody.setPrice(ex.getPrice());
                    start.setHours(ex.getEndTime().getHours());
                    start.setMinutes(ex.getEndTime().getMinutes());
                    expensesResponseBodyList.add(expensesResponseBody);
                }
            }
        }
        return expensesResponseBodyList;
    }

    @Override
    public List<Expenses> findExpenses(Expenses expenses) {
        return expensesMapper.select(expenses);
    }


}
