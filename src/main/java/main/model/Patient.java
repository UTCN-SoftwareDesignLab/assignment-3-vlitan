package main.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    @NonNull
    private String name;

    @Column(unique = true)
    @NonNull
    @Pattern(regexp = "[A-Z]{2}[0-9]{4}", message = "Identity card number does not match the pattern")
    private String identityCardNumber;

    @Column(unique = true)
    @NonNull
    @Pattern(regexp = "[0-9]{10}", message = "Illegal characters or wrong length of personal numerical code")
    private String personalNumericalCode;

    @Column
    private String address;

    @Column
    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "patient")
    private List<Consultation> consultations = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getPersonalNumericalCode() {
        return personalNumericalCode;
    }

    public void setPersonalNumericalCode(String personalNumericalCode) {
        this.personalNumericalCode = personalNumericalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identityCardNumber='" + identityCardNumber + '\'' +
                ", personalNumericalCode='" + personalNumericalCode + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
