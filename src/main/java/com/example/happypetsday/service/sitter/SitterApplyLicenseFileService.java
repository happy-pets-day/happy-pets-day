package com.example.happypetsday.service.sitter;


import com.example.happypetsday.dto.SitterApplyLicenseFile;
import com.example.happypetsday.mapper.SitterApplyLicenseFileMapper;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private String applyDir;

    public void register(SitterApplyLicenseFile file) {
        if (file == null) {
            throw new IllegalArgumentException("파일 정보 누락");
        }
        licenseFile.insert(file);
    }

    public Long findApplyNum(Long userNumber) {
        return licenseFile.selectApplyNumber(userNumber);
    }

    public List<SitterApplyLicenseFile> findList(Long applyNumber) {
        return licenseFile.select(applyNumber);
    }

    //    파일 저장 처리
    public SitterApplyLicenseFile saveFile(MultipartFile file, Long userNumber, Long applyNumber) throws IOException {
        if (file == null||applyNumber==null ) {
            throw new IllegalArgumentException("파일이 누락되었습니다.");
        }

        String originName = file.getOriginalFilename();
        originName = originName.replaceAll("\\s+", "");

        UUID uuid = UUID.randomUUID();
        String sysName = uuid.toString() + "_" + originName;

        File uploadPath = new File(applyDir, getUploadPath());

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        File uploadFile = new File(uploadPath, sysName);
        file.transferTo(uploadFile);

        System.out.println(uploadFile.toPath());

        if (Files.probeContentType(uploadFile.toPath()).startsWith("image")) {
            FileOutputStream out = new FileOutputStream(new File(uploadPath, "th_" + sysName));
            Thumbnailator.createThumbnail(file.getInputStream(), out, 300, 200);
            out.close();
        }

        SitterApplyLicenseFile fileDto = new SitterApplyLicenseFile();
        fileDto.setApplyFileUuid(uuid.toString());
        fileDto.setApplyFileName(originName);
        fileDto.setApplyFileUploadPath(getUploadPath());
        fileDto.setApplyNumber(applyNumber);
        fileDto.setUserNumber(userNumber);

        System.out.println("업로드 패스: " + uploadPath);

        return fileDto;
    }



    /**
     * 파일 리스트를 DB등록 및 저장 처리
     *
     * @param files 여러 파일을 담은 리스트
     * @throws IOException
     */
    public void registerAndSaveFiles(List<MultipartFile> files, Long applyNumber, List<String> applyFileTitle, Long userNumber) throws IOException {
        if (files == null || files.isEmpty()) { // 파일 리스트가 null이거나 비어있을 경우 처리하지 않음
            return;
        }
        System.out.println("파일서비스 ===== " + files.size());

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String title = applyFileTitle.get(i);
            if(title!=null){

                SitterApplyLicenseFile fileDto = saveFile(file, userNumber, applyNumber);

                fileDto.setApplyFileTitle(title);
                fileDto.setApplyNumber(applyNumber);
                register(fileDto);
            }
        }
        System.out.println("파일서비스" + files.size());
        System.out.println("파일서비스" + applyFileTitle);
        return;
    }


    //    파일이 저장되는 하위 경로를 현재 날짜로 설정할 것이기 때문에 현재날짜를 구한다.
    private String getUploadPath() {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

    //    실제 자격증 사진 삭제
    public void removeLicenceFile(Long userNumber){
        if (userNumber == null) {
            throw new IllegalArgumentException("시터 번호 누락(file)");
        }
        List<SitterApplyLicenseFile> fileList = licenseFile.selectByUserNumber(userNumber);

        for(SitterApplyLicenseFile file : fileList){
            File target = new File(applyDir,file.getApplyFileUploadPath()+"/"+file.getApplyFileUuid()+"_"+file.getApplyFileName());
            File thumbnail = new File(applyDir,file.getApplyFileUploadPath()+"/th_"+ file.getApplyFileUuid()+"_"+file.getApplyFileName());

            if(target.exists()){
                target.delete();
            }
            if(thumbnail.exists()){
                thumbnail.delete();
            }
        }
    }

}
