package com.ptithcm.clothing_store.mapper;

import com.ptithcm.clothing_store.model.dto.*;
import com.ptithcm.clothing_store.model.entity.*;
import com.ptithcm.clothing_store.util.DiscountUtil;
import com.ptithcm.clothing_store.util.TagUtil;
import org.springframework.stereotype.Component;

import java.util.*;
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
    public Color ColorDtoToColor(ColorDto dto){
        Color entity = new Color();
        entity.setColor(dto.getColor());
        entity.setId(dto.getId());
        entity.setVersion(dto.getVersion());
        return entity;
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
    public ProductColor productColorDtoToProductColor(ColorProductDto dto){
        ProductColor entity = new ProductColor();
        entity.setId(dto.getId());
        entity.setImage(dto.getImg());
        entity.setVersion(dto.getVersion());
        entity.setColor(this.ColorDtoToColor(dto.getColor()));
        if(!Objects.isNull(dto.getProductColorSizesDto())) {
            entity.setProductColorSize(
                    this.productColorSizeDtoListToProductColorSizeList(
                            dto.getProductColorSizesDto().stream().collect(Collectors.toSet())));
        }
        return entity;
    }
    public BrandDto brandToBrandDto(Brand entity){
        BrandDto dto = new BrandDto();
        dto.setId(entity.getId());
        dto.setBrand(entity.getBrandName());
        dto.setVersion(entity.getVersion());
        dto.setImage(entity.getImage());
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
    public Size sizeDtoToSize(SizeDto dto){
        Size entity = new Size();
        entity.setId(dto.getId());
        entity.setSize(dto.getSize());
        entity.setVersion(dto.getVersion());
        return entity;
    }
    public ProductColorSizeDto productColorSizeToProductColorSizeDto(ProductColorSize entity){
        ProductColorSizeDto dto = new ProductColorSizeDto();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setSize(this.sizeToSizeDto(entity.getSize()));
        return dto;
    }
    public MaterialDto materialToMaterialDto(Material entity){
        MaterialDto dto = new MaterialDto();
        dto.setId(entity.getId());
        dto.setMaterialName(entity.getMaterialName());
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
    public Set<ProductColorSize> productColorSizeDtoListToProductColorSizeList(Set<ProductColorSizeDto> dtos){
        Set<ProductColorSize> entities = new HashSet<>();
        dtos.forEach(e->{
            entities.add(this.productColorSizeDtoToProductColorSize(e));
        });
        return entities;
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
    public ProductColorSize productColorSizeDtoToProductColorSize(ProductColorSizeDto dto){
        ProductColorSize entity = new ProductColorSize();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        entity.setSize(this.sizeDtoToSize(dto.getSize()));
        entity.setProductColor(this.productColorDtoToProductColor(dto.getColor()));
        return entity;
    }
    public ProductDto productToProductDto(Product entity){
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setDescription(entity.getDescription());
        dto.setColor(this.productColorsToProductColorsDto(entity.getProductColors().stream().collect(Collectors.toList())));
        dto.setBrand(this.brandToBrandDto(entity.getBrand()));
        dto.setLabel(this.labelToLabelDto(entity.getLabel()));
        dto.setMaterial(this.materialToMaterialDto(entity.getMaterial()));
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

    public Brand brandDtoToBrand (BrandDto dto){
        Brand entity = new Brand();
        entity.setId(dto.getId());
        entity.setVersion(dto.getVersion());
        entity.setBrandName(dto.getBrand());
        entity.setImage(dto.getImage());
        return entity;
    }

    public Material materialDtoToMaterial(MaterialDto dto){
        Material entity = new Material();
        entity.setMaterialName(dto.getMaterialName());
        entity.setId(dto.getId());
        entity.setVersion(dto.getVersion());
        return entity;
    }

    public Label labelDtoToLabel(LabelDto dto){
        Label entity = new Label();
        entity.setLabelName(dto.getLabel());
        entity.setTag(dto.getTag());
        entity.setId(dto.getId());
        entity.setVersion(dto.getVersion());
        return entity;
    }

    public Gender genderDtoToGender(GenderDto dto){
        Gender entity = new Gender();
        entity.setId(dto.getId());
        entity.setGenderName(dto.getGender());
        entity.setVersion(dto.getVersion());
        return entity;
    }

    public Color colorDtoColor(ColorDto dto){
        Color entity = new Color();
        entity.setColor(dto.getColor());
        entity.setId(dto.getId());
        entity.setVersion(dto.getVersion());
        return entity;
    }

    public Product mapperUpdateProduct(ProductDto dto){
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setBrand(this.brandDtoToBrand(dto.getBrand()));
        entity.setMaterial(this.materialDtoToMaterial(dto.getMaterial()));
        entity.setLabel(this.labelDtoToLabel(dto.getLabel()));
        entity.setGender(this.genderDtoToGender(dto.getGender()));
        entity.setVersion(dto.getVersion());
        entity.setTag(TagUtil.convertTag(TagUtil.removeAccent(dto.getProductName())));
        entity.setIsActive(dto.getIsActive());
        entity.setIsNew(dto.getIsNew());
        entity.setImage(dto.getImg());
        entity.setDescription(dto.getDescription());
        entity.setProductName(dto.getProductName());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}
