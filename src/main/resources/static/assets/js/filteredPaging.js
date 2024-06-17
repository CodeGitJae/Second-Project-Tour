function changeGuPaging(curPage, totalPages){
	let startPage = curPage - (curPage % 5 == 0 ? 4 : (curPage % 5 -1));
	if(startPage < 1){
		startPage = 1;
	}
	
	let endPage = startPage  + 4 ;
	if(endPage > totalPages){
		endPage = totalPages;
	}
	
	let prevPage =  (curPage - 5 < 1) ? 1 : curPage - 5;
	let nextPage = (curPage + 5 >= totalPages) ? totalPages : (curPage + 5 );
/*	let nextPage = curPage < totalPages ? curPage +1 : totalPages;*/
	
	
	let str = `<div class="pagination style="display: flex; justify-content: center;"">
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				    <li class="page-item">
				      <a class="page-link prevPage" href="#" data-page="1" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					     <li class="page-item"><a class="page-link prevPage" href="#" data-page="${prevPage}">이전</a></li>`;

	        
	for(pageNum = startPage; pageNum<=endPage; pageNum++){	
	  str += `	<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
					<li class="page-item ${pageNum === curPage ? 'active' : ''}">
						<a class="page-link movePage" href="#" data-page="${pageNum}">${pageNum}</a>
					</li>
				</c:forEach>`;
		}
			
	  str += `<li class="page-item"><a class="page-link nextPage" href="#" data-page="${nextPage}">다음</a></li>
		  		<li class="page-item">
			      <a class="page-link nextPage" href="#" data-page="${totalPages}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			</nav>
		</div>`
	
	return str;
	}