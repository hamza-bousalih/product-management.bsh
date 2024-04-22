package org.bshg.productmanagement.ws.converter;
import org.bshg.productmanagement.entity.Supplier;
import org.bshg.productmanagement.ws.dto.SupplierDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class SupplierConverter {
@Autowired private ProductConverter productConverter;
private boolean products = true;
protected void configure(boolean value) {
this.productConverter.setSupplier(value);
}
public final SupplierDto toDto(Supplier item) {
this.configure(false);
var dto = item != null ? convertToDto(item) : null;
this.configure(true);
return dto;
}
public final Supplier toItem(SupplierDto d) {
return d != null ? convertToItem(d) : null;
}
public final List<Supplier> toItem(List<SupplierDto> dtos) {
if (dtos == null) return null;
List<Supplier> list = new ArrayList<>();
dtos.forEach(it -> list.add(toItem(it)));
return list;
}
public final List<SupplierDto> toDto(List<Supplier> items) {
if (items == null) return null;
List<SupplierDto> list = new ArrayList<>();
items.forEach(it -> list.add(toDto(it)));
return list;
}
protected Supplier convertToItem(SupplierDto dto) {
var item = new Supplier();
item.setId(dto.getId());
item.setName(dto.getName());
item.setProducts(productConverter.toItem(dto.getProducts()));
return item;
}
protected SupplierDto convertToDto(Supplier item) {
var dto = new SupplierDto();
dto.setId(item.getId());
dto.setName(item.getName());
dto.setProducts(products? productConverter.toDto(item.getProducts()): null);
return dto;
}
public void setProducts(boolean value) {
this.products = value;
}
public void setProductConverter(ProductConverter value) {
this.productConverter = value;
}
public ProductConverter getProductConverter() {
return productConverter;
}
}