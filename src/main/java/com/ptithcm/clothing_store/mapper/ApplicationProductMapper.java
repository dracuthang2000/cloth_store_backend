package com.ptithcm.clothing_store.mapper;

import com.google.gson.Gson;
import com.ptithcm.clothing_store.model.dto.*;
import com.ptithcm.clothing_store.model.entity.*;
import com.ptithcm.clothing_store.util.DiscountUtil;
import org.hibernate.mapping.Collection;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicationProductMapper {
    public ApplicationProductMapper() {
    }

    public ColorDto ColorToColorDto(Color entity){
        ColorDto dto = new ColorDto();
        dto.setColor(entity.getColor());
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        return dto;
    }
    public ColorProductDto productColorToProductColorDto(ProductColor entity){
        ColorProductDto dto = new ColorProductDto();
        dto.setId(entity.getId());
        dto.setImg(entity.getImage());
        dto.setVersion(entity.getVersion());
        dto.setColor(this.ColorToColorDto(entity.getColor()));
        dto.setProductColorSizesDto(this.productColorSizeListToProductColorSizeDtoList(entity.getProductColorSize().stream().collect(Collectors.toList())));
        return dto;
    }
    public BrandDto brandToBrandDto(Brand entity){
        BrandDto dto = new BrandDto();
        dto.setId(entity.getId());
        dto.setBrand(entity.getBrandName());
        dto.setVersion(entity.getVersion());
        return dto;
    }
    public LabelDto labelToLabelDto(Label entity){
        LabelDto dto = new LabelDto();
        dto.setLabel(entity.getLabelName());
        dto.setTag(entity.getTag());
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        return dto;
    }
    public GenderDto genderToGenderDto(Gender entity){
        GenderDto dto = new GenderDto();
        dto.setGender(entity.getGenderName());
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        return dto;
    }
    public SizeDto sizeToSizeDto(Size entity){
        SizeDto dto = new SizeDto();
        dto.setId(entity.getId());
        dto.setSize(entity.getSize());
        dto.setVersion(entity.getVersion());
        return dto;
    }
    public ProductColorSizeDto productColorSizeToProductColorSizeDto(ProductColorSize entity){
        ProductColorSizeDto dto = new ProductColorSizeDto();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setSize(this.sizeToSizeDto(entity.getSize()));
        return dto;
    }
    public StuffDto stuffToStuffDto(Stuff entity){
        StuffDto dto = new StuffDto();
        dto.setId(entity.getId());
        dto.setStuff(entity.getStuffName());
        dto.setVersion(entity.getVersion());
        return dto;
    }
    private PriceDto priceToPriceDto(Price entity){
        PriceDto dto = new PriceDto();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setStartDate(entity.getStartDate());
        dto.setVersion(entity.getVersion());
        return dto;
    }
    public DiscountDto discountToDiscountDto(Discount entity){
        DiscountDto dto = new DiscountDto();
        dto.setId(entity.getId());
        dto.setPercent(entity.getPercent());
        dto.setVersion(entity.getVersion());
        return dto;
    }
    public ProductDiscountDto productDiscountToProductDiscountDto(ProductDiscount entity){
        ProductDiscountDto dto = new ProductDiscountDto();
        dto.setDiscount(this.discountToDiscountDto(entity.getDiscount()));
        dto.setStartDate(entity.getId().getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setVersion(entity.getVersion());
        return dto;
    }
    public List<ProductDiscountDto> productDiscountsToProductDiscountDtos(List<ProductDiscount> entities){
        List<ProductDiscountDto> dtos = new ArrayList<>();
        entities.forEach(e->{
            dtos.add(this.productDiscountToProductDiscountDto(e));
        });
        return dtos;
    }
    public List<ProductColorSizeDto> productColorSizeListToProductColorSizeDtoList(List<ProductColorSize> entities){
        List<ProductColorSizeDto> dtos = new ArrayList<>();
        entities.forEach(e->{
            dtos.add(this.productColorSizeToProductColorSizeDto(e));
        });
        return dtos;
    }
    public List<ColorProductDto> productColorsToProductColorsDto(List<ProductColor> entities){
        List<ColorProductDto> dtos = new ArrayList<>();
        entities.forEach(e->{
            dtos.add(this.productColorToProductColorDto(e));
        });
        return dtos;
    }
    public List<PriceDto> pricesToPricesDto(List<Price> entities){
        List<PriceDto> dtos = new ArrayList<>();
        entities.forEach(e->{
            dtos.add(this.priceToPriceDto(e));
        });
        return dtos;
    }
    public ProductDto productToProductDto(Product entity){
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setDescription(entity.getDescription());
        dto.setColor(this.productColorsToProductColorsDto(entity.getProductColors().stream().collect(Collectors.toList())));
        dto.setBrand(this.brandToBrandDto(entity.getBrand()));
        dto.setLabel(this.labelToLabelDto(entity.getLabel()));
        dto.setStuff(this.stuffToStuffDto(entity.getStuff()));
        dto.setGender(this.genderToGenderDto(entity.getGender()));
        dto.setPriceLog(this.pricesToPricesDto(entity.getPrices().stream().collect(Collectors.toList())));
        dto.setVersion(entity.getVersion());
        dto.setIsActive(entity.getIsActive());
        dto.setPrice(entity.getPrice());
        dto.setImg(entity.getImage());
        dto.setIsNew(entity.getIsNew());
        dto.setTag(entity.getTag());
        dto.setDiscounts(this.productDiscountsToProductDiscountDtos(entity.getProductDiscounts().stream().collect(Collectors.toList())));
        if(dto.getDiscounts().size()!=0) {
            dto.getDiscounts().forEach(d->{
                if(DiscountUtil.discountNow(d)){
                    dto.setDiscount(d.getDiscount());
                }
            });
        }
        return dto;
    }
}
