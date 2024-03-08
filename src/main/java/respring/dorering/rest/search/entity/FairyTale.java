package respring.dorering.rest.search.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * FairyTale 엔티티는 데이터베이스의 동화 관련 테이블에 매핑되어있는 클래스입니다.
 * 테이블의 각 컬럼은 클래스의 필드로 표현됩니다.
 */
@Getter
@Entity // 이 클래스가 JPA 엔티티임을 나타냅니다.
@Table(name = "fairytale_info") // 실제 데이터베이스 테이블 이름을 명시합니다.
public class FairyTale {




    @Id // 데이터베이스의 기본 키(primary key)임을 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략을 IDENTITY로 설정합니다.
    private Long id; // 테이블의 기본 키에 해당하는 필드입니다.

    @Column(name = "fairytale_title") // 실제 데이터베이스 컬럼 이름을 명시합니다.
    private String title; // 동화의 제목을 저장하는 필드입니다.

    @Column(name = "fairytale_story") // 실제 데이터베이스 컬럼 이름을 명시합니다.
    private String story; // 동화의 스토리를 저장하는 필드입니다.

    @Column(name = "fairytale_thumb") // 실제 데이터베이스 컬럼 이름을 명시합니다.
    private String thumbnail; // 동화의 썸네일 이미지 경로를 저장하는 필드입니다.

    @Column(name = "summary") // 실제 데이터베이스 컬럼 이름을 명시합니다.
    private String summary; // 동화의 요약을 저장하는 필드입니다.

    // Getters and Setters ...
    // 각 필드에 대한 getter와 setter 메소드를 생성합니다.
    // IDE의 자동 생성 기능을 사용하여 쉽게 추가할 수 있습니다.

    // 기본 생성자입니다.
    // JPA 스펙에서는 엔티티에 기본 생성자가 필요합니다.
    public FairyTale() {
    }

    // 모든 필드를 초기화하는 생성자입니다.
    // 필요에 따라 사용할 수 있지만, JPA에서 엔티티를 생성할 때 기본 생성자를 사용한 후 setter를 통해 값을 설정하는 방식이 일반적입니다.
    public FairyTale(String title, String story, String thumbnail, String summary) {
        this.title = title;
        this.story = story;
        this.thumbnail = thumbnail;
        this.summary = summary;
    }

//    public String getThumbnail() {
//        return thumbnail;
//    }
//
//    public String getSummary() {
//        return summary;
//    }

    // toString, equals, hashCode 메서드는 객체의 문자열 표현 및 비교를 위해 오버라이드 할 수 있습니다.
    // IDE에서 자동 생성할 수 있으며, 필요에 따라 추가하시면 됩니다.
    // 여기서는 예시를 생략합니다.
}