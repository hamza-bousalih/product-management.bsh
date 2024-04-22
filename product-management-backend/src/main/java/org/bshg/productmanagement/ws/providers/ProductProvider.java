package org.bshg.productmanagement.ws.providers;
import org.bshg.productmanagement.entity.Product;
import org.bshg.productmanagement.services.facade.ProductService;
import org.bshg.productmanagement.ws.converter.ProductConverter;
import org.bshg.productmanagement.ws.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;
@RestController
@RequestMapping("/api/product")
public class ProductProvider {
@Autowired private ProductService service;
@Autowired private ProductConverter converter;
@GetMapping("/id/{id}")
public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
var result = service.findById(id);
var resultDto = converter.toDto(result);
return ResponseEntity.ok(resultDto);
}
@GetMapping
public ResponseEntity<List<ProductDto>> findAll() {
var result = service.findAll();
var resultDto = converter.toDto(result);
return ResponseEntity.ok(resultDto);
}
@GetMapping("/optimized")
public ResponseEntity<List<ProductDto>> findAllOptimized() {
var result = service.findAllOptimized();
var resultDto = converter.toDto(result);
return ResponseEntity.ok(resultDto);
}
@PostMapping
public ResponseEntity<ProductDto> save(@RequestBody ProductDto dto) {
if (dto == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
var item = converter.toItem(dto);
var result = service.create(item);
var resultDto = converter.toDto(result);
return ResponseEntity.ok(resultDto);
}
@PostMapping("/all")
public ResponseEntity<List<ProductDto>> save(@RequestBody List<ProductDto> dtos) {
if (dtos == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
var item = converter.toItem(dtos);
var result = service.create(item);
var resultDto = converter.toDto(result);
return ResponseEntity.ok(resultDto);
}
@PutMapping()
public ResponseEntity<ProductDto> update(@RequestBody ProductDto dto) {
if (dto == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
var item = converter.toItem(dto);
var result = service.update(item);
var resultDto = converter.toDto(result);
return ResponseEntity.ok(resultDto);
}
@PutMapping("/all")
public ResponseEntity<List<ProductDto>> update(@RequestBody List<ProductDto> dtos) {
if (dtos == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
var item = converter.toItem(dtos);
var result = service.update(item);
var resultDto = converter.toDto(result);
return ResponseEntity.ok(resultDto);
}
@DeleteMapping
public ResponseEntity<ProductDto> delete(@RequestBody ProductDto dto) {
if (dto == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
var item = converter.toItem(dto);
service.delete(item);
return ResponseEntity.ok(dto);
}
@DeleteMapping("/all")
public ResponseEntity<List<ProductDto>> delete(@RequestBody List<ProductDto> dtos) {
if (dtos == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
var item = converter.toItem(dtos);
service.delete(item);
return ResponseEntity.ok(dtos);
}
@DeleteMapping("/id/{id}")
public ResponseEntity<Long> deleteById(@PathVariable Long id) {
service.deleteById(id);
return ResponseEntity.ok(id);
}
@DeleteMapping("/ids")
public ResponseEntity<List<Long>> deleteByIdIn(@RequestParam("id") List<Long> ids) {
service.deleteByIdIn(ids);
return ResponseEntity.ok(ids);
}
@DeleteMapping("/customer/id/{id}")
public ResponseEntity<Long> deleteByCustomerId(@PathVariable Long id){
service.deleteByCustomerId(id);
return ResponseEntity.ok(id);
}
@GetMapping("/customer/id/{id}")
public ResponseEntity<List<ProductDto>> findByCustomerId(@PathVariable Long id){
var result = service.findByCustomerId(id);
var resultDto = converter.toDto(result);
return ResponseEntity.ok(resultDto);
}
@DeleteMapping("/supplier/id/{id}")
public ResponseEntity<Long> deleteBySupplierId(@PathVariable Long id){
service.deleteBySupplierId(id);
return ResponseEntity.ok(id);
}
@GetMapping("/supplier/id/{id}")
public ResponseEntity<List<ProductDto>> findBySupplierId(@PathVariable Long id){
var result = service.findBySupplierId(id);
var resultDto = converter.toDto(result);
return ResponseEntity.ok(resultDto);
}
}