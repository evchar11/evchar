package cn.evchar.common.requestparam;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by 噼里啪啦嘣 on 2015/11/6.
 */
public class CreateOrganizationRequestParam {
    //组织名称
    private String name;
    //创建人
    @NotBlank
    private Long userid;
    //类别
    @NotBlank
    private Integer clazz;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getClazz() {
        return clazz;
    }

    public void setClazz(Integer clazz) {
        this.clazz = clazz;
    }
}
