package org.bshg.productmanagement.entity;
import org.bshg.productmanagement.zsecurity.entity.AppUser;
import jakarta.persistence.*;
import java.time.*;
import java.util.*;
@Entity
@Table(name="admin")
public class Admin extends AppUser {
private String firstname;
private String lastname;
private String email;
private String phone;
public Admin() {
super();
}
public String getFirstname() {
return firstname;
}
public void setFirstname(String value) {
this.firstname = value;
}
public String getLastname() {
return lastname;
}
public void setLastname(String value) {
this.lastname = value;
}
public String getEmail() {
return email;
}
public void setEmail(String value) {
this.email = value;
}
public String getPhone() {
return phone;
}
public void setPhone(String value) {
this.phone = value;
}
@Override
public boolean equals(Object object) {
if (object instanceof Admin admin) {
return admin.getId().equals(this.getId());
}
return false;
}
@Override
public int hashCode() {return (id == null) ? 0 : id.hashCode();}
}