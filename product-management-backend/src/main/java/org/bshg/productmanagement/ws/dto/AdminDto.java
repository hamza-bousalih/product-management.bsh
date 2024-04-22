package org.bshg.productmanagement.ws.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.bshg.productmanagement.zsecurity.ws.dto.AppUserDto;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminDto extends AppUserDto {
private Long id;
private String firstname;
private String lastname;
private String email;
private String phone;
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
}