package Models;

public class Document {
    private String id;
    private String title;
    private String author;
    private String status;
    private int amount;
    private String description;
    private String teg;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeg() {
        return teg;
    }

    public void setTeg(String teg) {
        this.teg = teg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Document(String id, String title, String author, String status, int amount, String description, String teg, String type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
        this.amount = amount;
        this.description = description;
        this.teg = teg;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", teg='" + teg + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
