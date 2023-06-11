package com.ssafy.trip.hotplace.model.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.hotplace.model.dao.HotPlaceDao;
import com.ssafy.trip.hotplace.model.dto.FileInfoDto;
import com.ssafy.trip.hotplace.model.dto.HotPlace;
import com.ssafy.trip.hotplace.model.dto.HotPlacePageBean;
import com.ssafy.trip.util.PageUtility;

@Service
public class HotPlaceServiceImpl implements HotPlaceService {
	private HotPlaceDao boardDao;

	@Value("${file.imgPath}")
	private String realPath;
	private static final Logger logger = LoggerFactory.getLogger(HotPlaceService.class);
//	private static InfoBoardService boardService = new InfoBoardServiceImpl(boardDao);

//	private InfoBoardServiceImpl() {
//		boardDao = InfoBoardDaoImpl.getBoardDao();
//	}
//	@Autowired
//	private HotPlaceService boardService;
	private HotPlaceServiceImpl(HotPlaceDao boardDao) {
		this.boardDao = boardDao;
	}

//	public static InfoBoardService getBoardService() {
//		return boardService;
//	}

//	@Override
//	public void writeArticle(HotPlace boardDto) throws Exception {
//		boardDao.writeArticle(boardDto);
//		List<FileInfoDto> fileInfos = boardDto.getFileInfos();
//		
//		if (fileInfos != null && !fileInfos.isEmpty()) {
//			boardDao.uploadFile(boardDto);
//		}
////		System.out.println("여기 찍어본거임" + boardDao.toString());
//	}
	@Override
//	@Transactional
	public void writeArticle(HotPlace hotPlace) throws Exception {
//        User user = (User) session.getAttribute("userInfo");
//        hotPlace.setUserId(user.getUserid());

//        FileUpload 관련 설정.
//        logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
//		try {
		MultipartFile[] files = hotPlace.getFileup();
		
		if(files != null) {
//            String realPath = servletContext.getRealPath("/upload");
//            String realPath = servletContext.getRealPath("/resources/img");
//            String today = new SimpleDateFormat("yyMMdd").format(new Date());
//            String saveFolder = realPath + File.separator + today;
			String saveFolder = realPath + File.separator;
//            logger.debug("--------저장 폴더 : {}", saveFolder);
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
			logger.debug("업로드한 파일의 hot id.....................{}",hotPlace);
			
		}
		boardDao.writeArticle(hotPlace);
		logger.debug("업로드 이후 hotplace의 hot Id.......................!!!!{}", hotPlace);
		boardDao.uploadFile(hotPlace);
//		} catch (Exception e) {
//			e.printStackTrace();
//			// 오류가 났기 때문에 모든 데이타는 rollback이 되고 저장한 파일 정보도 삭제해야 한다.
//			fileRemove(realFile);
//			throw new BookException("책 정보 등록 중 오류 발생 ");
//		}
	}

	public void fileRemove(File[] realFile) {
		if (realFile != null && realFile.length > 0) {
			for (File file : realFile) {
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}

//	@Override
//	public List<HotPlaceBoard> listArticle(Map<String, String> map) throws Exception {
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
////		if("userid".equals(key))
////			key = "user_id";
//		param.put("key", key.isEmpty() ? "" : key);
//		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);
//		return boardDao.listArticle(param);
//	}

	@Override
	public List<HotPlace> listArticle(HotPlacePageBean bean) throws SQLException {

		List<HotPlace> first = boardDao.listArticle(bean);
		int total = boardDao.getTotalArticleCount(bean);
//			logger.debug("searchAll  first read..................{}",first);
		PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "");
		bean.setPageLink(page.getPageBar()); // paging 처리한 html 링크를 추출하기
		return first;

	}

//	@Override
//	public PageNavigation makePageNavigation(HotPlacePageBean bean) throws Exception {
//		PageNavigation pageNavigation = new PageNavigation();
//
//		int naviSize = SizeConstant.NAVIGATION_SIZE;
//		int sizePerPage = SizeConstant.LIST_SIZE;
//		int currentPage = Integer.parseInt(map.get("pgno"));
//
//		pageNavigation.setCurrentPage(currentPage);
//		pageNavigation.setNaviSize(naviSize);
////		Map<String, Object> param = new HashMap<String, Object>();
////		String key = map.get("key");
////		if ("userid".equals(key))
////			key = "user_id";
////		param.put("key", key.isEmpty() ? "" : key);
////		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
//		int totalCount = boardDao.getTotalArticleCount(bean);
//		pageNavigation.setTotalCount(totalCount);
//		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//		pageNavigation.setTotalPageCount(totalPageCount);
//		boolean startRange = currentPage <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//		pageNavigation.setEndRange(endRange);
//		pageNavigation.makeNavigator();
//
//		return pageNavigation;
//	}

	@Override
	public HotPlace getArticle(int hotId) throws Exception {
		logger.debug("fileupload 이미지 띄우기...............{}",boardDao.getArticle(hotId));
		return boardDao.getArticle(hotId);
	}

	@Override
	public void updateHit(int hotId) throws Exception {
		boardDao.updateHit(hotId);
	}

	@Override
	public void modifyArticle(HotPlace boardDto) throws Exception {
		// TODO : BoardDaoImpl의 modifyArticle 호출
		boardDao.modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(int hotId) throws Exception {
		// TODO : BoardDaoImpl의 deleteArticle 호출
		boardDao.deleteImage(hotId);
		boardDao.deleteArticle(hotId);
	}

	public List<HotPlace> listArticleAll() {
		List<HotPlace> result = boardDao.listArticleAll();
		return result;
	}

}
