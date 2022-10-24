package wonjjong.dev.ottservice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    //외래키로 가지고 있는게 나으려나 ?
    private String thumbnailPath;

    @Column(nullable = false)
    private String videoPath;

    //태그 필드 필요


    @Builder
    public Contents(Long id, String title, String description, String thumbnailPath, String videoPath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnailPath = thumbnailPath;
        this.videoPath = videoPath;
    }

    public void changeThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public void changeVideoPath(String videoPath){
        this.videoPath = videoPath;
    }
}
