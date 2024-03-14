package respring.dorering.rest.search.dto;

/**
 * SearchDTO는 클라이언트에게 전송될 검색 결과 데이터를 담는 객체입니다.
 * 이 객체는 검색 결과로 보여줄 정보만을 포함해야 하며,
 * 클라이언트에게 불필요한 정보는 제외해야 합니다.
 */
public class SearchDTO {

    // 동화의 제목을 저장하는 필드입니다.
    private String title;

    // 동화의 썸네일 이미지 경로를 저장하는 필드입니다.
    private String thumbnail;

    // 동화의 요약을 저장하는 필드입니다.
    // 일부 데이터에는 null이 허용되므로, 해당 필드는 nullable 입니다.
    private String summary;

    private String fairytaleCode;

    // 기본 생성자입니다. JavaBean 규약에 따라 기본 생성자를 제공해야 합니다.
    public SearchDTO() {
    }

    // 모든 필드를 초기화하는 생성자입니다.
    public SearchDTO(String title, String thumbnail, String summary, String fairytaleCode) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.summary = summary;
        this.fairytaleCode = fairytaleCode;
    }

    // title 필드에 대한 getter 메서드입니다.
    public String getTitle() {
        return title;
    }

    // title 필드에 대한 setter 메서드입니다.
    public void setTitle(String title) {
        this.title = title;
    }

    // thumbnail 필드에 대한 getter 메서드입니다.
    public String getThumbnail() {
        return thumbnail;
    }

    // thumbnail 필드에 대한 setter 메서드입니다.
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    // summary 필드에 대한 getter 메서드입니다.
    public String getSummary() {
        return summary;
    }

    // summary 필드에 대한 setter 메서드입니다.
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFairytaleCode(){return fairytaleCode;}

    public void setFairytaleCode(String fairytaleCode){this.fairytaleCode = fairytaleCode;}
    // toString 메서드는 객체의 문자열 표현을 제공합니다.
    @Override
    public String toString() {
        return "SearchDTO{" +
                "title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", summary='" + summary + '\'' +
                ", fairytaleCode='" + fairytaleCode + '\'' + // toString 메서드에 fairytaleCode 추가
                '}';
    }

    // equals와 hashCode 메서드는 객체 비교와 해시 기반의 자료구조에서 객체를 사용할 때 중요합니다.
    // IDE에서 자동 생성할 수 있으며, 필요에 따라 추가하시면 됩니다.
    // 여기서는 예시를 생략합니다.
}