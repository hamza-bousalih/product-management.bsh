package org.bshg.productmanagement.entity;
import jakarta.persistence.*;
import java.time.*;
import java.util.*;
@Entity
@Table(name="customer")
@SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
private Long id;
private String firstname;
private String lastname;
private String email;
private String phone;
@OneToMany(mappedBy = "customer")
private List<Product> products;
public Customer() {
}
public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
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
public List<Product> getProducts() {
return products;
}
public void setProducts(List<Product> value) {
this.products = value;
}
@Override
public boolean equals(Object object) {
if (object instanceof Customer customer) {
return customer.getId().equals(this.getId());
}
return false;
}
@Override
public int hashCode() {return (id == null) ? 0 : id.hashCode();}
}