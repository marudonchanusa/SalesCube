package jp.co.arkinfosys.form.master;

import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.DoubleRange;
import org.seasar.struts.util.MessageResourcesUtil;

import jp.co.arkinfosys.common.Constants;

/**
 * 顧客別単価マスタ管理（登録・編集）のアクションフォームクラスです。
 * @author user
 *
 */
public class EditCustomerRetailPriceForm extends AbstractEditForm {

	/** 顧客別単価ID */
	public String customerRetailPriceId;

	/** 適用年月日 */
	@DateType(datePattern = Constants.FORMAT.DATE)
	public String applyDate;

	/** 顧客コード */
	public String customerCode;

	/** 顧客名 */
	public String customerName;

	/** 商品コード */
	public String productCode;

	/** 商品名 */
	public String productName;

	/** 単価 */
	@DoubleRange(min="-999999999", max="999999999", arg0 = @Arg(key = "labels.retailPrice2", resource = true))
	public String retailPrice;

	@Override
	public void initialize() {
		applyDate = "";
		customerCode = "";
		productCode = "";
		retailPrice = "";
	}

	/**
	 * 登録・編集時のバリデートを行います。
	 * @return
	 */
	public ActionMessages validate() {
		ActionMessages errors = new ActionMessages();

		String labelApplyDate = MessageResourcesUtil
				.getMessage("labels.applyDate");
		String labelCustomerCode = MessageResourcesUtil
				.getMessage("labels.customerCode");
		String labelProductCode = MessageResourcesUtil
				.getMessage("labels.productCode");
		String labelRetailPrice = MessageResourcesUtil
				.getMessage("labels.product.retailPrice");

		//適用年月日
		//必須チェック
		checkRequired(applyDate, labelApplyDate, errors);
		//日付チェック
		checkDate(applyDate, labelApplyDate, errors);

		//顧客コード
		checkRequired(customerCode, labelCustomerCode, errors);

		//商品コード
		checkRequired(productCode, labelCustomerCode, errors);

		//売価
		checkRequired(retailPrice, labelRetailPrice, errors);
		//数値チェック
		checkIntegerPlus(retailPrice, labelRetailPrice, errors);

		return errors;
	}
}
