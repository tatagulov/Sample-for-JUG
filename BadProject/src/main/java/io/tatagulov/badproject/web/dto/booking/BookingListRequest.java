package io.tatagulov.badproject.web.dto.booking;

import io.tatagulov.badproject.web.dto.BaseListRequest;

public class BookingListRequest extends BaseListRequest {
    private String startDate;
    private String endDate;
    private Integer maxTotalAmmount;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getMaxTotalAmmount() {
        return maxTotalAmmount;
    }

    public void setMaxTotalAmmount(Integer maxTotalAmmount) {
        this.maxTotalAmmount = maxTotalAmmount;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
