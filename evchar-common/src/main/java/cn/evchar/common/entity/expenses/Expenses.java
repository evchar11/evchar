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
@Table(name="evchar_expenses")
public class Expenses  extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "createtime")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;
    @Column(name = "parentid")
    private Long parentId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
