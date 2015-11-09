package cn.evchar.common.entity.Organization;

import cn.evchar.common.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 噼里啪啦嘣 on 2015/11/6.
 */
@Entity
@Table(name="evchar_organization_device")
public class OrgUser extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "orgid")
    private Long orgId;
    @Column(name = "deviceid")
    private Long deviceId;
    @Column(name = "createtime")
    private Date createTime;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
