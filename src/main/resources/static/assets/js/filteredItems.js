function showItemsByGuArr(areaArr){
	
	let str = '';
	for(sigunguCode in areaArr){
		let item = areaArr[sigunguCode];
		
		if(item.firstimage ==""){ 
		str += `
				            <li class="article-item">
				            	<div class="items">
					                <div class="image-box">
					                	<a href="/Seoul/showdetail?contentId=${item.contentid}">
			                              	<img src="/assets/img/preparingforimage.png" alt="${item.title}">
					                	</a>
					                </div>
					                <div class="text-box">
					                    <h3>${item.title}</h3>
					                    <p>${item.addr1}</p>
					                </div>
				                </div>
		           			 </li>`;
		} else {
	    str +=	`	        <li class="article-item">
				            	<div class="items">
					                <div class="image-box">
						                <a href="/Seoul/showdetail?contentId=${item.contentid}">
				                            <img src="${item.firstimage}" alt="${item.title}">
						                </a>
					                </div>
					                <div class="text-box">
					                    <h3>${item.title}</h3>
					                    <p>${item.addr1}</p>
					                </div>
				                </div>
				            </li>`;		
		}  
	}
	
	return str;
}