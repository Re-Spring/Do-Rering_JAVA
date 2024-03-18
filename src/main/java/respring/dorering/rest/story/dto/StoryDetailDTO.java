package respring.dorering.rest.story.dto;

public class StoryDetailDTO extends StoryDTO {
    private String userId; // 사용자 ID

    public StoryDetailDTO(Integer fairytaleCode, String fairytaleTitle, String fairytaleGenre, String fairytaleThumb, String fairytaleSummary, String userId) {
        super(fairytaleCode, fairytaleTitle, fairytaleGenre, fairytaleThumb, fairytaleSummary); // 부모 클래스의 생성자를 호출합니다.
        this.userId = userId; // 사용자 ID를 초기화합니다.
    }

    // Getter와 Setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

