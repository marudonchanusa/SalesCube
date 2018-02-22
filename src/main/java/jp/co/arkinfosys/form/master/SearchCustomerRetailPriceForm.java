package jp.co.arkinfosys.form.master;

import org.seasar.struts.annotation.DateType;

import jp.co.arkinfosys.dto.master.CustomerRetailPriceDto;
import jp.co.arkinfosys.form.AbstractSearchForm;

/**
 * 顧客別単価画面（検索）のアクションフォームクラスです.
 * @author K.Yoshida
 *
 */
public class SearchCustomerRetailPriceForm extends AbstractSearchForm<CustomerRetailPriceDto> {

	/** 適用日 */
	@DateType(datePatternStrict = "yyyy/MM/dd")
	public String applyDate;

	/** 顧客コードFrom */
	public String customerCodeFrom;

	/** 顧客コードTo */
	public String customerCodeTo;

	/** 商品コードFrom */
	public String productCodeFrom;

	/** 商品コードTo */
	public String productCodeTo;

}
