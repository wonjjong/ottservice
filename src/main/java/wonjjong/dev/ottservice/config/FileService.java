package wonjjong.dev.ottservice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wonjjong.dev.ottservice.domain.file.File;
import wonjjong.dev.ottservice.domain.file.FileRepository;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

	private final FileRepository fileRepository;

	public File fileUpload(MultipartFile multipartFile) throws IOException {
		String fileName = multipartFile.getOriginalFilename();
		String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);

		Map<String, Map<String,Object>> filegrpName;
		/*
				1. 지원되는 파일형식인지
				2. 파일명이 정확한지
				3.

		*/

		long size = multipartFile.getSize();
		Resource resource = multipartFile.getResource();
		log.info("originalFilename = {}, size = {}, resource = {}", fileName, size, resource.getURI());


		return null;
	}
}
