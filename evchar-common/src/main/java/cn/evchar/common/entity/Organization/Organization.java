package cn.evchar.common.entity.Organization;

import cn.evchar.common.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 噼里啪啦嘣 on 2015/11/6.
 */
@Entity
@Table(name="evchar_organization")
public class Organization extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "userid")
    private Long userID;
    @Column(name = "clazz")
    private Integer clazz;
    @Column(name = "createtime")
    private Date createTime;

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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Integer getClazz() {
        return clazz;
    }

    public void setClazz(Integer clazz) {
        this.clazz = clazz;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
