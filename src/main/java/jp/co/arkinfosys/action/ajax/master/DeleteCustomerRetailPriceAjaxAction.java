package jp.co.arkinfosys.action.ajax.master;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;

import jp.co.arkinfosys.action.ajax.AbstractDeleteAjaxAction;
import jp.co.arkinfosys.dto.master.CustomerRetailPriceDto;
import jp.co.arkinfosys.entity.join.CustomerRetailPriceJoin;
import jp.co.arkinfosys.form.master.EditCustomerRetailPriceForm;
import jp.co.arkinfosys.service.AbstractMasterEditService;
import jp.co.arkinfosys.service.CustomerRetailPriceService;

/**
 * 顧客別単価（検索）の削除実行アクションクラス
 * @author K.Yoshida
 *
 */
public class DeleteCustomerRetailPriceAjaxAction
		extends AbstractDeleteAjaxAction<CustomerRetailPriceDto, CustomerRetailPriceJoin> {

	@Resource
	public CustomerRetailPriceService customerRetailPriceService;

	@ActionForm
	@Resource
	public EditCustomerRetailPriceForm editCustomerRetailPriceForm;

	/**
	 * 削除レコードを識別する情報を持った顧客ランクDTOを返します。
	 * @return
	 */
	protected CustomerRetailPriceDto getIdentifiedDto() {
		return Beans.createAndCopy(CustomerRetailPriceDto.class,
				this.editCustomerRetailPriceForm).execute();
	}

	/**
	 * 削除処理を行う顧客別単価サービスを返します。
	 */
	@Override
	protected AbstractMasterEditService<CustomerRetailPriceDto, CustomerRetailPriceJoin> getService() {
		return this.customerRetailPriceService;
	}
}
