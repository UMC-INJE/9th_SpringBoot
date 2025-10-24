package dto;

import java.time.LocalDate;

public record MyMission(
		 Integer missionId,
		 Integer storeId,
		 String text,        // "가게명에서 조건 하세요"
		 String pointLabel,  // "500p"
		 String statusLabel, // "성공" / "진행중"
		 LocalDate deadline) {
}
