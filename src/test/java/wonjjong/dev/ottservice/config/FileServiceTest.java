package wonjjong.dev.ottservice.config;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileServiceTest {

	private MockMultipartFile mockMultipartFile;
	
	@BeforeAll
	void before() throws IOException {
		String filename = "test";
		String contentType = "png";
		String filePath = "src/test/resources/test.png";
		mockMultipartFile = getMockMultipartFile(filename, contentType, filePath);
	}

	@Test
	@DisplayName("파일명")
	void 파일명() {
		String getFileName = mockMultipartFile.getOriginalFilename();
		assertThat(getFileName).isEqualTo("test.png");
	}

	@Test
	@DisplayName("확장자명")
	void 확장자명() {
		String fileName= mockMultipartFile.getOriginalFilename();
		String extensionName = fileName.substring(mockMultipartFile.getOriginalFilename().lastIndexOf(".") + 1);
		assertThat(extensionName).isEqualTo("png");
	}

	@Test
	@DisplayName("파일크기")
	void 파일크기_가져오기() {
		long size = mockMultipartFile.getSize(); // Byte
		Resource resource = mockMultipartFile.getResource();
		System.out.println(resource);
	}


	private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(new File(path));
		return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
	}

}