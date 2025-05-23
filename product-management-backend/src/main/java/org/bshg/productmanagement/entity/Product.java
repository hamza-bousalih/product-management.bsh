package org.bshg.productmanagement.entity;
import jakarta.persistence.*;
import java.time.*;
import java.util.*;
@Entity
@Table(name="product")
@SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
private Long id;
private String name;
private String marque;
private String sn;
private String cab;
@ManyToOne(fetch = FetchType.LAZY)
private Customer customer;
@ManyToOne(fetch = FetchType.LAZY)
private Supplier supplier;
public Product() {
}
public Product(Long id, String label) {
// constructor to get optimized fields
this.id = id;
this.name = label;
}
public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
}
public String getName() {
return name;
}
public void setName(String value) {
this.name = value;
}
public String getMarque() {
return marque;
}
public void setMarque(String value) {
this.marque = value;
}
public String getSn() {
return sn;
}
public void setSn(String value) {
this.sn = value;
}
public String getCab() {
return cab;
}
public void setCab(String value) {
this.cab = value;
}
public Customer getCustomer() {
return customer;
}
public void setCustomer(Customer value) {
this.customer = value;
}
public Supplier getSupplier() {
return supplier;
}
public void setSupplier(Supplier value) {
this.supplier = value;
}
@Override
public boolean equals(Object object) {
if (object instanceof Product product) {
return product.getId().equals(this.getId());
}
return false;
}
@Override
public int hashCode() {return (id == null) ? 0 : id.hashCode();}
}