$(document).ready(function(){
	
	// 로컬 스토리지에 'items' 키로 빈 배열을 저장 (없을 경우에만)
	if (localStorage.getItem('items') === null) {
		localStorage.setItem('items', JSON.stringify([]));
	}
	
	/* local Stroage */
	let items = localStorage.getItem('items');
	let itemList = JSON.parse(items);
	// list가 null인 경우 빈 배열로 초기화
	if(itemList == null) itemList = [];
	
	// html요소에서 데이터를 가져옴
	$('.article-item').each(function(index, element) {
	    let title = $(element).find('.text-box h3').text();
	    let addr1 = $(element).find('.text-box p').text();
	    let image = $(element).find('.image-box img').attr('src');
	    var str = title + '|' + addr1 + '|' + image;;

	    // image가 유효한 경우에만 데이터를 배열의 앞에 추가
	    if (image && image.trim() !== '') {
	        itemList.unshift(str); // 앞에서부터 저장
	        // 로컬 스토리지에 'items' 키로 데이터를 저장
	        localStorage.setItem('items', JSON.stringify(itemList));
	    }
	    
	});
	
    /* View */
	if(itemList.length != 0){
		/* history가 있을 경우 .none_noti.hide(), .noti.show(); */
		$('.noti').show();
		$('.none_noti').hide();
		
		// '|' split 하기
		var tagList = [];
		for (item in itemList){
			var strArray = itemList[item].split('|');
			var tag = '<li>'+strArray[0] + strArray[1] +'</li>';
			tagList += tag;
		}
		$('.noti').html(tagList);
		
	} else {
		/* history가 없을 경우 .none_noti.show(), .noti.hide(); */
		$('.none_noti').show();
		$('.noti').hide(); // 이 줄을 추가하여 .noti를 숨깁니다.
	}
	 
});