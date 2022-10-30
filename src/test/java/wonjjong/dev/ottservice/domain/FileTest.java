package wonjjong.dev.ottservice.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wonjjong.dev.ottservice.domain.file.File;
import wonjjong.dev.ottservice.domain.file.FileRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class FileTest {

	@Autowired
	private FileRepository fileRepository;

	@Test
	@Transactional
	void 파일저장_테스트() {
		String uuid = UUID.randomUUID().toString(); //36 byte
		File file1 = new File();
		file1.setStoredFileName(UUID.randomUUID().toString());

		File file2 = new File();
		file2.setStoredFileName(UUID.randomUUID().toString());

		fileRepository.save(file1);
		fileRepository.save(file2);

	}
}