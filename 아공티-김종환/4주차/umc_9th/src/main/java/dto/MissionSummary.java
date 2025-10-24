package dto;

public record  MissionSummary(
		 Long availableCount,   // 도전 가능한 미션 개수
		 Integer totalPoint     // 모두 달성 시 총 포인트(없으면 0)
		) {

}
