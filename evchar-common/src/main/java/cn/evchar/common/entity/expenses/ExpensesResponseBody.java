package cn.evchar.common.entity.expenses;

import cn.evchar.common.util.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by 噼里啪啦嘣 on 2015/11/9.
 */
public class ExpensesResponseBody {
    private Long id;
    private String name;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date startTime;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date endTime;
    private Long parentId;
    private Double useHour;
    private Double price;
    private Double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Double getUseHour() {
        return useHour;
    }

    public void setUseHour(Double useHour) {
        this.useHour = useHour;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return useHour*price;
    }

}
