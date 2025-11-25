package dto;

import entity.Mission;
import entity.UserMission;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MyMissionResponse(
        Integer missionId,
        Integer storeId,
        String storeName,
        String conditional,
        Integer point,
        LocalDate deadline,
        String status
) {

    public static MyMissionResponse from(UserMission userMission) {
        Mission mission = userMission.getMission();

        return MyMissionResponse.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getStoreId())
                .storeName(mission.getStore().getStoreName())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .status(userMission.getStatus()) 
                .build();
    }
}
