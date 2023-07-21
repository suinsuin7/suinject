package kr.or.ddit.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.ProductService;
import kr.or.ddit.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

//프링이가 "이 클래ㅔ스는 컨트롤러구나! 자바빈으로 등록해야징~~" 라고 처리함
@Slf4j
@Controller
public class ProductController {
	//이미 만들어진 자바빈을 주입!
	//DI, IoC
	@Autowired
	ProductService productService;

	//요청URL : /shopping/welcome
	//요청파라미터 : 없음
	//요청방식 : GET
	@RequestMapping(value = "/shopping/welcome", method = RequestMethod.GET)
	public ModelAndView welcome(ModelAndView mav) {
		// /WEB-INF/views/shopping/welcome.jsp
		mav.setViewName("shopping/welcome");
		//forwarding
		return mav;
	}
	
	//요청URL : /shopping/addProduct
	//요청파라미터 : 없음
	//요청방식 : GET
	@RequestMapping(value = "/shopping/addProduct", method = RequestMethod.GET)
	public ModelAndView addProduct(ModelAndView mav) {
		//forwarding
		mav.setViewName("shopping/addProduct");
		
		return mav;
	}
	
	//상품 등록
//	요청URL : /shopping/processAddProduct
//	요청방식 : post
//	요청파라미터(HTTP파라미터,QueryString) :
//	{productId=P1237,pname=에어팟3,unitPrice=200000,description=굿음질,
//	manufacturer=Apple,category=Table,unitsInStock=1000,condition=Old}
	@RequestMapping(value = "/shopping/processAddProduct", method = RequestMethod.POST)
	public ModelAndView processAddProduct(ProductVO productVO,
			ModelAndView mav) {
		log.info("productVO : " + productVO);
		
		//파일 업로드 시작
		// 1) 업로드 폴더 설정
		String uploadFolder 
			= "E:\\eGovFrame3.10.0\\workspace\\springProj\\src\\main\\webapp\\resources\\images";
		log.info("uploadFolder : " + uploadFolder);
		//vo객체에서 이미지 파일 객체를 get함
		MultipartFile multipartFile = productVO.getProductImage();
		
		String filename = multipartFile.getOriginalFilename();
		log.info("---------------------------");
		log.info("파일명 : " + multipartFile.getOriginalFilename());
		log.info("파일 크기 : " + multipartFile.getSize());
		
		//랜덤값 생성
		UUID uuid = UUID.randomUUID();
		filename = uuid.toString() + "_" + filename;
		
		//File객체 설계(복사할 대상 경로, 파일명)
		File saveFile = new File(uploadFolder, filename);
		try {
			//파일 복사 실행
			multipartFile.transferTo(saveFile);
			
			//productVO객체의 filename 멤버변수에 업로드될 이미지의 명을 set함
			productVO.setFilename(filename);
			
			//PRODUCT 테이블에 INSERT 기능 구현
			int result = productService.processAddProduct(productVO);
			log.info("result : " + result);
			
			//redirect
			mav.setViewName("redirect:/shopping/products");
			
			return mav;
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			mav.setViewName("redirect:/shopping/addProduct");
		}
		//파일 업로드 끝
		//redirect
		return mav;
	}
	
	// 상품 목록
	//요청URL : /shopping/products
	//요청파라미터 : 없음
	//요청방식 : get
	@RequestMapping(value="/shopping/products", method = RequestMethod.GET)
	public ModelAndView products(ModelAndView mav) {
		
		List<ProductVO> attributeValue = this.productService.products();
		log.info("attributeValue : " + attributeValue);
		
		mav.addObject("listOfProducts", attributeValue);
		//forwarding
		mav.setViewName("shopping/products");
		
		return mav;
	}
	
	//상품 상세
	//요청URL : /shopping/product?productId=P1234
	//요청파라미터 : productId=P1234
	//요청방식 : get
	@RequestMapping(value = "/shopping/product", method=RequestMethod.GET)
	public ModelAndView product(@RequestParam String productId,
			ModelAndView mav) {
		log.info("productId : " + productId);
		
		ProductVO productVO= this.productService.product(productId);
		log.info("productVO : " + productVO );
		//데이터
		mav.addObject("product", productVO);
		
		//forwarding
		mav.setViewName("shopping/product");
		
		return mav;
	}
	
    //addToCart (장바구니)
    @RequestMapping(value = "/shopping/addCart", method = RequestMethod.POST)
    public ModelAndView addCart(ModelAndView mav, @RequestParam String productId, HttpServletRequest request ) {
        log.info("productId: " + productId);
        HttpSession session = request.getSession();

        if (productId==null || productId.trim().equals("")) {
            mav.setViewName("redirect:/shopping/products");
            return mav;
        }

        ProductVO product = this.productService.product(productId);
        //상품 결과가 없으면...
        if (product==null) {
            //[상품이 없음] 예외처리 페이지로 이동
            mav.setViewName("redirect:/shopping/exceptionNoProductId");
            return mav;
        }

        //상품이 있으면 계속 실행
        //장바구니(session) => 세션명: cartlist
        ArrayList<ProductVO> list
                = (ArrayList<ProductVO>) session.getAttribute("cartlist");
        //장바구니 없으면 생성
        if (list==null) {
            //list는 null이므로 여기서 리스트를 생성해줘야 함
            list = new ArrayList<ProductVO>();
            //cartlist라는 세션명으로 생성
            session.setAttribute("cartlist", list);
        }
        int cnt = 0;
        //장바구니가 있다면 다음을 실행
        //1) 장바구니에 P1234 상품이 이미 있는 경우
        //    private int quantity; 장바구니에 담은 개수
        //    quantity를 1 증가
        for (int i = 0; i < list.size(); i++) {
            //list는 P1234, P1235, P1236
            //list.get(0).getProductId().equals("P1234")
            if (list.get(i).getProductId().equals(productId)) {
                cnt++;//장바구니에 넣을 상품을 찾았다면 1 증가
                //장바구니에 상품이 이미 들어있다면 장바구니에 담은 개수를 1개 증가
                //list.get(i): productVO
                list.get(i).setQuantity(list.get(i).getQuantity() + 1);
            }
        }
        //2) 장바구니에 P1234 상품이 없는 경우
        if (cnt==0) {
            //    quantity를 1로 처리
            product.setQuantity(1);
            //    장바구니에 P1234 상품을 넣어주고
            list.add(product);
        }
        //장바구니 확인
        //ArrayList<productVO> : list
        for (ProductVO vo : list) {
            log.info( "vo: " + vo );
        }
        mav.setViewName("redirect:/shopping/product?productId="+productId);

        return mav;
    }
}
