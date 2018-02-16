<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ja">
<head>
	<title><bean:message key='titles.system'/>　顧客別単価マスタ（検索）</title>

	<%@ include file="/WEB-INF/view/common/header.jsp" %>

	<script type="text/javascript">
	<!--
	var paramData = null;
	var paramDataTmp = null;

	function init() {
		// 初期表示時に全件検索
		execSearch(createData());
	}
	function onF1() {
		initForm();
	}
	function onF2() {
		searchCustomerRetailPrice();
	}
	function onF3() {

	}
	function onF4() {

	}
	/**
	 * 初期化ボタン押下
	 */
	function initForm() {
		// 入力内容を初期化してよろしいですか？
		if(confirm('<bean:message key="confirm.init" />')){
			window.location.doHref('${f:url("/master/searchCustomerRetailPrice")}');
		}
	}

	function searchCustomerRetailPrice() {
		return execSearch(createData());
	}

	/**
	 * リクエストパラメータ作成
	 */
	function createData(prev){
		//リクエストデータ作成
		paramData = new Object();
		var prev = "";
		if (prev) {
			prev = "prev_";
		}
		// 適用日
		id = "#" + prev + "applyDate";
		if($(id).val()) {
			paramData["applyDate"] = $(id).val();
		}
		// 顧客コードFrom
		id = "#" + prev + "customerCodeFrom";
		if($(id).val()) {
			paramData["customerCodeFrom"] = $(id).val();
		}
		// 顧客コードTo
		id = "#" + prev + "customerCodeTo";
		if($(id).val()) {
			paramData["customerCodeTo"] = $(id).val();
		}
		// 商品コードFrom
		id = "#" + prev + "productCodeFrom";
		if($(id).val()) {
			paramData["productCodeFrom"] = $(id).val();
		}
		// 商品コードTo
		id = "#" + prev + "productCodeTo";
		if($(id).val()) {
			paramData["productCodeTo"] = $(id).val();
		}

		// 表示件数
		id = "#" + prev + "rowCount";
		if(prev) {
			paramData["rowCount"] = $(id).attr("value");
		}
		else {
			var rowCount = $(id).get(0);
			paramData["rowCount"] = rowCount.options[ rowCount.selectedIndex ].value;
		}

		return paramData;
	}

	function execSearch(paramData) {
		if(!paramData["pageNo"]) {
			// ページの設定がなければ1ページ
			paramData["pageNo"] = 1;
		}

		// 検索実行
		asyncRequest(
			"${f:url('/ajax/master/searchCustomerRetailPriceAjax/search')}",
			paramData,
			function(data) {
				var jData = $(data);

				// 検索結果テーブルを更新する
				$("#ListContainer").empty();
				$("#ListContainer").append(data);

				// 1件以上ヒットした場合
				if($("#searchResultCount").val() != "0") {
					// 検索条件を保持
					paramDataTmp = paramData;
				}
			}
		);
	}

	-->
	</script>
</head>
<body onhelp="return false;" onload="init()">

<%-- ページヘッダ領域 --%>
<%@ include file="/WEB-INF/view/common/titlebar.jsp" %>

<%-- メニュー領域 --%>
<jsp:include page="/WEB-INF/view/common/menubar.jsp">
	<jsp:param name="PARENT_MENU_ID" value="0013"/>
	<jsp:param name="MENU_ID" value="1317"/>
</jsp:include>

<!-- メイン機能 -->
<div id="main_function">
<s:form styleId="SearchCustomerRetailPriceForm" onsubmit="return false;">
	<span class="title">顧客別単価</span>
	<div class="function_buttons">
		<button tabindex="2000" onclick="initForm()">F1<br>初期化</button>
        <button tabindex="2001" onclick="searchCustomerRetailPrice()">F2<br>検索</button>
<c:if test="${!isUpdate}">
        <button disabled="disabled">F3<br>追加</button>
</c:if>
<c:if test="${isUpdate}">
        <button tabindex="2002" onclick="return false">F3<br>追加</button>
</c:if>
<c:if test="${isUpdate}">
        <button tabindex="2003" onclick="return false">F4<br><bean:message key='words.name.excel'/></button>
</c:if>
<c:if test="${!isUpdate}">
        <button tabindex="2003" disabled="disabled">F4<br><bean:message key='words.name.excel'/></button>
</c:if>
        <button disabled="disabled">F5<br>&nbsp;</button>
        <button disabled="disabled">F6<br>&nbsp;</button>
        <button disabled="disabled">F7<br>&nbsp;</button>
        <button disabled="disabled">F8<br>&nbsp;</button>
        <button disabled="disabled">F9<br>&nbsp;</button>
        <button disabled="disabled">F10<br>&nbsp;</button>
        <button disabled="disabled">F11<br>&nbsp;</button>
        <button disabled="disabled">F12<br>&nbsp;</button>
	</div>
	<br><br><br>

	<div class="function_forms">
	<div style="padding-left: 20px">
		<html:errors/>
		<span id="ajax_errors"></span>
	</div>

    <div class="form_section_wrap">
	    <div class="form_section">
	    	<div class="section_title">
				<span>顧客ランク条件</span>
	            <button class="btn_toggle" />
			</div><!-- /.section_title -->

			<div id="search_info" class="section_body">
				<table id="user_info" class="forms" summary="顧客ランク情報" style="width: 600px">
					<tr>
						<th><div class="col_title_right">適用日</div></th>
						<td><html:text property="applyDate" styleId="applyDate" style="width: 135px; ime-mode: disabled;" styleClass="date_input" tabindex="100" />
					<tr>
						<th><div class="col_title_right">顧客コード</div></th>
						<td><html:text styleId="customerCodeFrom" property="customerCodeFrom" style="width: 100px;" tabindex="101"/>～
							<html:text styleId="customerCodeTo" property="customerCodeTo" style="width: 100px;" tabindex="102" />
						</td>
					</tr>
					<tr>
						<th><div class="col_title_right">商品コード</div></th>
						<td><html:text styleId="productCodeFrom" property="productCodeFrom" style="width: 100px;" tabindex="103"/>～
							<html:text styleId="productCodeTo" property="productCodeTo" style="width: 100px;" tabindex="104" />
						</td>
					</tr>
				</table>
			</div>
    	</div><!-- /.form_section -->
    </div><!-- /.form_section_wrap -->

	<div style="text-align: right; width: 1160px">
		<button tabindex="150" onclick="return false" class="btn_medium">初期化</button>
		<button tabindex="151" onclick="return false" class="btn_medium">検索</button>
	</div>

	<div id="ListContainer">
		<div style="width: 1010px; height: 25px;">
				<div style="position:absolute; left: 0px;">検索結果件数： 0件</div>
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
		</table>
	</div>

</s:form>
</div>
</body>

</html>