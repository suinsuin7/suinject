<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
   // document 내 모든 요소가 로딩된 후에 실행
   $(function() {
      bookImg = "<img src=/resources/"
      $("input[name='bookImage']").html();
      
      // 이미지 미리보기 시작 ///////////////////////////////////
      // <input type="file" name="bookImage" ... >
      $("input[name='bookImage']").on("change", handleImgFileSelect);

      // e : onchange 이벤트 객체
      function handleImgFileSelect(e) {
         // e.target :    <input type="file" name="bookImage" ... >
         let files = e.target.files;
         // 이미지 오브젝트 배열
         let fileArr = Array.prototype.slice.call(files);
         let img_html = "";
         // f : fileArr(이미지 오브젝터 배열)에서 이미지 오브젝트 1개
         fileArr.forEach(function(f) {
            // f.type : 이미지 오브젝트의 MIME 타입
            if (!f.type.match("image.*")) {
               alert("이미지 확장자만 가능합니다.");
               // 함수 종료
               return;
            }
            //if문 통과(이미지라면...)
            //이미지를 읽어보자
            let reader = new FileReader();
            reader.onload = function(e) {
               // e.target : 읽고 있는 이미지 객체
               console.log("")
               let img_html = "<img src='" + e.target.result + "' style='width:100%;' />";
               $("#row").children("div").first().html(img_html);
               $(".bg-register-image").removeClass("bg-register-image");
            }
            // 다음 이미지 파일(f)를 위해 reader를 초기화
            reader.readAsDataURL(f);
         })   // end forEach
//          $(".bg-register-image").removeClass("bg-register-image");
//          // 이미지를 담고있는 div
//          $("#row").first().html(img_html);
         
      }
      // 이미지 미리보기 끝   ///////////////////////////////////
      
      $(".clsCategory").on("click", function() {
         //this: 여러개 중에 클릭한 바로 그 요소
         let category = $(this).html();
         console.log("category : " + category);
         $("#category").html(category);
         //<input type="hidden" name="category" />
         $("input[name='category']").val(category);
      })
      
      
      //도서코드 자동생성
      $("#aBookId").on("click", function(){
         console.log("개똥이");
         
         //contentType: 보내는타입
         //dataType: 응답타입
         $.ajax({
            url:"/bookInfo/getBookId",
            type:"post",
            dataType:"text",
            success:function(result){
               console.log("result : " + result);
               //도서코드 텍스트박스에 값 넣기
               $("#bookId").val(result);
            }
         })
      })
      
      //class가 form-control-user인 요소들을 readonly 처리
      $(".form-control-user").attr("readonly","readonly");
      
      $("#category").attr("disabled","disabled");
      
      //상태
      $("input[name='condition']").attr("disabled","disabled");
//       $("input[name='condition']").attr("onclick","return(false)");

      //파일 등록 버튼 가리기
      $("#bookImage").parent().parent().css("display", "none");
      
      $('#description').attr("readonly","readonly");
      
//       //ckeditor
//       CKEDITOR.instances['description'].setReadOnly(true);


      // 수정버튼 클릭 -> 수정모드로 전환
      $('#edit').on("click", function() {
         $("#div1").css("display", "none");
         $("#div2").css("display", "block");
         
         $(".form-control-user").removeAttr("readonly");
         
         $("#category").removeAttr("disabled");
         
         //상태
         $("input[name='condition']").removeAttr("disabled");
//          $("input[name='condition']").attr("onclick","return(false)");

         //파일 등록 버튼 가리기
         $("#bookImage").parent().parent().css("display", "block");
         
//          $('#description').removeAttr("readonly");
          CKEDITOR.instances['description'].setReadOnly(false);
          
          //도서아이디는 계속 비활성화
          $("input[name='bookId']").attr("readonly", true);
      });

      //삭제버튼 클릭
      $("#delete").on("click", function(){
    	//<form class="bookInfoFrm" action의 속성값을 /bookInfo/deleteBookPost로 변경
    	$(".bookInfoFrm").attr("action", "/bookInfo/deleteBookPost");
    	//BookInfoController에서 해당 URI를 받아서 
    	//log.info("bookId : " + bookInfoVO.getBookId());
    	//도서 목록으로 redirect
    	
    	//삭제하시겠습니까? confirm 처리
		let result = confirm("삭제하시겠습니까?");
		console.log("result : " + result);
		
    	if(result==true) { //확인
    		//form 요소를 선택하여 submit처리	
    		$(".bookInfoFrm").submit();
    	}else{ //취소
    		//삭제가 취소되었습니다. 경고창 처리
    		alert("삭제가 취소되었습니다.");
    	}
      });
   });
