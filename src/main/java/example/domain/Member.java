package example.domain;


public class Member {

    private Long id;
    public Long getId() {
        return id;
    }
    public Member setId(Long id) {
        this.id = id;
        return null;
    }

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String password;
    private String getPassword(){return password;}
    public void setPassword(String password) {this.password = password;}


    private String email;

    public void setEmail(String email) {this.email = email;}

    public String getEmail() {return email;}

    private String phoneNumber;
    public String getPhoneNumber() {return phoneNumber;}


    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}




}
