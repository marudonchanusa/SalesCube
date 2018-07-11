package jp.co.arkinfosys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.exception.OrderByNotFoundRuntimeException;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.beans.util.Beans;

import jp.co.arkinfosys.common.Constants;
import jp.co.arkinfosys.common.StringUtil;
import jp.co.arkinfosys.dto.master.CustomerRetailPriceDto;
import jp.co.arkinfosys.entity.CustomerRank;
import jp.co.arkinfosys.entity.CustomerRetailPrice;
import jp.co.arkinfosys.entity.join.CustomerRetailPriceJoin;
import jp.co.arkinfosys.service.exception.ServiceException;
import jp.co.arkinfosys.service.exception.UnabledLockException;

/**
 * 顧客別単価のサービスクラス
 *
 * @author K.Yoshida
 *
 */
public class CustomerRetailPriceService
		extends AbstractMasterEditService<CustomerRetailPriceDto, CustomerRetailPriceJoin>
		implements MasterSearch<CustomerRetailPriceJoin> {

	@Resource
	private SeqMakerService seqMakerService;

	public static class Param {
		public static final String CUSTOMER_RETAIL_PRICE_ID = "customerRetailPriceId";
		public static final String APPLY_DATE = "applyDate";
		public static final String CUSTOMER_CODE = "customerCode";
		public static final String CUSTOMER_CODE_FROM = "customerCodeFrom";
		public static final String CUSTOMER_CODE_TO = "customerCodeTo";
		public static final String PRODUCT_CODE = "productCode";
		public static final String PRODUCT_CODE_FROM = "productCodeFrom";
		public static final String PRODUCT_CODE_TO = "productCodeTo";
		public static final String SORT_COLUMN_CUSTOMER_RETAIL_PRICE = "sortColumnCustomerRetailPrice";
		public static final String SORT_ORDER = "sortOrder";
		public static final String ROW_COUNT = "rowCount";
		public static final String OFFSET = "offsetRow";
	}

	private static final String COLUMN_APPLY_DATE = "APPLY_DATE";
	private static final String COLUMN_CUSTOMER_CODE = "CUSTOMER_CODE";
	private static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
	private static final String COLUMN_PRODUCT_CODE = "PRODUCT_CODE";
	private static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
	private static final String COLUMN_RETAIL_PRICE = "RETAIL_PRICE";

	/**
	 * 全ての顧客別単価を返します。
	 *
	 * @return 顧客別単価のリスト
	 * @throws ServiceException
	 */
	public List<CustomerRetailPrice> findAllCustomerRetailPrice() throws ServiceException {
		try {
			return this.selectBySqlFile(CustomerRetailPrice.class, "customerretailprice/FindAllCustomerRetailPrice.sql",
					super.createSqlParam()).getResultList();
		} catch (OrderByNotFoundRuntimeException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 検索条件に合致する件数を返します.
	 *
	 * @param conditions
	 *            検索条件のマップ
	 * @return 検索結果件数
	 * @throws ServiceException
	 */
	public int countByCondition(Map<String, Object> conditions) throws ServiceException {
		if (conditions == null) {
			return 0;
		}
		try {
			Map<String, Object> param = super.createSqlParam();
			this.setEmptyCondition(param);

			this.setCondition(conditions, null, false, param);

			return this.selectBySqlFile(Integer.class, "customerretailprice/CountCustomerRetailPriceByCondition.sql",
					param).getSingleResult().intValue();

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 *
	 * @param conditions
	 *            検索条件のマップ
	 * @param sortColumn
	 *            ソートカラム名
	 * @param sortOrderAsc
	 *            昇順にソートするか否か
	 * @param rowCount
	 *            取得件数
	 * @param offset
	 *            取得開始位置
	 * @return {@link CustomerRank}のリスト
	 * @throws ServiceException
	 * @see jp.co.arkinfosys.service.MasterSearch#findByConditionLimit(java.util.Map,
	 *      java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CustomerRetailPriceJoin> findByConditionLimit(Map<String, Object> conditions, String sortColumn,
			boolean sortOrderAsc, int rowCount, int offset) throws ServiceException {
		if (conditions == null) {
			return new ArrayList<CustomerRetailPriceJoin>();
		}
		try {
			Map<String, Object> param = super.createSqlParam();
			this.setEmptyCondition(param);

			this.setCondition(conditions, sortColumn, sortOrderAsc, param);

			// LIMITを設定する
			if (rowCount > 0) {
				param.put(Param.ROW_COUNT, rowCount);
				param.put(Param.OFFSET, offset);
			}

			return this.selectBySqlFile(CustomerRetailPriceJoin.class,
					"customerretailprice/FindCustomerRetailPriceByConditionLimit.sql", param).getResultList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 空の検索条件マップを作成します.
	 *
	 * @param param
	 *            検索条件マップ
	 * @return 検索条件キーのみ設定した検索条件マップ
	 */
	private Map<String, Object> setEmptyCondition(Map<String, Object> param) {
		param.put(Param.APPLY_DATE, null);
		param.put(Param.CUSTOMER_CODE, null);
		param.put(Param.CUSTOMER_CODE_FROM, null);
		param.put(Param.CUSTOMER_CODE_TO, null);
		param.put(Param.PRODUCT_CODE, null);
		param.put(Param.PRODUCT_CODE_FROM, null);
		param.put(Param.PRODUCT_CODE_TO, null);
		param.put(Param.SORT_COLUMN_CUSTOMER_RETAIL_PRICE, null);
		param.put(Param.SORT_ORDER, null);
		return param;
	}

	/**
	 * 検索条件マップを設定します。
	 *
	 * @param conditions
	 *            検索条件値のマップ
	 * @param sortColumn
	 *            ソートカラム名
	 * @param sortOrderAsc
	 *            昇順か否か
	 * @param param
	 *            検索条件マップ
	 */
	private void setCondition(Map<String, Object> conditions, String sortColumn, boolean sortOrderAsc,
			Map<String, Object> param) {
		// 適用年月日
		if (conditions.containsKey(Param.APPLY_DATE)) {
			if (StringUtil.hasLength((String) conditions.get(Param.APPLY_DATE))) {
				param.put(Param.APPLY_DATE, (String) conditions.get(Param.APPLY_DATE));
			}
		}

		// 顧客コード
		if (conditions.containsKey(Param.CUSTOMER_CODE)) {
			param.put(Param.APPLY_DATE, (String) conditions.get(Param.CUSTOMER_CODE));
		}

		// 顧客コードFrom
		if (conditions.containsKey(Param.CUSTOMER_CODE_FROM)) {
			param.put(Param.CUSTOMER_CODE_FROM, (String) conditions.get(Param.CUSTOMER_CODE_FROM));

		}

		// 顧客コードTo
		if (conditions.containsKey(Param.CUSTOMER_CODE_TO)) {
			param.put(Param.CUSTOMER_CODE_TO, (String) conditions.get(Param.CUSTOMER_CODE_TO));
		}

		// 商品コード
		if (conditions.containsKey(Param.PRODUCT_CODE)) {
			param.put(Param.PRODUCT_CODE, (String) conditions.get(Param.PRODUCT_CODE));
		}

		// 商品コードFrom
		if (conditions.containsKey(Param.PRODUCT_CODE_FROM)) {
			param.put(Param.PRODUCT_CODE_FROM, (String) conditions.get(Param.PRODUCT_CODE_FROM));

		}

		// 商品コードTo
		if (conditions.containsKey(Param.PRODUCT_CODE_TO)) {
			param.put(Param.PRODUCT_CODE_TO, (String) conditions.get(Param.PRODUCT_CODE_TO));

		}

		// ソートカラムを設定する
		param.put(Param.SORT_COLUMN_CUSTOMER_RETAIL_PRICE, COLUMN_APPLY_DATE);

		// ソートオーダーを設定する
		if (sortOrderAsc) {
			param.put(Param.SORT_ORDER, Constants.SQL.ASC);
		} else {
			param.put(Param.SORT_ORDER, Constants.SQL.DESC);
		}
	}

	/**
	 * マスタを登録します.
	 *
	 * @param dto
	 *            マスタDTO
	 * @throws Exception
	 */
	@Override
	public void insertRecord(CustomerRetailPriceDto dto) throws Exception {
		if (dto == null) {
			return;
		}
		try {
			// 顧客別単価の登録
			Map<String, Object> param = super.createSqlParam();

			long newCustomerRetailPriceId = seqMakerService.nextval(CustomerRetailPrice.TABLE_NAME);
			dto.customerRetailPriceId = String.valueOf(newCustomerRetailPriceId);

			// データ調整(必要無し)

			BeanMap customerRetailPriceInfo = Beans.createAndCopy(BeanMap.class, dto)
					.timestampConverter(Constants.FORMAT.TIMESTAMP).dateConverter(Constants.FORMAT.DATE).execute();

			param.putAll(customerRetailPriceInfo);
			this.updateBySqlFile("customerretailprice/InsertCustomerRetailPrice.sql", param).execute();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * マスタを更新します.
	 *
	 * @param dto
	 *            マスタDTO
	 * @throws Exception
	 */
	public void updateRecord(CustomerRetailPriceDto dto) throws Exception {
		if (dto == null) {
			return;
		}

		// 排他制御
		Map<String, Object> lockParam = createSqlParam();
		lockParam.put(Param.CUSTOMER_RETAIL_PRICE_ID, dto.customerRetailPriceId);

		// 排他制御エラー時は例外を発生する
		lockRecordBySqlFile("customerretailprice/LockCustomerRetailPrice.sql", lockParam, dto.updDatetm);

		// 顧客別単価の更新
		Map<String, Object> param = super.createSqlParam();
		BeanMap customerRetailPriceInfo = Beans.createAndCopy(BeanMap.class, dto)
				.timestampConverter(Constants.FORMAT.TIMESTAMP).dateConverter(Constants.FORMAT.DATE).execute();

		param.putAll(customerRetailPriceInfo);
		this.updateBySqlFile("customerretailprice/UpdateCustomerRetailPrice.sql", param).execute();
	}

	/**
	 * マスタを削除します.
	 *
	 * @param dto
	 *            マスタDTO
	 * @throws Exception
	 */
	public void deleteRecord(CustomerRetailPriceDto dto) throws Exception {
		try {
			// 排他制御
			Map<String, Object> param = super.createSqlParam();
			param.put(Param.CUSTOMER_RETAIL_PRICE_ID, dto.customerRetailPriceId);
			this.lockRecordBySqlFile("customerretailprice/LockCustomerRetailPrice.sql", param, dto.updDatetm);

			// 削除
			param = super.createSqlParam();
			param.put(Param.CUSTOMER_RETAIL_PRICE_ID, dto.customerRetailPriceId);
			this.updateBySqlFile("customerretailprice/DeleteCustomerRetailPrice.sql", param).execute();
		} catch (UnabledLockException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public CustomerRetailPriceJoin findById(String customerRetailPriceId) throws ServiceException {
		Map<String, Object> param = super.createSqlParam();

		param.put(Param.CUSTOMER_RETAIL_PRICE_ID, customerRetailPriceId);
		CustomerRetailPriceJoin result = this.selectBySqlFile(CustomerRetailPriceJoin.class,
				"customerretailprice/FindCustomerRetailPriceById.sql", param).getSingleResult();
		return result;
	}

	/**
	 *
	 * @return {customerRetailPriceId}
	 */
	protected String[] getKeyColumnNames() {
		return new String[] { "CUSTOMER_RETAIL_PRICE_ID" };
	}

	protected String getTableName() {
		return CustomerRetailPrice.TABLE_NAME;
	}

	/**
	 *検索条件で顧客別単価を検索
	 */
	public List<CustomerRetailPriceJoin> findByCondition(Map<String, Object> conditions, String sortColumn,
			boolean sortOrderAsc) throws ServiceException {

		if (conditions == null) {
			return new ArrayList<CustomerRetailPriceJoin>();
		}
		try {
			Map<String, Object> param = super.createSqlParam();
			this.setEmptyCondition(param);

			this.setCondition(conditions, "", true, param);

			return this.selectBySqlFile(CustomerRetailPriceJoin.class,
					"customerretailprice/FindCustomerRetailPriceJoinByCondition.sql", conditions).getResultList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
