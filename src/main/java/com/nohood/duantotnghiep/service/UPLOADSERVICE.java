package com.nohood.duantotnghiep.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UPLOADSERVICE {
    File save(MultipartFile file, String folder);
    void saveproduct(MultipartFile[] files, String namefolder);
    void updateproduct(MultipartFile[] files, String namefolder);
}
