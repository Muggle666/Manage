package com.hao.onlineExam.web;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/toUpload")
	public String toUpload() {
		return "file/fileUpload";
	}

	@RequestMapping(value = "/upload")
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session,
			ModelMap model) {
		String path = session.getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();// 获取上传文件的源文件名
		System.out.println(path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
			model.addAttribute("message", "Upload Successfully.");
		} else if (targetFile.exists()) {
			model.addAttribute("message", "file has already exist.");
		} else {
			model.addAttribute("message", "Upload fail.");
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "file/fileUpload";
	}

	@RequestMapping(value = "/file")
	public String users(Model model) {
		return "file/fileUpload";
	}
}
