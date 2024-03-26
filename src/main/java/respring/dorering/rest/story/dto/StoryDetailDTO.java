package respring.dorering.rest.story.dto;

public class StoryDetailDTO extends StoryDTO {
    private String userId;

    public StoryDetailDTO(Integer fairytaleCode, String fairytaleTitle, String fairytaleGenre, String fairytaleThumb, String fairytaleSummary, String userId, Integer videoFileCode) {
        super(fairytaleCode, fairytaleTitle, fairytaleGenre, fairytaleThumb, fairytaleSummary, videoFileCode);
        this.userId = userId;
    }

    // Getter와 Setter 메서드
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
