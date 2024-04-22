package org.bshg.productmanagement.entity;
import jakarta.persistence.*;
import java.time.*;
import java.util.*;
@Entity
@Table(name="supplier")
@SequenceGenerator(name = "supplier_seq", sequenceName = "supplier_seq", allocationSize = 1)
public class Supplier {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_seq")
private Long id;
private String name;
@OneToMany(mappedBy = "supplier")
private List<Product> products;
public Supplier() {
}
public Supplier(Long id, String label) {
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
public List<Product> getProducts() {
return products;
}
public void setProducts(List<Product> value) {
this.products = value;
}
@Override
public boolean equals(Object object) {
if (object instanceof Supplier supplier) {
return supplier.getId().equals(this.getId());
}
return false;
}
@Override
public int hashCode() {return (id == null) ? 0 : id.hashCode();}
}