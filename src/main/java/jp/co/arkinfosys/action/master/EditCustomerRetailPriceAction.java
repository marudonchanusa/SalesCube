package jp.co.arkinfosys.action.master;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ActionMessagesUtil;

import jp.co.arkinfosys.common.Constants;
import jp.co.arkinfosys.dto.master.CustomerRetailPriceDto;
import jp.co.arkinfosys.entity.AuditInfo;
import jp.co.arkinfosys.entity.Customer;
import jp.co.arkinfosys.entity.CustomerRetailPrice;
import jp.co.arkinfosys.entity.Product;
import jp.co.arkinfosys.entity.join.CustomerRetailPriceJoin;
import jp.co.arkinfosys.form.master.AbstractEditForm;
import jp.co.arkinfosys.form.master.EditCustomerRetailPriceForm;
import jp.co.arkinfosys.service.AbstractMasterEditService;
import jp.co.arkinfosys.service.CustomerRetailPriceService;
import jp.co.arkinfosys.service.CustomerService;
import jp.co.arkinfosys.service.ProductService;
import jp.co.arkinfosys.service.exception.ServiceException;

/**
 * 顧客別単価マスタ編集画面のアクションクラス
 * @author K.Yoshida
 *
 */
public class EditCustomerRetailPriceAction extends
		AbstractEditAction<CustomerRetailPriceDto, CustomerRetailPriceJoin> {

	private static class Mapping {
		public static final String INPUT = "editCustomerRetailPrice.jsp";
	}

	@ActionForm
	@Resource
	public EditCustomerRetailPriceForm editCustomerRetailPriceForm;

	@Resource
	public CustomerRetailPriceService customerRetailPriceService;

	@Resource
	public CustomerService customerService;

	@Resource
	public ProductService productService;

	/**
	 * 新規登録時の初期化処理を行います。
	 * 処理終了後、{@link EditCustomerRetailPriceAction#getInputURL()}で取得したURIに遷移します.
	 * @return 画面遷移先のURL文字列
	 * @throws Exception
	 */
	@Execute(validator = false)
	public String index() throws Exception {
		super.init(null);
		initList();
		return getInputURL();
	}

	/**
	 * 編集モード時の初期化処理を行います。
	 * 処理終了後、{@link EditCustomerRetailPriceAction#doEdit(String) doEdit()}で取得したURIに遷移します。
	 * @return 画面遷移先のURI文字列
	 * @throws Exception
	 */
	@Execute(validator = false, urlPattern = "edit/{customerRetailPriceId}")
	public String edit() throws Exception {
		return doEdit(getKey());
	}

	/**
	 * 登録処理を行います。
	 * 処理終了後、{@link EditCustomerRetailPriceAction#doInsert()}で取得したURIに遷移します。
	 * @return 画面遷移先のURI文字列
	 * @throws Exception
	 */
	@Execute(validator = true, validate = "validate", input = "index", stopOnValidationError = false)
	public String insert() throws Exception {
		initList();
		if(this.editCustomerRetailPriceForm.customerCode != "") {
			//存在しない顧客コードの場合エラーとする
			Customer customer = this.customerService.findById(this.editCustomerRetailPriceForm.customerCode);
			if (customer == null) {
				super.messages.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("errors.customer.not.exist.code"));
			}
		}
		if (this.editCustomerRetailPriceForm.productCode != "") {
			//存在しない商品コードの場合エラーとする
			Product product = this.productService.findById(this.editCustomerRetailPriceForm.productCode);
			if (product == null) {
				super.messages.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("errors.dispProductPrice.none.productCode"));
			}
		}
		// 検索条件を取得
		BeanMap params = Beans.createAndCopy(BeanMap.class, getActionForm())
				.excludesWhitespace().lrTrim().execute();

		if (this.customerRetailPriceService.findByCondition(params, "", true).size() > 0) {
			//既に同適用年月日、顧客コード、商品コードの単価が存在する場合エラーとする
			super.messages.add(ActionMessages.GLOBAL_MESSAGE,
					new ActionMessage("errors.customerretailprice.already.exists"));
		}

		if (super.messages.size() > 0) {
			ActionMessagesUtil.addErrors(super.httpRequest, super.messages);
			return EditCustomerRetailPriceAction.Mapping.INPUT;
		}

		return doInsert();
	}

	/**
	 * 更新処理を行います。
	 * 処理終了後、{@link EditCustomerRetailPriceAction#doUpdate()}で取得したURIに遷移します.
	 * @return
	 * @throws Exception
	 */
	@Execute(validator = true, validate = "validate", input = "initEdit", stopOnValidationError = false)
	public String update() throws Exception {
		initList();
		if(this.editCustomerRetailPriceForm.customerCode != "") {
			//存在しない顧客コードの場合エラーとする
			Customer customer = this.customerService.findById(this.editCustomerRetailPriceForm.customerCode);
			if (customer == null) {
				super.messages.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("errors.customer.not.exist.code"));
			}
		}
		if (this.editCustomerRetailPriceForm.productCode != "") {
			//存在しない商品コードの場合エラーとする
			Product product = this.productService.findById(this.editCustomerRetailPriceForm.productCode);
			if (product == null) {
				super.messages.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("errors.dispProductPrice.none.productCode"));
			}
		}

		if (super.messages.size() > 0) {
			ActionMessagesUtil.addErrors(super.httpRequest, super.messages);
			this.editCustomerRetailPriceForm.editMode = true;
			return EditCustomerRetailPriceAction.Mapping.INPUT;
		}

		return doUpdate();
	}

	/**
	 * 削除処理を行います.<br>
	 * 処理終了後、{@link EditCustomerRetailPriceAction#doDelete()}で取得したURIに遷移します.
	 * @return 画面遷移先のURI文字列
	 * @throws Exception
	 */
	@Execute(validator = false)
	public String delete() throws Exception {
		return doDelete();

	}

	/**
	 * バリデートでエラーになった際の初期化処理を行います.<br>
	 * 処理実行後、{@link EditCustomerRankAction#getInputURL()}で取得したURIに遷移します.
	 * @return 画面遷移先のURI文字列
	 * @throws ServiceException
	 */
	@Execute(validator = false)
	public String initEdit() throws ServiceException {
		this.editCustomerRetailPriceForm.editMode = true;
		initList();
		return getInputURL();
	}

	/**
	 *
	 * @return {@link EditCustomerRetailPricekForm}
	 * @see jp.co.arkinfosys.action.master.AbstractEditAction#getActionForm()
	 */
	@Override
	protected AbstractEditForm getActionForm() {
		return this.editCustomerRetailPriceForm;
	}

	/**
	 * 自動キー発行なので使わない
	 * @return null
	 * @see jp.co.arkinfosys.action.master.AbstractEditAction#getAlreadyExistsErrorKey()
	 */
	@Override
	protected String getAlreadyExistsErrorKey() {
		// 自動発番なので使わない
		return null;
	}

	/**
	 * 同じ適用年月日、顧客コード、商品コードの顧客別単価があるかチェック
	 * @return
	 */
	private List getAlreadyExists() {
		// 検索条件を取得
		BeanMap params = Beans.createAndCopy(BeanMap.class, getActionForm())
				.excludesWhitespace().lrTrim().execute();

		return this.customerRetailPriceService.findByCondition(params);
	}

	/**
	 *
	 * @return {@link CustomerRetailPriceDto}
	 * @see jp.co.arkinfosys.action.master.AbstractEditAction#getDtoClass()
	 */
	@Override
	protected Class<CustomerRetailPriceDto> getDtoClass() {
		return CustomerRetailPriceDto.class;
	}

	/**
	 *
	 * @return {@link Mapping#INPUT}で定義されたURI文字列
	 * @see jp.co.arkinfosys.action.master.AbstractEditAction#getInputURL()
	 */
	@Override
	protected String getInputURL() {
		return Mapping.INPUT;
	}

	/**
	 *
	 * @return 顧客別単価ID
	 * @see jp.co.arkinfosys.action.master.AbstractEditAction#getKey()
	 */
	@Override
	protected String getKey() {
		return this.editCustomerRetailPriceForm.customerRetailPriceId;
	}

	@Override
	protected AuditInfo loadData(String key) throws ServiceException {
		CustomerRetailPrice result = this.customerRetailPriceService.findById(key);
		return result;
	}

	/**
	 *
	 * @param record {@link Customer}
	 * @throws ServiceException
	 * @see jp.co.arkinfosys.action.master.AbstractEditAction#setForm(jp.co.arkinfosys.entity.AuditInfo)
	 */
	@Override
	protected void setForm(AuditInfo record) throws ServiceException {
		super.setForm(record);

		//端数処理
		this.editCustomerRetailPriceForm.priceFractCategory = super.mineDto.priceFractCategory;

		//単価少数桁
		this.editCustomerRetailPriceForm.unitPriceDecAlignment = "0";

		//外貨少数桁
		this.editCustomerRetailPriceForm.dolUnitPriceDecAlignment = String
				.valueOf(super.mineDto.unitPriceDecAlignment);
	}

	@Override
	protected String getMenuId() {
		// TODO 自動生成されたメソッド・スタブ
		return Constants.MENU_ID.MASTER_CUSTOMER_RETAIL_PRICE;
	}

	@Override
	protected AbstractMasterEditService<CustomerRetailPriceDto, CustomerRetailPriceJoin> getService() {
		return this.customerRetailPriceService;
	}

}
