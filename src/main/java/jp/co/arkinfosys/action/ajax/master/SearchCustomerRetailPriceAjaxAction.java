package jp.co.arkinfosys.action.ajax.master;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import jp.co.arkinfosys.action.ajax.AbstractSearchResultAjaxAction;
import jp.co.arkinfosys.common.Constants;
import jp.co.arkinfosys.dto.master.CustomerRetailPriceDto;
import jp.co.arkinfosys.entity.CustomerRetailPrice;
import jp.co.arkinfosys.entity.join.CustomerRetailPriceJoin;
import jp.co.arkinfosys.form.AbstractSearchForm;
import jp.co.arkinfosys.form.master.SearchCustomerRetailPriceForm;
import jp.co.arkinfosys.service.CustomerRankService;
import jp.co.arkinfosys.service.CustomerRetailPriceService;
import jp.co.arkinfosys.service.MasterSearch;


public class SearchCustomerRetailPriceAjaxAction extends
		AbstractSearchResultAjaxAction<CustomerRetailPriceDto, CustomerRetailPriceJoin> {

	@ActionForm
	@Resource
	public SearchCustomerRetailPriceForm searchCustomerRetailPriceForm;

	@Resource
	public CustomerRetailPriceService customerRetailPriceService;

	@Execute(validator = false)
	public String search() throws Exception {
		return super.doSearch();
	}

	protected List<CustomerRetailPriceDto> exchange(List<CustomerRetailPriceJoin> entityList)
			throws Exception {

		List<CustomerRetailPriceDto> dtoList = new ArrayList<CustomerRetailPriceDto>();
		for (CustomerRetailPrice entity : entityList) {
			CustomerRetailPriceDto dto = Beans.createAndCopy(this.getDtoClass(), 
					entity).timestampConverter(Constants.FORMAT.TIMESTAMP)
					.dateConverter(Constants.FORMAT.DATE).execute();
			dtoList.add(dto);
		}

		return dtoList;
	}

	/**
	 * 検索画面のアクションフォームを返します.
	 * @return {@link SearchCustomerRetailPriceForm}
	 */
	@Override
	protected AbstractSearchForm<CustomerRetailPriceDto> getActionForm() {
		return this.searchCustomerRetailPriceForm;
	}

	/**
	 * 顧客別単価DTOを返します.
	 * @return {@link CustomerRetailPriceDto}
	 */
	@Override
	protected Class<CustomerRetailPriceDto> getDtoClass() {
		return CustomerRetailPriceDto.class;
	}

	@Override
	protected String getResultURIString() {
		return Mapping.RESULT;
	}

	/**
	 * 検索処理を行う顧客別単価サービスを返します.
	 * @return {@link CustomerRankService}
	 */
	@Override
	protected MasterSearch<CustomerRetailPriceJoin> getService() {
		return this.customerRetailPriceService;
	}

	/**
	 * 検索画面のメニューIDを返します.
	 * @return 顧客ランクマスタ画面のメニューID
	 */
	@Override
	protected String getSearchMenuID() {
		return Constants.MENU_ID.MASTER_CUSTOMER_RETAIL_PRICE;
	}
}
