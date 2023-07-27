<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
//document 내 모든 요소가 로딩된 후에 실행
$(function(){
	//이미지 미리보기 시작///////////////////////////////
	//<input type="file" name="bookImage"...
	$("input[name='bookImage']").on("change", handleImgFileSelect);
	
	//e : onchange 이벤트 객체
	function handleImgFileSelect(e){
		//e.target : <input type="file" name="bookImage"...
		let files = e.target.files;
		//이미지 오브젝트 배열
		let fileArr = Array.prototype.slice.call(files);
		//f : fileArr(이미지 오브젝트 배열)에서 이미지 오브젝트 1개
		fileArr.forEach(function(f){
			//f.type : 이미지 오브젝트의 MIME 타입
			if(!f.type.match("image.*")){
				alert("이미지 확장자만 가능합니다.");
				//함수 종료
				return;
			}
		
			//if문 통과(이미지라면...)
			//이미지를 읽어보자
			let reader = new FileReader();
			reader.onload = function(e) {
				//e.target : 읽고 있는 이미지 객체
				let img_html = "<img src='" + e.target.result + "' style='width:100%;' />";
	               $("#row").children("div").first().html(img_html);
	               $(".bg-register-image").removeClass("bg-register-image");
			}
			//다음 이미지 파일(f)을 위해 reader를 초기화
			reader.readAsDataURL(f);
		});//end forEach
		
		//$(".bg-register-image").removeClass("bg-register-image");
		//이미지를 담고 있는 div
		//$("#row").first().html();
	}
	//이미지 미리보기 끝///////////////////////////////

	/*
	<a class="clsCategory" href="#">Hello Coding</a>
    <a class="clsCategory" href="#">IT 모바일</a>
    <a class="clsCategory" href="#">소설</a>
	*/
	$(".clsCategory").on("click", function(){
		//this : 여러개 중에 클릭한 바로 그 요소
		let category = $(this).html();
		console.log("category : " + category);
		
		//<button id="category" ....
		$("#category").html(category);
		//<input type="hidden" name="category" />
		$("input[name='category']").val(category);
	});
});

</script>

<div class="row" id="row">
	<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
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
			<form class="bookInfoFrm" action="/bookInfo/addBookPost" method="post" 
				enctype="multipart/form-data">
				<!-- 폼데이터 -->
				<div class="form-group row">
					<div class="col-sm-6 mb-3 mb-sm-0">
						<input type="text" class="form-control form-control-user"
							id="bookId" name="bookId" placeholder="도서코드" />
					</div>
					<div class="col-sm-6">
						<input type="text" class="form-control form-control-user"
							id="name" name="name" placeholder="도서명" />
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-6 mb-3 mb-sm-0">
						<input type="number" class="form-control form-control-user"
							id="unitPrice" name="unitPrice" placeholder="가격" />
					</div>
					<div class="col-sm-6">
						<input type="text" class="form-control form-control-user"
							id="author" name="author" placeholder="저자" />
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-6 mb-3 mb-sm-0">
						<input type="text" class="form-control form-control-user"
							id="publisher" name="publisher" placeholder="출판사" />
					</div>
					<div class="col-sm-6">
						<input type="date" class="form-control form-control-user"
							id="releaseDate" name="releaseDate" placeholder="출판일" />
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-6 mb-3 mb-sm-0">
						<input type="number" class="form-control form-control-user"
							id="totalPages" name="totalPages" placeholder="총페이지수" />
					</div>
					<div class="col-sm-6">
						<button class="btn btn-primary dropdown-toggle" type="button" id="category" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        	분류
                        </button>
                      <div class="dropdown-menu animated--fade-in" aria-labelledby="dropdownMenuButton" style="">
                           <a class="dropdown-item clsCategory" href="#">Hello Coding</a>
                           <a class="dropdown-item clsCategory" href="#">IT 모바일</a>
                           <a class="dropdown-item clsCategory" href="#">소설</a>
                      </div>
                      <input type="text" name="category" />
					</div>
				</div>
				<div class="form-group">
					<textarea type="email" class="form-control form-control-user"
						id="description" name="description"
						placeholder="상세정보"></textarea>
				</div>
				<div class="form-group row">
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="condition" id="condition1" value="신규도서" checked/>
					  <label class="form-check-label" for="condition1">신규도서</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="condition" id="condition2" value="중고도서" />
					  <label class="form-check-label" for="condition2">중고도서</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="condition" id="condition3" value="E-Book" />
					  <label class="form-check-label" for="condition3">E-Book</label>
					</div>
				</div>
				<div class="form-group row">
					<div class="input-group mb-3">
					  <input type="file" class="form-control" id="bookImage" name="bookImage" />
					</div>
				</div>
				<button type="submit"class="btn btn-primary btn-user btn-block">
					도서 등록</button>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	CKEDITOR.replace("description");
</script>