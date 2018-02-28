package Models;

public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String status;
    private String password;
    private String cookieId;
    private int fine;

    public User(String id, String email, String password, String name, String surname, String cookieId, String status, int fine) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.status = status;
        this.password = password;
        this.cookieId = cookieId;
        this.fine = fine;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", password='" + password + '\'' +
                ", cookieId='" + cookieId + '\'' +
                ", fine=" + fine +
                '}';
    }
}
