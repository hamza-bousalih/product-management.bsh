package org.bshg.productmanagement.ws.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierDto {
private Long id;
private String name;
private List<ProductDto> products;
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
public List<ProductDto> getProducts() {
return products;
}
public void setProducts(List<ProductDto> value) {
this.products = value;
}
}