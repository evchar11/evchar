package cn.evchar.common.requestparam;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by 噼里啪啦嘣 on 2015/11/9.
 */
public class ExpansesRequestParam {
    @NotNull
    private Date startTime;
    @NotNull
    private Date endTime;
    @NotNull
    private Long specId;

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

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }
}
