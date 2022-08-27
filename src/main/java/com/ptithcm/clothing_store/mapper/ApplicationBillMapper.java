package com.ptithcm.clothing_store.mapper;

import com.ptithcm.clothing_store.model.dto.*;
import com.ptithcm.clothing_store.model.dto.bill.*;
import com.ptithcm.clothing_store.model.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicationBillMapper {
    private ApplicationBillMapper(){
    }
    public BillDto billToBillDto(Bill entity){
        BillDto dto = new BillDto();
        ReceiverDto receiverDto = new ReceiverDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setIsPayment(entity.getIsPayment());
        dto.setNote(entity.getNote());
        dto.setTotal(entity.getTotal());
        dto.setVersion(entity.getVersion());
        dto.setIdCustomer(entity.getCustomer().getId());
        dto.setState(entity.getOrders().getState());
        receiverDto.setAddress(entity.getAddress());
        receiverDto.setFirstName(entity.getFirstName());
        receiverDto.setLastName(entity.getLastName());
        receiverDto.setMail(entity.getMail());
        receiverDto.setPhoneNumber(entity.getPhoneNumber());
        dto.setReceiver(receiverDto);
        dto.setBillProductDetails(
                this.billProductDetailsToBillProductDetailsDto(
                        entity.getBillProductDetails().
                                stream().
                                collect(Collectors.toList())));
        return dto;
    }
    public Bill billDtoToBill(BillUpdateDto dto){
        Bill entity = new Bill();
        entity.setId(dto.getId());
        entity.setTotal(dto.getTotal());
        entity.setDate(dto.getDate());
        entity.setIsPayment(dto.getIsPayment());
        entity.setNote(dto.getNote());
        entity.setVersion(dto.getVersion());
        entity.setFirstName(dto.getReceiver().getFirstName());
        entity.setLastName(dto.getReceiver().getLastName());
        entity.setMail(dto.getReceiver().getMail());
        entity.setPhoneNumber(dto.getReceiver().getPhoneNumber());
        entity.setAddress(dto.getReceiver().getAddress());
        return entity;
    }
    public List<BillProductDetailDto> billProductDetailsToBillProductDetailsDto(List<BillProductDetail> entities){
        List<BillProductDetailDto> dtos = new ArrayList<>();
        entities.forEach((p)->{
            dtos.add(this.billProductDetailToBillProductDetailDto(p));
        });
        return dtos;
    }

    public BillProductDetailDto billProductDetailToBillProductDetailDto(BillProductDetail entity){
        BillProductDetailDto dto = new BillProductDetailDto();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setVersion(entity.getVersion());
        dto.setProductColorSizeDto(billProductColorSizeToBillPrProductColorSizeDto(entity.getProductColorSize()));
        return dto;
    }
    public BillProductColorSizeDto billProductColorSizeToBillPrProductColorSizeDto(ProductColorSize entity){
        BillProductColorSizeDto dto = new BillProductColorSizeDto();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setSize(new ApplicationProductMapper().sizeToSizeDto(entity.getSize()));
        dto.setColor(productColorBillToProductColorBillDto(entity.getProductColor()));
        return dto;
    }
    public BillColorProductDto productColorBillToProductColorBillDto(ProductColor entity){
        BillColorProductDto dto = new BillColorProductDto();
        dto.setId(entity.getId());
        dto.setImg(entity.getImage());
        dto.setVersion(entity.getVersion());
        dto.setColor(new ApplicationProductMapper().colorToColorDto(entity.getColor()));
        dto.setProduct(productBillToProductBillDto(entity.getProduct()));
        return dto;
    }
    public BillProductDto productBillToProductBillDto(Product entity){
        BillProductDto dto = new BillProductDto();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setDescription(entity.getDescription());
        dto.setBrand(new ApplicationProductMapper().brandToBrandDto(entity.getBrand()));
        dto.setLabel(new ApplicationProductMapper().labelToLabelDto(entity.getLabel()));
        dto.setMaterial(new ApplicationProductMapper().materialToMaterialDto(entity.getMaterial()));
        dto.setGender(new ApplicationProductMapper().genderToGenderDto(entity.getGender()));
        dto.setImg(entity.getImage());
        return dto;
    }
    public BillProductDetail billProductDetailDtoToBillProductDetail(BillProductDetailUpdateDto dto){
        BillProductDetail entity = new BillProductDetail();
        entity.setQuantity(dto.getQuantity());
        entity.setUnitPrice(dto.getUnitPrice());
        return entity;
    }
}
