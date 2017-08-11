package ifox.hh.entity;

/**
 * Created by 41988 on 2017/8/9.
 */
public class Record {
    private Integer rid;
    private String beginTime;
    private Double costMoney;
    private Integer recordId;
    private String endTime;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Record() {
    }

    public Record(Integer rid, String beginTime,String endTime, Double costMoney) {
        this.rid = rid;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.costMoney = costMoney;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public Double getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(Double costMoney) {
        this.costMoney = costMoney;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
    
}
