function showItemsByGuArr(areaArr){
	
	let str = '';
	for(sigunguCode in areaArr){
		let item = areaArr[sigunguCode];
		
		if(item.firstimage ==""){ 
		str += `
				            <li class="article-item">
				            	<div class="items">
					                <div class="image-box">
			                            <img src="/assets/img/preparingforimage.png" alt="${item.title}">
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
			                            <img src="${item.firstimage}" alt="${item.title}">
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