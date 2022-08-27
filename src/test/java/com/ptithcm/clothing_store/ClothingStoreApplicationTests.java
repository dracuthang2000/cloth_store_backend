package com.ptithcm.clothing_store;

import com.ptithcm.clothing_store.model.dto.ProductDto;
import com.ptithcm.clothing_store.util.TagUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClothingStoreApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(TagUtil.removeAccent("áo thun cổ trụ tối giản"));
	}
	@Test
	void saveProduct(){
		ProductDto dto = new ProductDto();
		dto.setProductName("Áo thun cổ dài tối gián màu đen");
	}

}
