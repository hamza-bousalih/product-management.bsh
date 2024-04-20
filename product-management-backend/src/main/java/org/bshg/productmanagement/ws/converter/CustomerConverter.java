package org.bshg.productmanagement.ws.converter;

import org.bshg.productmanagement.bean.Customer;
import org.bshg.productmanagement.ws.dto.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter {
    protected void configure(boolean value) {
    }

    public final CustomerDto toDto(Customer item) {
        this.configure(false);
        var dto = item != null ? convertToDto(item) : null;
        this.configure(true);
        return dto;
    }

    public final Customer toItem(CustomerDto d) {
        return d != null ? convertToItem(d) : null;
    }

    public final List<Customer> toItem(List<CustomerDto> dtos) {
        if (dtos == null) return null;
        List<Customer> list = new ArrayList<>();
        dtos.forEach(it -> list.add(toItem(it)));
        return list;
    }

    public final List<CustomerDto> toDto(List<Customer> items) {
        if (items == null) return null;
        List<CustomerDto> list = new ArrayList<>();
        items.forEach(it -> list.add(toDto(it)));
        return list;
    }

    protected Customer convertToItem(CustomerDto dto) {
        var item = new Customer();
        item.setId(dto.getId());
        item.setFirstname(dto.getFirstname());
        item.setLastname(dto.getLastname());
        item.setEmail(dto.getEmail());
        item.setPhone(dto.getPhone());
        return item;
    }

    protected CustomerDto convertToDto(Customer item) {
        var dto = new CustomerDto();
        dto.setId(item.getId());
        dto.setFirstname(item.getFirstname());
        dto.setLastname(item.getLastname());
        dto.setEmail(item.getEmail());
        dto.setPhone(item.getPhone());
        return dto;
    }
}