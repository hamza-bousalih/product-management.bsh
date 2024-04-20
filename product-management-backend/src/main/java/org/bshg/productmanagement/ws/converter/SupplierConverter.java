package org.bshg.productmanagement.ws.converter;

import org.bshg.productmanagement.bean.Supplier;
import org.bshg.productmanagement.ws.dto.SupplierDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierConverter {
    protected void configure(boolean value) {
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
        return item;
    }

    protected SupplierDto convertToDto(Supplier item) {
        var dto = new SupplierDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        return dto;
    }
}