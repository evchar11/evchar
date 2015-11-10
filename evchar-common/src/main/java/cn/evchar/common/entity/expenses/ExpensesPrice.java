package cn.evchar.common.entity.expenses;

import cn.evchar.common.entity.AbstractEntity;
import cn.evchar.common.util.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 噼里啪啦嘣 on 2015/11/9.
 */
@Entity
@Table(name="evchar_expenses_price")
public class ExpensesPrice extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "expensesid")
    private Long expensesId;

    @Column(name = "starttime")
    private String startTime;

    @Column(name = "endtime")
    private String endTime;

    @Column(name = "price")
    private Double price;

    @Column(name = "createtime")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpensesId() {
        return expensesId;
    }

    public void setExpensesId(Long expensesId) {
        this.expensesId = expensesId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
