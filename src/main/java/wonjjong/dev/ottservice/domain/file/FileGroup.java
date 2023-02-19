package wonjjong.dev.ottservice.domain.file;

import lombok.Data;
import lombok.NoArgsConstructor;
import wonjjong.dev.ottservice.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class FileGroup extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "fileGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    List<File> files = new ArrayList<>();

}
