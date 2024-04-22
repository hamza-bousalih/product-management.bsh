package org.bshg.productmanagement.ws.converter;
import org.bshg.productmanagement.entity.Product;
import org.bshg.productmanagement.ws.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class ProductConverter {
@Autowired private CustomerConverter customerConverter;
@Autowired private SupplierConverter supplierConverter;
private boolean customer = true;
private boolean supplier = true;
protected void configure(boolean value) {
this.customerConverter.setProducts(value);
this.supplierConverter.setProducts(value);
}
public final ProductDto toDto(Product item) {
this.configure(false);
var dto = item != null ? convertToDto(item) : null;
this.configure(true);
return dto;
}
public final Product toItem(ProductDto d) {
return d != null ? convertToItem(d) : null;
}
public final List<Product> toItem(List<ProductDto> dtos) {
if (dtos == null) return null;
List<Product> list = new ArrayList<>();
dtos.forEach(it -> list.add(toItem(it)));
return list;
}
public final List<ProductDto> toDto(List<Product> items) {
if (items == null) return null;
List<ProductDto> list = new ArrayList<>();
items.forEach(it -> list.add(toDto(it)));
return list;
}
protected Product convertToItem(ProductDto dto) {
var item = new Product();
item.setId(dto.getId());
item.setName(dto.getName());
item.setMarque(dto.getMarque());
item.setSn(dto.getSn());
item.setCab(dto.getCab());
item.setCustomer(customerConverter.toItem(dto.getCustomer()));
item.setSupplier(supplierConverter.toItem(dto.getSupplier()));
return item;
}
protected ProductDto convertToDto(Product item) {
var dto = new ProductDto();
dto.setId(item.getId());
dto.setName(item.getName());
dto.setMarque(item.getMarque());
dto.setSn(item.getSn());
dto.setCab(item.getCab());
dto.setCustomer(customer? customerConverter.toDto(item.getCustomer()): null);
dto.setSupplier(supplier? supplierConverter.toDto(item.getSupplier()): null);
return dto;
}
public void setCustomer(boolean value) {
this.customer = value;
}
public void setSupplier(boolean value) {
this.supplier = value;
}
public void setCustomerConverter(CustomerConverter value) {
this.customerConverter = value;
}
public CustomerConverter getCustomerConverter() {
return customerConverter;
}
public void setSupplierConverter(SupplierConverter value) {
this.supplierConverter = value;
}
public SupplierConverter getSupplierConverter() {
return supplierConverter;
}
}