</script>
<div class="row" id="row">
<!-- attachVOList : List<AttachVO> -->
   <div class="col-lg-5 d-none d-lg-block bg-register-image"
   style="background-image:url('/resources/images${data.attachVOList[0].filename}')"></div>
   <div class="col-lg-7">
      <div class="p-5">
         <div class="text-center">
            <h1 class="h4 text-gray-900 mb-4">도서등록</h1>
         </div>
         <!-- 
         요청URL : /bookInfo/addBookPost
         요청파라미터 : {bookId=ISBN1234,name=...}
         요청방식 : post
          -->
         <form class="bookInfoFrm" action="/bookInfo/updatePost" method="post"
            enctype="multipart/form-data" >
         <!-- 폼데이터 -->
            <div class="form-group row">
               <div class="col-sm-6 mb-3 mb-sm-0">
                  <input type="text" class="form-control form-control-user"
                     id="bookId" value="${data.bookId}" placeholder="도서코드" name="bookId" readonly>
               </div>
               <div class="col-sm-6">
                  <input type="text" class="form-control form-control-user"
                     id="name" value="${data.name}" placeholder="도서명" name="name">
               </div>
            </div>
            <div class="form-group row">
               <div class="col-sm-6 mb-3 mb-sm-0">
                  <input type="number" class="form-control form-control-user"
                     id="unitPrice" value="${data.unitPrice}" placeholder="가격" name="unitPrice">
               </div>
               <div class="col-sm-6">
                  <input type="text" class="form-control form-control-user"
                     id="author" value="${data.author}" placeholder="저자" name="author">
               </div>
            </div>
            <div class="form-group row">
               <div class="col-sm-6 mb-3 mb-sm-0">
                  <input type="text" class="form-control form-control-user"
                     id="publisher" value="${data.publisher}" placeholder="출판사" name="publisher">
               </div>
               <div class="col-sm-6">
                  <input type="date" class="form-control form-control-user"
                     id="releaseDate" value="${data.releaseDate}" placeholder="출판일" name="releaseDate">
               </div>
            </div>
            <div class="form-group row">
               <div class="col-sm-6 mb-3 mb-sm-0">
                  <input type="number" class="form-control form-control-user"
                     id="totalPages" value="${data.totalPages}" placeholder="총페이지수" name="totalPages">
               </div>
               <div class="col-sm-6">
                  <button class="btn btn-primary dropdown-toggle" type="button"
                     id="category" name="category" data-toggle="dropdown"
                     aria-haspopup="true" aria-expanded="true">${data.category}</button>
                  <div class="dropdown-menu animated--fade-in"
                     aria-labelledby="dropdownMenuButton" style="">
                     <a class="dropdown-item clsCategory" href="#">Hello Coding</a> <a
                        class="dropdown-item clsCategory" href="#">IT 모바일</a> <a
                        class="dropdown-item clsCategory" href="#">소설</a>
                  </div>
                  <input type="hidden" name="category" value="${data.category}" />
               </div>
            </div>
            <div class="form-group">
               <textarea class="form-control form-control-user" id="description"
                  name="description" placeholder="Email Address">${data.description}</textarea>
            </div>
            <div class="form-group row">
               <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="condition"
                     id="condition1" value="신규도서"
                     <c:if test="${data.condition=='신규도서'}">checked</c:if>
                     /> <label
                     class="form-check-label" for="inlineRadio1">신규도서</label>
               </div>
               <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="condition"
                     id="condition2" value="중고도서"
                     <c:if test="${data.condition=='중고도서'}">checked</c:if>
                     /> <label
                     class="form-check-label" for="inlineRadio2">중고도서</label>
               </div>
               <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="condition"
                     id="condition3" value="E-Book"
                     <c:if test="${data.condition=='E-Book'}">checked</c:if>
                     /> <label
                     class="form-check-label" for="inlineRadio3">E-Book</label>
               </div>
            </div>
            <div class="form-group row">
               <div class="input-group mb-3">
                  <input type="file" class="form-control" id="bookImage"
                     name="bookImage" />
               </div>
            </div>
            <!-- 일반모드 -->
            <div id="div1">
               <p>
                  <button type="button" id="edit"
                     class="btn btn-primary btn-user btn-block"
                     style="width:50%;float:left;">수정</button>
                  <button type="button" id="delete"
                     class="btn btn-primary btn-user btn-block"
                     style="width:50%">삭제</button>
               </p>
               <p>
                  <a href="/bookInfo/listBook"
                     class="btn btn-success btn-user btn-block">목록</a>
               </p>
            </div>
            <!-- 수정모드 -->
            <div id="div2" style="display:none;">
               <button type="submit" class="btn btn-primary btn-user btn-block">확인</button>
               <a href="/bookInfo/detailBook?bookId=${param.bookId}"
                  class="btn btn-success btn-user btn-block">취소</a>
            </div>
         </form>
      </div>
   </div>
</div>
<script>
   CKEDITOR.replace("description");
</script>