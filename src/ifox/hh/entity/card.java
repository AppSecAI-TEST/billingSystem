package ifox.hh.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 41988 on 2017/8/9.
 */
public class Card {
    private Integer cid;
    private String cardNum;
    private String name;
    private Double balance;
    private String cardStatus;
    private List<Record> records = new ArrayList<>();

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Card() {
    }

    public Card(String cardNum, String name, Double balance, String cardStatus) {
        this.cardNum = cardNum;
        this.name = name;
        this.balance = balance;
        this.cardStatus = cardStatus;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cid=" + cid +
                ", cardNum='" + cardNum + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", cardStatus='" + cardStatus + '\'' +
                ", records=" + records +
                '}';
    }
}
