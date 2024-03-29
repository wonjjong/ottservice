package wonjjong.dev.ottservice.domain.file;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wonjjong.dev.ottservice.domain.BaseTimeEntity;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class File extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String extension;

	@Column
	private String originFileName;

	@Column
	private String storedFileName;

	@Column
	private int fileSize;

	@Column
	private String storedFilePath;


}
