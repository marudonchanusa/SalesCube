package jp.co.arkinfosys.action.master;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import jp.co.arkinfosys.common.Constants;
import jp.co.arkinfosys.dto.master.CustomerRetailPriceDto;
import jp.co.arkinfosys.entity.AuditInfo;
import jp.co.arkinfosys.entity.CustomerRetailPrice;
import jp.co.arkinfosys.entity.join.CustomerRetailPriceJoin;
import jp.co.arkinfosys.form.master.AbstractEditForm;
import jp.co.arkinfosys.form.master.EditCustomerRetailPriceForm;
import jp.co.arkinfosys.service.AbstractMasterEditService;
import jp.co.arkinfosys.service.CustomerRetailPriceService;
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

	public CustomerRetailPriceService customerRetailPriceService;

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
	 * 自動発番のため、使用しません.
	 * @return null
	 * @see jp.co.arkinfosys.action.master.AbstractEditAction#getAlreadyExistsErrorKey()
	 */
	@Override
	protected String getAlreadyExistsErrorKey() {
		// 自動発番なので使わない
		return null;
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
