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
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date startTime;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    @Column(name = "endtime")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date endTime;

    @Column(name = "price")
    private Double price;

    @Column(name = "createtime")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;
}
