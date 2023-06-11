package com.ssafy.trip.hotplace.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.trip.hotplace.model.dto.FileInfoDto;
import com.ssafy.trip.hotplace.model.dto.HotPlace;
import com.ssafy.trip.hotplace.model.dto.HotPlacePageBean;
import com.ssafy.trip.hotplace.model.service.HotPlaceServiceImpl;
import com.ssafy.trip.user.model.dto.User;

import io.swagger.annotations.ApiOperation;

//@Controller
//@RequestMapping("/hotPlace")
public class HotPlaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(HotPlaceController.class);
	private int pgno;
	private String key;
	private String word;
	private String queryStrig;
	
	@Value("${file.path}")
	private String realPath;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private HotPlaceServiceImpl boardService;

//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		boardService = InfoBoardServiceImpl.getBoardService();
//	}

	
	@ApiOperation(value="책 목록 정보", notes = "검색 조건에 맞는 도서 정보를 검색한 목록 정보")
	@GetMapping("/hotList")
	public void hotList(HotPlacePageBean bean, Model model) throws Exception {
		logger.debug("hotPlaces....................pageBean:{}",bean);
		List<HotPlace> hotPlaces = boardService.listArticle(bean);
		logger.debug("hotPlaces....................{}",hotPlaces);
		model.addAttribute("hotPlaces", hotPlaces);
	}
	
	@GetMapping("/view")
	public void detail(int hotId, HotPlacePageBean bean, Model model) throws Exception {
		logger.debug("hotPlace detail....................");
		boardService.updateHit(hotId);
		HotPlace hotPlace = boardService.getArticle(hotId);
		logger.debug("detail....................{}",hotPlace);
		model.addAttribute("hotPlace", hotPlace);
	}
	
	@GetMapping("/write")
	public void writeArticle(HttpSession session) {
		logger.debug("hotPlace................... write 화면으로 이동");
	}

	
	

    @PostMapping("/write")
    public String write(HotPlace hotPlace, @RequestParam("upload") MultipartFile[] files, HttpSession session,
            RedirectAttributes redirectAttributes) throws Exception {
        logger.debug("write boardDto : {}", hotPlace);
        User user = (User) session.getAttribute("userInfo");
        hotPlace.setUserId(user.getUserid());

//        FileUpload 관련 설정.
        logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
        if (!files[0].isEmpty()) {
//            String realPath = servletContext.getRealPath("/upload");
//            String realPath = servletContext.getRealPath("/resources/img");
//            String today = new SimpleDateFormat("yyMMdd").format(new Date());
//            String saveFolder = realPath + File.separator + today;
            String saveFolder = realPath + File.separator;
            logger.debug("--------저장 폴더 : {}", saveFolder);
            File folder = new File(saveFolder);
            if (!folder.exists())
                folder.mkdirs();
            List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
            for (MultipartFile mfile : files) {
                FileInfoDto fileInfoDto = new FileInfoDto();
                String originalFileName = mfile.getOriginalFilename();
                if (!originalFileName.isEmpty()) {
                    String saveFileName = UUID.randomUUID().toString()
                            + originalFileName.substring(originalFileName.lastIndexOf('.'));
                    fileInfoDto.setSaveFolder("");
                    fileInfoDto.setOriginalFile(originalFileName);
                    fileInfoDto.setSaveFile(saveFileName);
                    logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
                    mfile.transferTo(new File(folder, saveFileName));
                }
                fileInfos.add(fileInfoDto);
            }
            hotPlace.setFileInfos(fileInfos);
        }

        boardService.writeArticle(hotPlace);
        redirectAttributes.addAttribute("pgno", "1");
        redirectAttributes.addAttribute("key", "");
        redirectAttributes.addAttribute("word", "");
        return "redirect:/hotPlace/hotList";
    }
	
	
//	@PostMapping("/write")
//	public String regist(HotPlace hotPlace,  @RequestParam("upload") List<MultipartFile> files, HttpSession session) throws Exception {
//		logger.debug("hotPlace................... write");
//		logger.debug("hotPlace................... 요청데이타 hotPlace:{}",hotPlace.toString());
//		User user = (User)session.getAttribute("userInfo");
//		hotPlace.setUserId(user.getId());
//		String realPath = servletContext.getRealPath("/WEB-INF/upload");
//		logger.debug("realPath.........{}", realPath);
//		String today = new SimpleDateFormat("yyMMdd").format(new Date());
//		String saveFolder = realPath + File.separator + today;
////		logger.debug("tmpsaveFolder.........{}",  realPath +  today);
//		logger.debug("saveFolder.........{}", saveFolder);
//		File folder = new File(saveFolder);
//		FileOutputStream fos = new FileOutputStream(folder);
//		if(!folder.exists()) {
//			folder.mkdir();
//		}
//	    if (!files.isEmpty()) {
//	        List<FileInfoDto> fileInfos = new ArrayList<>();
//	        for (MultipartFile file : files) {
//	            if (file.isEmpty()) continue;
//	            String originalFileName = file.getOriginalFilename();
//	            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
//	            String savedFileName = UUID.randomUUID().toString() + extension;
//	            
//	            
//	            FileInfoDto fileInfoDto = new FileInfoDto();
//	            fileInfoDto.setOriginalFile(originalFileName);
//	            fileInfoDto.setSaveFile(savedFileName);
//	            fileInfoDto.setSaveFolder(today);
//	            fileInfos.add(fileInfoDto);
//	            
//	            File nowSaveFile = new File(saveFolder + File.separator + originalFileName);
////	            file.transferTo(new File(folder, savedFileName));
//	            file.transferTo(nowSaveFile);
//	            
//	        }
//	        hotPlace.setFileInfos(fileInfos);
//	    }
	    
	    
//	    
//		boardService.writeArticle(hotPlace);
////	    redirectAttributes.addAttribute("pgno", "1");
////	    redirectAttributes.addAttribute("key", "");
////	    redirectAttributes.addAttribute("word", "");
//		return "redirect:/hotPlace/hotList";
//	}
//	


	@GetMapping("/delete")
	public String delete(int hotId, HotPlacePageBean bean, RedirectAttributes ra) throws Exception {
		logger.debug("book................... remove isbn:{}",hotId);
		boardService.deleteArticle(hotId);
		ra.addAttribute("pageNo", bean.getPageNo());
		ra.addAttribute("key", bean.getKey());
		ra.addAttribute("word", bean.getWord());
		return "redirect:/hotPlace/hotList";
	}
	
	@GetMapping("/modify")
	public void modify(int hotId, Model model) throws Exception {
		logger.debug("hotPlace................... update isbn:{}",hotId);
		HotPlace hotPlace = boardService.getArticle(hotId);
		logger.debug("hotPlace................... update book:{}",hotPlace);
		model.addAttribute("hotPlace", hotPlace);
	}
	
	@PostMapping("/modify")
	public String modify(HotPlace hotPlace, HotPlacePageBean bean, RedirectAttributes ra) throws Exception {
		logger.debug("hotPlace................... update hotPlace:{}",hotPlace);
		boardService.modifyArticle(hotPlace);
//		ra.addAttribute("pageNo", bean.getPageNo());
//		ra.addAttribute("key", bean.getKey());
//		ra.addAttribute("word", bean.getWord());
		
		return "redirect:/hotPlace/hotList";
	}
	
	public boolean check(HttpSession session) {
//		User  user = (User)session.getAttribute("userInfo");
//		if(user == null  || !user.getId().equals("admin")) {
//			throw new BookException("관리자 전용 페이지 입니다. 접근 권한이 없습니다.");
//		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
