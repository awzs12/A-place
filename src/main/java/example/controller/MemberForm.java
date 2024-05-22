package example.controller;

public class MemberForm {
    private String name;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }


    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}


    private String password;
    public String getPassword(){return password;}
    public void setPassword(String password) {this.password = password;}


    private String email;

    public void setEmail(String email) {this.email = email;}

    public String getEmail() {return email;}

    private String phoneNumber;
    public String getPhoneNumber() {return phoneNumber;}


    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}


}
