package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewReqDto {
	private Integer userId;
    private Integer rating;
    private String content;
}
