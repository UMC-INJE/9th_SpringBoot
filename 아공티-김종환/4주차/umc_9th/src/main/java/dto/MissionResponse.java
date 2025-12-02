package dto;

import entity.Mission;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MissionResponse(
        Integer missionId,
        Integer storeId,
        String storeName,
        String conditional,
        Integer point,
        LocalDate deadline,
        Integer createdAt
) {

    public static MissionResponse from(Mission mission) {
        return MissionResponse.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getStoreId())
                .storeName(mission.getStore().getStoreName())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .createdAt(mission.getCreatedAt())
                .build();
    }
}
