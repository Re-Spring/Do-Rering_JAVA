package respring.dorering.rest.story.dto;

public class StoryDetailDTO extends StoryDTO {
    private String userId;

    public StoryDetailDTO(Integer fairytaleCode, String fairytaleTitle, String fairytaleGenre, String fairytaleThumb, String fairytaleSummary, String userId) {
        super(fairytaleCode, fairytaleTitle, fairytaleGenre, fairytaleThumb, fairytaleSummary);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

