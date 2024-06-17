let	img = $(".article-item").find("img").attr("src");
let	title= $(".article-item").find("h3").text();
localStorage.setItem('items', img);
localStorage.setItem('items', title);
console.log(img, title);
$(document).ready(function(){
	// 로컬 스토리지 초기화
	// item이 JSON 형태의 문자열로 담길 수 있게 초기화 시 배열로 설정
	if(!localStorage.getItem('items')){
	 localStorage.setItem('items', JSON.stringify([]));
	}

	// 플로팅 배너 시작	
	// 기존 css에서 플로팅 배너 위치(top)값을 가져와 저장한다.
	var floatPosition = parseInt($("#pastItemMenu").css('top'));
	// 250px 이런식으로 가져오므로 여기서 숫자만 가져온다. parseInt( 값 );

	$(window).scroll(function() {
		// 현재 스크롤 위치를 가져온다.
		var scrollTop = $(window).scrollTop();
		var newPosition = scrollTop + floatPosition + "px";

		/* 애니메이션 없이 바로 따라감
		 $("#floatMenu").css('top', newPosition);
		 */

		$("#pastItemMenu").stop().animate({
			"top" : newPosition
		}, 500);

	}).scroll();
	// 플로팅 배너 끝	
	
	// 최근본 게시물 페이지 로딩 될 때 불러오기 함수
	loadStorageItem();
	
});


 // 게시글 정보 전처리 및 localStorage에 추가
$(".getItem").on("click", function(e){
//	e.preventDefault();
	
	// a태그 hrf 속성 가져오기
	const href = $(this).attr("href");
	// 쿼리스트링 "?" 기준으로 나누고 두번째 인덱스 값 저장
	const getQueryStr = href.split("?")[1];
	
	// urlSearchParams 생성자를 통해 파라미터를 저장(키:벨류 형식)
	// 이 생성자는 자바스크립트의 내장 객체라고함.
	const getUrlParams = new URLSearchParams(getQueryStr);
	console.log("getUrlParams :", getUrlParams);
	
	// get() 메서드를 이용해서 key값의 value 가져오기
	const getContentId = getUrlParams.get("contentId");
	console.log("getContentId :", getContentId); 
	
	const imgElement = $(this).find(".img").get(0);
	const titleElement = $(this).closest(".items").find(".title").get(0);
	 
 	const imgSrc = imgElement.src;
 	const titleText = titleElement.textContent.trim(); //trim 공백 제거후 반환함
	
	// 최근 본 게시물 정보를 하나의 객체에 담기 (key : value) 
	let item = {cententid: getContentId, img: imgSrc, title: titleText}
//	localStorage.setItem("items", JSON.stringify([item]));
	
	console.log("이미지 주소 타입은 ",typeof imgSrc);
	console.log("이미지 주소 :", imgSrc); 
//	localStorage.setItem("img", imgSrc);
	
	console.log("제목 타입은 ",typeof titleText);
	console.log("제목 :", titleText);
//  localStorage.setItem("title", titleText);


	// local Stroage 저장된 객체 가져와서 스크립트에서 사용할수있는 Json 객체로 만들기 
	let items = localStorage.getItem('items');
	let itemList = JSON.parse(items) || [];  // 게시물 저장할 때 값이 없을때 빈 배열 저
	console.log("itemList Json:", itemList);
	
	// 새로운 객체(새롭게 본 게시물 정보) 추가
	itemList.push(item);
	console.log("pushItem:", itemList);
	
	// Set 중복 처리를 위해 문자열로 파싱
	let parseStrItem = itemList.map(JSON.stringify);
	// Array를 Set으로 바꾸기 (중복 방지)
	setListItem = new Set(parseStrItem);
	console.log("setType:", setListItem);
	
	// Set을 Array로 바꾸기
	arrayListItem = Array.from(setListItem);
	// 자바스크립트에서 사용 가능한 JSON 객체로 파싱
	itemList = arrayListItem.map(JSON.parse);
	console.log("arrayType :", itemList);
	
	// list가 3개이상일 경우 제일 오래된 item 삭제
	if(itemList.length > 3){
		itemList.shift();
	}
	
	// 상품이 추가된 배열을 다시 localStorage에 추가하기
	localStorage.setItem('items', JSON.stringify(itemList));
	   
// });
	
    // View 메서드 호출
	updateItemForView(itemList);
 
});

// 아래 부터는 함수

// 버튼 클릭 시 localStorage 데이터와 지난 게시물 정보 동시 삭제 메서드 (태그에 onclick 추가)
function deleteItemAll(){
	localStorage.clear();
	$(".showItemList").empty();	
}

// 페이지 로딩 시 게시물 목록 업데이트 메서드
function loadStorageItem(){
	let items = localStorage.getItem('items');
	let itemsList = JSON.parse(items) || [];
	updateItemForView(itemsList);
}


// 내가 본 게시물 추가를 위한 view 메서드 생성
function updateItemForView(itemList){
		if(itemList.length != 0){
		/* history가 있을 경우 .none_noti.hide(), .noti.show(); */
		$('.showItemList').show();
		$('.none_noti').hide();
		
		// '|' split 하기
		var tagList = [];
		for (item in itemList){
			var pastViewArray = itemList[item]
//			console.log("result :",pastViewArray);
			var tag = `<li><a class="getItem" href="/Seoul/showdetail?contentId=${pastViewArray.cententid}">
						<img style="width: 235px; height: 150px; margin-bottom: 10px;"
						src="${pastViewArray.img}" alt="${pastViewArray.title}"></a></li>
						<li>${pastViewArray.title}</li>`;
			tagList.push(tag);
			
//			var strArray = itemList[item];
//			var tag = '<li>'+ strArray +'</li>';
//			tagList += tag;
		}
		$('.showItemList').html(tagList);
		
		} else {
			/* history가 없을 경우 .none_noti.show(), .noti.hide(); */
			$('.none_noti').show();
	//		$('.itemsContent').hide(); // 이 줄을 추가하여 .noti를 숨깁니다.
		}
}
