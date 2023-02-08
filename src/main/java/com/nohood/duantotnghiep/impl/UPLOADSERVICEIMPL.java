package com.nohood.duantotnghiep.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nohood.duantotnghiep.service.UPLOADSERVICE;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UPLOADSERVICEIMPL implements UPLOADSERVICE {
    @Autowired
    ServletContext app;
    @Override
	public File save(MultipartFile file, String folder) {
		File dir = new File(app.getRealPath("/" + folder));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get("..\\ShopQuanAoNam\\src\\main\\resources\\static\\images\\" + folder + "\\" + name);
			Files.write(path, bytes);
			File savedFile = new File(dir, name);
			file.transferTo(savedFile);
			return savedFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
    @Override
	public void saveproduct(MultipartFile[] files, String namefolder) {
		String folder = "..\\ShopQuanAoNam\\src\\main\\resources\\static\\images\\products\\" + namefolder;
		File dir = new File(folder);
		dir.mkdirs();
		try {
			int i = 1;
			for(MultipartFile file : files) {
				
				String s1 = "index"+i;
				String s = System.currentTimeMillis() + file.getOriginalFilename();
				String name = s1 + s.substring(s.lastIndexOf("."));
				byte[] bytes = file.getBytes();
				Path path = Paths.get("..\\ShopQuanAoNam\\src\\main\\resources\\static\\images\\products\\" + namefolder + "\\" + name);
				Files.write(path, bytes);
				i++;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	@Override
	public void updateproduct(MultipartFile[] files, String namefolder) {
		String folder = "..\\ShopQuanAoNam\\src\\main\\resources\\static\\images\\products\\" + namefolder;
		File dir = new File(folder);
		dir.mkdirs();
		try {
			int i = 1;
			for(MultipartFile file : files) {
				
				String s1 = "index"+i;
				String s = System.currentTimeMillis() + file.getOriginalFilename();
				String name = s1 + s.substring(s.lastIndexOf("."));
				byte[] bytes = file.getBytes();
				Path path = Paths.get("..\\ShopQuanAoNam\\src\\main\\resources\\static\\images\\products\\" + namefolder + "\\" + name);
				Files.write(path, bytes);
				i++;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
