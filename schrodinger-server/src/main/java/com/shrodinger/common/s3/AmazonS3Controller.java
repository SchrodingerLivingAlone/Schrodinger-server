package com.shrodinger.common.s3;

import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class AmazonS3Controller {

    private final AwsS3Service awsS3Service;
    @PostMapping("")
    public ApiResponse<List<String>> uploadImage(@RequestPart("file") List<MultipartFile> multipartFile) {
        return ApiResponse.of(SuccessStatus.IMAG_UPLOAD_SUCCESS, awsS3Service.uploadImage(multipartFile));
    }


    @DeleteMapping("")
    public ApiResponse<String> deleteImage(@RequestParam String fileName) {
        awsS3Service.deleteImage(fileName);
        return ApiResponse.onSuccess("null");
    }
}
