/*
 * Copyright 2018 keiichi Yoshida
 */
package jp.co.arkinfosys.action.master;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;

import jp.co.arkinfosys.action.AbstractSearchAction;
import jp.co.arkinfosys.common.Constants;
import jp.co.arkinfosys.dto.master.CustomerRetailPriceDto;
import jp.co.arkinfosys.form.AbstractSearchForm;
import jp.co.arkinfosys.form.master.SearchCustomerRetailPriceForm;
import jp.co.arkinfosys.service.exception.ServiceException;

/**
 * 顧客別単価のアクションクラスです.
 * @author K.Yoshida
 *
 */
public class SearchCustomerRetailPriceAction extends
		AbstractSearchAction<CustomerRetailPriceDto> {

	@ActionForm
	@Resource
	public SearchCustomerRetailPriceForm searchCustomerRetailPriceForm;

	/**
	 * @throws ServiceException
	 * @see jp.co.arkinfosys.action.AbstractSearchAction#createList()
	 */
	@Override
	protected void createList() throws ServiceException{
	}

	/**
	 * @return {@link SearchCustomerRetailPriceForm}
	 * @see jp.co.arkinfosys.action.AbstractSearchAction#getActionForm()
	 */
	protected AbstractSearchForm<CustomerRetailPriceDto> getActionForm(){
		return this.searchCustomerRetailPriceForm;
	}

	/**
	 * 検索画面のメニューIDを返します.
	 * @return 検索画面メニューID
	 */
	protected String getSearchMenuID() {
		return Constants.MENU_ID.MASTER_CUSTOMER_RETAIL_PRICE;
	}

}