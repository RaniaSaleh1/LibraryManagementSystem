package entity;


import jakarta.persistence.*;


import java.util.List;

@Entity
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String phonenumber;
    private String name;
    private String contactInformation;

    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
    private List<BorrowingRecord> borrowingRecords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    
    public String getName() {
        return name;
    }
    public String getPhonenumber(){
        return phonenumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
    public Patron(Long id){
        this.id=id;
    }
    public Patron(){}

    public void setEmail(String email) {
        this.email=email;
    }

    public void setPhoneNumber(String phonenumber) {
        this.phonenumber=phonenumber;
    }
}
