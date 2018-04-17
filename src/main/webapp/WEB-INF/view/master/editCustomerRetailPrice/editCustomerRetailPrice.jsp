<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ja">
<head>
	<title><bean:message key='titles.system'/>　顧客別単価管理(登録・編集)</title>

	<%@ include file="/WEB-INF/view/common/header.jsp" %>

	<script type="text/javascript">
	<!--

	$(function() {
		//applyPriceAlignment();
		//applyQuantityAlignment($(".BDCqua"));
		//applyStatsAlignment($(".BDCrate"));
		//applyCUnitSign();
	});

    // ファンクションキーとのマッピング
    function onF1() { initForm(); }
    function onF2() { backToSearch(); }
    function onF3() { registerCustomerRetailPrice(); }
    function onF4() { deleteCustomerRetailPrice(); }

	function initForm() {
		if(confirm("<bean:message key='confirm.init'/>")){
			location.doHref(contextRoot + "/master/editCustomerRetailPrice");
		}
	}

    function backToSearch() {
    	if(confirm("<bean:message key='confirm.master.customerretailprice.back'/>")){
            location.doHref(contextRoot + "/master/searchCustomerRetailPrice/");
        }
    }

    function registerCustomerRetailPrice() {
        <c:if test="${!editMode}">
    	if(confirm("<bean:message key='confirm.insert'/>")){
        	$("#editCustomerRetailPriceForm").attr("action", "${f:url("/master/editCustomerRetailPrice/insert")}");
        </c:if>
        <c:if test="${editMode}">
    	if(confirm("<bean:message key='confirm.update'/>")){
        	$("#editCustomerRetailPriceForm").attr("action", "${f:url("/master/editCustomerRetailPrice/update")}");
        </c:if>
       		_before_submit($(".numeral_commas"));
        	$("#editCustomerRetailPriceForm").trigger("submit");
        }
    }

    function deleteCustomerRetailPrice() {
    	if(confirm("<bean:message key='confirm.delete'/>")){
        	$("#editCustomerRetailPriceForm").attr("action", "${f:url("/master/editCustomerRetailPrice/delete")}");
    		_before_submit($(".numeral_commas"));
        	$("#editCustomerRetailPriceForm").trigger("submit");
        }
    }

	/**
	 * 数量小数桁処理と端数処理を適用する
	 */
	function applyQuantityAlignment(jQueryObject) {
		if(jQueryObject != null) {
			jQueryObject.setBDCStyle( ${mineDto.productFractCategory} ,${mineDto.numDecAlignment} ).attBDC();
		}
	}

	// 単価小数桁処理と端数処理を適用する
	function applyPriceAlignment() {
		// 円単価
		$(".BDCyen").setBDCStyle( $("#priceFractCategory").val() ,$("#unitPriceDecAlignment").val() ).attBDC();
		// 外貨単価
		$(".BDCdol").setBDCStyle( $("#priceFractCategory").val() ,$("#dolUnitPriceDecAlignment").val() ).attBDC();
	}

	// 統計端数処理を適用する
	function applyStatsAlignment(jQueryObject) {
		if(jQueryObject != null) {
			// 四捨五入
			jQueryObject.setBDCStyle( "1" ,$("#statsDecAlignment").val() ).attBDC();
		}
	}

	// 外貨記号リストを作成して仕入先の外貨記号をセットする(記号の付与・除去に必要)
	function applyCUnitSign(){
		// レートマスタの全ての外貨記号リストを取得する
		var data = new Object();
		asyncRequest(
			contextRoot + "/ajax/commonRate/getAllRateSign",
			data,
			function(data) {
				if(data==""){
					alert('<bean:message key="errors.notExist" arg0="レート情報"/>');
					return;
				}
				var values = eval("(" + data + ")");

				// レート記号を設定
				for(var key in values){
					CurrencyUnitClassNameHashList[key] = values[key];
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
<s:form styleId="editCustomerRetailPriceForm" onsubmit="return false;">
<input type="hidden" id="updatable" name="updatable" value="${isUpdate}">
<div id="main_function">

	<span class="title">顧客ランク</span>

	<div class="function_buttons">
		<button tabindex="2000" onclick="initForm()"> F1<br>初期化</button>
		<button tabindex="2001" onclick="backToSearch()">F2<br>戻る</button>
		<c:if test="${!isUpdate}">
				<button tabindex="2002" disabled="disabled">F3<br>更新</button>
		</c:if>
		<c:if test="${isUpdate}">
			<c:if test="${editMode}">
				<button tabindex="2002" onclick="registerCustomerRetailPrice()">F3<br>更新</button>
		    </c:if>
			<c:if test="${!editMode}">
				<button tabindex="2002" onclick="registerCustomerRetailPrice()">F3<br>登録</button>
		    </c:if>
		</c:if>
		<c:if test="${!isUpdate}">
				<button tabindex="2003" disabled="disabled">F4<br>削除</button>
		</c:if>
		<c:if test="${isUpdate}">
			<c:if test="${editMode}">
				<button tabindex="2003" onclick="deleteCustomerRetailPrice()">F4<br>削除</button>
		    </c:if>
			<c:if test="${!editMode}">
				<button tabindex="2003" disabled="disabled">F4<br>削除</button>
		    </c:if>
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
    	<div style="padding-left: 20px"><html:errors/></div>
    	<div style="padding-left: 20px;color: blue;">
        	<html:messages id="msg" message="true">
        		<bean:write name="msg" ignore="true"/><br>
        	</html:messages>
    	</div>

	    <div class="form_section_wrap">
		    <div class="form_section">
		    	<div class="section_title">
					<span>顧客別単価情報</span>
		            <button class="btn_toggle" />
				</div><!-- /.section_title -->

				<div id="order_section" class="section_body">
					<table id="user_info" class="forms" summary="顧客別単価情報" style="width: 600px">
						<tr>
							<th><div class="col_title_right">適用日</div></th>
							<td><html:text property="applyDate" styleId="applyDate" style="width: 135px; ime-mode: disabled;" styleClass="date_input" tabindex="100" /></td>
						</tr>
						<tr>
							<th><div class="col_title_right">顧客コード</div></th>
							<td><html:text styleId="customerCode" property="customerCode" style="width: 100px"  tabindex="101"
								onfocus="this.curVal=this.value;" onblur="if(this.curVal!=this.value){ changeCustomerCode(this); }"/>
								<html:image src="${f:url('/images//customize/btn_search.png')}"
									style="vertical-align: middle; cursor: pointer;" tabindex="102"
									onclick="customerSearch()" />
							</td>
							<th><div class="col_title_right">顧客名</div></th>
							<td>
								<html:text property="customerName" styleId="customerName" style="width: 500px" readonly="true" styleClass="c_disable" maxlength="60" tabindex="103" />
							</td>
						</tr>
						<tr>
							<th><div class="col_title_right">商品コード</div></th>
							<td><html:text styleId="productCode" property="productCode" style="width: 100px"  tabindex="104"
								onfocus="this.curVal=this.value;" onblur="if(this.curVal!=this.value){ changeProductCode(this); }"/>
								<html:image src="${f:url('/images//customize/btn_search.png')}"
									style="vertical-align: middle; cursor: pointer;" tabindex="105"
									onclick="productSearch()" />
							</td>
							<th><div class="col_title_right">商品名</div></th>
							<td>
								<html:text property="productName" styleId="productName" style="width: 500px;" readonly="true" styleClass="c_disable" maxlength="60" tabindex="106" />
							</td>
						</tr>
						<tr>
							<th><div class="col_title_right">単価</div>
							<td><html:text styleClass="numeral_commas yen_value BDCyen" styleId="retailPrice" property="retailPrice"
									style="width: 150px;" tabindex="107"/></td>
						</tr>
					</table>
				</div><!-- /.section_body -->
			</div><!-- /.form_section -->
		</div><!-- /.form_section_wrap -->

		<div style="text-align: right; width: 1160px">
			<span>登録日：${creDatetmShow}<html:hidden property="creDatetmShow"/>　更新日:${updDatetmShow}<html:hidden property="updDatetmShow"/>　</span>
			<button tabindex="800" onclick="initForm()" class="btn_medium">初期化</button>
			<c:if test="${!isUpdate}">
            	<button tabindex="801" disabled="disabled">更新</button>
			</c:if>
			<c:if test="${isUpdate}">
				<c:if test="${editMode}">
            		<button tabindex="801" onclick="registerCustomerRetailPrice()" class="btn_medium">更新</button>
    			</c:if>
				<c:if test="${!editMode}">
            		<button tabindex="801" onclick="registerCustomerRetailPrice()" class="btn_medium">登録</button>
    			</c:if>
			</c:if>
			<c:if test="${!isUpdate}">
				<button tabindex="802" disabled="disabled" class="btn_medium">削除</button>
			</c:if>
			<c:if test="${isUpdate}">
				<c:if test="${editMode}">
					<button tabindex="802" onclick="deleteCustomerRetailPrice()" class="btn_medium">削除</button>
    			</c:if>
				<c:if test="${!editMode}">
					<button tabindex="802" disabled="disabled" class="btn_medium">削除</button>
    			</c:if>
			</c:if>
		</div>
	</div>
</div>
<html:hidden property="customerRetailPriceId"/>
<html:hidden property="updDatetm"/>
</s:form>

</body>

</html>