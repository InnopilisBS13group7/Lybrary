package Models;

public class Order {
    private int id;
    private int userId;
    private int itemId;
    private long startTime, finishTime;
    private String status;

    public Order(int id, int userId, int itemId, long startTime, long finishTime, String status) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", status='" + status + '\'' +
                '}';
    }
}
