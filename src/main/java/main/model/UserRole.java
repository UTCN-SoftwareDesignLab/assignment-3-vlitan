//package main.model;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//import static javax.persistence.GenerationType.IDENTITY;
//
//@Entity
//@Table(name = "user_roles")
//public class UserRole {
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "user_role_id", unique = true, nullable = false)
//    private Integer userRoleId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "username", nullable = false)
//    private User user;
//
//    @Column
//   // @Enumerated(EnumType.STRING)
//    @NotNull
//    private String role;
//
//    public Integer getUserRoleId() {
//        return userRoleId;
//    }
//
//    public void setUserRoleId(Integer userRoleId) {
//        this.userRoleId = userRoleId;
//    }
//
//    public User getDoctor() {
//        return user;
//    }
//
//    public void setDoctor(User user) {
//        this.user = user;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//}
