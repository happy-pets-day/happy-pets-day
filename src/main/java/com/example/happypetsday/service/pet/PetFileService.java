package com.example.happypetsday.service.pet;

import com.example.happypetsday.dto.PetFileDto;
import com.example.happypetsday.mapper.PetFileMapper;
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
@Transactional
@RequiredArgsConstructor
public class PetFileService {
    private final PetFileMapper petFileMapper;

    @Value("${petFile.dir}")
    private String fileDir;

    public void register(PetFileDto petFileDto){
        if(petFileDto == null) { throw new IllegalArgumentException("파일 정보 누락"); }
        petFileMapper.insert(petFileDto);
    }

    //    파일이 저장되는 하위 경로를 현재 날짜로 설정할 것이기 때문에 현재날짜를 구한다.
    private String getUploadPath(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

    public PetFileDto findFile(Long petNumber){
        return petFileMapper.select(petNumber);
    }

    public void remove(Long petNumber){
        if (petNumber == null) {
            throw new IllegalArgumentException("게시물 번호 누락(file)");
        }

        PetFileDto petFile = findFile(petNumber);
        File target = new File(fileDir, petFile.getPetFileUploadPath() + "/" + petFile.getPetFileUuid() + "_" + petFile.getPetFileName());
        File thumbnail = new File(fileDir, petFile.getPetFileUploadPath() + "/th_" + petFile.getPetFileUuid() + "_" + petFile.getPetFileName());
        if(target.exists()){
            target.delete();
        }
        if(thumbnail.exists()){
            thumbnail.delete();
        }

        petFileMapper.delete(petNumber);
    }

    //    파일 저장 처리
    public PetFileDto saveFile(MultipartFile file) throws IOException {
//        사용자가 올린 파일 이름(확장자를 포함)
        String originName = new String( file.getOriginalFilename().getBytes(), "UTF-8");

//        파일 이름에 붙여줄 uuid 생성(파일이름 중복이 나오지 않게 처리)
        UUID uuid = UUID.randomUUID();

//        uuid와 파일이름을 합쳐준다.
        String sysName = uuid.toString() + "_" + originName;

        File uploadPath = new File(fileDir, getUploadPath());

//        경로가 존재하지 않는다면(폴더가 없다면)
        if(!uploadPath.exists()){
//            경로에 필요한 폴더를 생성한다.
            uploadPath.mkdirs();
        }

//        전체 경로와 파일이름을 연결한다.
        File uploadFile = new File(uploadPath, sysName);

//        매개변수로 받은 파일을 우리가 만든 경로와 이름으로 저장한다.
//        transferTo(경로)
//        MultipartFile객체를 실제로 저장시킨다.
//        저장시킬 경로와 이름을 매개변수로 넘겨주면 된다.
        file.transferTo(uploadFile);

//        썸네일 저장처리
//        이미지 파일인 경우에만 처리하는 조건식
        if(Files.probeContentType(uploadFile.toPath()).startsWith("image")){
            FileOutputStream out = new FileOutputStream(new File(uploadPath, "th_"+sysName));
            Thumbnailator.createThumbnail(file.getInputStream(), out, 300, 200);
            out.close();
        }

//        boardNumber를 제외한 모든 정보를 가진 FileDto를 반환한다.
        PetFileDto petFileDto = new PetFileDto();
        petFileDto.setPetFileUuid(uuid.toString());
        petFileDto.setPetFileName(originName);
        petFileDto.setPetFileUploadPath(getUploadPath());

        return petFileDto;
    }

    public void registerAndSaveFile(MultipartFile file, Long petNumber) throws IOException{
        PetFileDto petFileDto = saveFile(file);
        petFileDto.setPetNumber(petNumber);
        register(petFileDto);
    }

}
