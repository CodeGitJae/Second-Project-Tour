function showItemsByGuArr(arr){
	
	let str = '';
	for(idx in arr){
		let item = arr[idx];
		
		
		str += `
					            <li class="article-item">
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
	
	
	return str;
}