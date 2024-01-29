package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class YesNoConverter  implements AttributeConverter<String, Boolean> {
    // 엔티티의 데이터를 데이터베이스 컬럼에 저장할 데이터로 변환
    @Override
    public Boolean convertToDatabaseColumn(String attribute) {
        return "Y".equals(attribute);
    }
    // 데이터베이스에서 조회한 컬럼 데이터를 엔티티의 데이터로 변환
    @Override
    public String convertToEntityAttribute(Boolean dbData) {
        return dbData ? "Y" : "N";
    }
}
