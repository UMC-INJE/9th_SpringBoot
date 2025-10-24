package dto;

import java.time.LocalDate;

public record  Mission(
		Integer missionId,
	    Integer storeId,
	    String storeName,
	    Integer dday,          // D-7 같은 남은 일수
	    String categoryLabel,  // "중식당" (Store에 카테고리 컬럼/연관이 있다면 매핑)
	    String subtitle,       // "10000원 이상의 식사시 500P 적립"
	    LocalDate deadline
		) {

}
