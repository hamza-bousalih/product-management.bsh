package org.bshg.productmanagement.ws.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
private Long id;
private String name;
private String marque;
private String sn;
private String cab;
private CustomerDto customer;
private SupplierDto supplier;
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
public CustomerDto getCustomer() {
return customer;
}
public void setCustomer(CustomerDto value) {
this.customer = value;
}
public SupplierDto getSupplier() {
return supplier;
}
public void setSupplier(SupplierDto value) {
this.supplier = value;
}
}