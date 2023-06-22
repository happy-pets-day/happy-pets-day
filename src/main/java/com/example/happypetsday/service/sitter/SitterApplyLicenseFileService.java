package com.example.happypetsday.service.sitter;


import com.example.happypetsday.dto.SitterApplyLicenseFile;
import com.example.happypetsday.mapper.SitterApplyLicenseFileMapper;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SitterApplyLicenseFileService {
    private final SitterApplyLicenseFileMapper licenseFile;

    @Value("${sitterFile.dir}")
    private String fileDir;

    public void register(SitterApplyLicenseFile file){
        if(file == null) { throw new IllegalArgumentException("파일 정보 누락"); }
        licenseFile.insert(file);
    }

    public void remove(Long applyNumber){
        if(applyNumber == null){
            throw new IllegalArgumentException("지원 번호 누락");
        }

        List<SitterApplyLicenseFile> fileList = findList(applyNumber);

        for(SitterApplyLicenseFile file : fileList){
            File target = new File(fileDir, file.getApplyFileUploadPath() + "/" + file.getApplyFileUuid() + "_" + file.getApplyFileName());
            File thumbnail = new File(fileDir, file.getApplyFileUploadPath() + "/th_" + file.getApplyFileUuid() + "_" + file.getApplyFileName());

            if(target.exists()){
                target.delete();
            }
            if(thumbnail.exists()){
                thumbnail.delete();
            }
        }
        licenseFile.delete(applyNumber);
    }

    public List<SitterApplyLicenseFile> findList(Long applyNumber){
        return licenseFile.select(applyNumber);
    }

    public SitterApplyLicenseFile saveFile(MultipartFile file) throws IOException {
        String originName = file.getOriginalFilename();
        originName = originName.replaceAll("\\s+", "");

        UUID uuid = UUID.randomUUID();

        String sysName = uuid.toString() + "_" + originName;

        File uploadPath = new File(fileDir, getUploadPath());

        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        File uploadFile = new File(uploadPath, sysName);

        file.transferTo(uploadFile);

        if(Files.probeContentType(uploadFile.toPath()).startsWith("image")){
            FileOutputStream out = new FileOutputStream(new File(uploadPath, "th_"+sysName));
            Thumbnailator.createThumbnail(file.getInputStream(), out, 300, 200);
            out.close();
        }

        SitterApplyLicenseFile fileDto = new SitterApplyLicenseFile();
        fileDto.setApplyFileUuid(uuid.toString());
        fileDto.setApplyFileName(originName);
        fileDto.setApplyFileUploadPath(getUploadPath());

        return fileDto;
    }

    /**
     * 파일 리스트를 DB등록 및 저장 처리
     *
     * @param files 여러 파일을 담은 리스트
     * @param applyNumber 파일이 속하는 게시글 번호
     * @throws IOException
     */
    public void registerAndSaveFiles(List<MultipartFile> files, Long applyNumber, List<String> applyFileTitle, Long userNumber) throws IOException {
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String title = applyFileTitle.get(i);
            SitterApplyLicenseFile fileDto = saveFile(file);

            fileDto.setApplyNumber(applyNumber);
            fileDto.setUserNumber(userNumber);
            fileDto.setApplyFileTitle(title);

            register(fileDto);
        }
    }

    private String getUploadPath(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

    public List<SitterApplyLicenseFile> findOldList(){
        return licenseFile.selectOldList();
    }
}
