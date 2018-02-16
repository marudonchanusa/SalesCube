<div style="width: 1010px; height: 25px;">
		<input type="hidden" id="searchResultCount" value="${f:h(searchResultCount)}">
		<div style="position:absolute; left: 0px;">検索結果件数： ${searchResultCount}件</div>
		<div style="position:absolute; width: 1160px; text-align: center;">
			${sw:pageLink(searchResultCount,rowCount,pageNo)}
		</div>
        <jsp:include page="/WEB-INF/view/common/rowcount.jsp"/>
</div>

<table id="search_result" summary="searchResult" class="forms detail_info" style="table-layout: auto; margin-top: 20px;">
	<colgroup>
		<col span="1" style="width: 20%">
		<col span="1" style="width: 20%">
		<col span="1" style="width: 20%">
		<col span="1" style="width: 20%">
		<col span="1" style="width: 20%">
	</colgroup>
	<tr>
		<th class="rd_top_left" style="cursor: pointer; height: 30px;" rowspan="2">適用日</th>
		<th class="xl64" style="cursor: pointer; height: 30px;">顧客コード</th>
		<th class="xl64" style="cursor: pointer; height: 30px;">顧客名</th>
		<th class="xl64" style="cursor: pointer; height: 15px;">商品コード</th>
		<th class="rd_top_right" style="cursor: pointer">単価</th>
	</tr>
	<c:forEach var="bean" items="${searchResultList}" varStatus="status">
		<td>${bean.applyDate}</td>
		<td>${bean.customerCode}</td>
		<td>&nbsp;</td>
		<td>${bean.productCode}</td>
		<td>${bean.retailPrice}</td>
	</c:forEach>
</table>

<div style="position:absolute; width: 1160px; text-align: center; margin-top: 20px;">
	${sw:pageLink(searchResultCount,rowCount,pageNo)}
</div>

<%-- 今回の検索条件を保持 --%>
<input type="hidden" id="prev_applyDate" name="prev_applyDate" value="${f:h(applyDate)}">
<input type="hidden" id="prev_customerCodeFrom" name="prev_customerCodeFrom" value="${f:h(customerCodeFrom)}">
<input type="hidden" id="prev_customerCodeTo" name="prev_customerCodeTo" value="${f:h(customerCodeTo)}">
<input type="hidden" id="prev_productCodeFrom" name="prev_productCodeFrom" value="${f:h(productCodeFrom)}">
<input type="hidden" id="prev_productCodeTo" name="prev_productCodeTo" value="${f:h(productCodeTo)}">

