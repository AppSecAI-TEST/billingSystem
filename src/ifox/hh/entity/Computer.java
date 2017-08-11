package ifox.hh.entity;

/**
 * Created by 41988 on 2017/8/9.
 */
public class Computer {
    private Integer computerId;
    private String computerStatus;
    private String currentCardName;

    public Computer(Integer computerId, String computerStatus, String currentCardName, String computerBrand) {
        this.computerId = computerId;
        this.computerStatus = computerStatus;
        this.currentCardName = currentCardName;
        this.computerBrand = computerBrand;
    }


    public String getComputerBrand() {
        return computerBrand;
    }

    public void setComputerBrand(String computerBrand) {
        this.computerBrand = computerBrand;
    }

    private String computerBrand;

    public String getCurrentCardName() {
        return currentCardName;
    }

    public void setCurrentCardName(String currentCardName) {
        this.currentCardName = currentCardName;
    }

    public Computer() {
    }

    public Computer(String computerBrand) {
        this.computerBrand = computerBrand;
    }

    public Integer getComputerId() {
        return computerId;
    }

    public void setComputerId(Integer computerId) {
        this.computerId = computerId;
    }

    public String getComputerStatus() {
        return computerStatus;
    }

    public void setComputerStatus(String computerStatus) {
        this.computerStatus = computerStatus;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "computerId=" + computerId +
                ", computerStatus='" + computerStatus + '\'' +
                ", currentCardName='" + currentCardName + '\'' +
                ", computerBrand='" + computerBrand + '\'' +
                '}';
    }
}
