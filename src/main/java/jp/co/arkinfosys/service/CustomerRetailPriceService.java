package jp.co.arkinfosys.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.exception.OrderByNotFoundRuntimeException;

import jp.co.arkinfosys.common.Constants;
import jp.co.arkinfosys.common.StringUtil;
import jp.co.arkinfosys.dto.master.CustomerRetailPriceDto;
import jp.co.arkinfosys.entity.CustomerRank;
import jp.co.arkinfosys.entity.CustomerRetailPrice;
import jp.co.arkinfosys.service.exception.ServiceException;

/**
 * 顧客別単価のサービスクラス
 * @author K.Yoshida
 *
 */
public class CustomerRetailPriceService extends AbstractMasterEditService<CustomerRetailPriceDto, CustomerRetailPrice>
		implements MasterSearch<CustomerRetailPrice>
		{

	@Resource
	private SeqMakerService seqMakerService;

	public static class Param {
		public static final String APPLY_DATE = "applyDate";
		public static final String CUSTOMER_CODE = "customerCode";
		public static final String PRODUCT_CODE = "productCode";
		public static final String RETAIL_PRICE = "retailPrice";
		public static final String SORT_COLUMN_CUSTOMER = "sortColumnCustomer";
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
	 * @return 顧客別単価のリスト
	 * @throws ServiceException
	 */
	public List<CustomerRetailPrice> findAllCustomerRetailPrice() throws ServiceException {
		try {
			return this.selectBySqlFile(CustomerRetailPrice.class,
					"customerretailprice/FindAllCustomerRetailPrice.sql",
					super.createSqlParam()).getResultList();
		} catch (OrderByNotFoundRuntimeException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 検索条件に合致する件数を返します.
	 * @param conditions 検索条件のマップ
	 * @return 検索結果件数
	 * @throws ServiceException
	 */
	public int countByCondition(Map<String, Object> conditions)
			throws ServiceException {
		if (conditions == null) {
			return 0;
		}
		try {
			Map<String, Object> param = super.createSqlParam();
			this.setEmptyCondition(param);

			this.setCondition(conditions,  null, false, param);

			return this.selectBySqlFile(Integer.class,
					"customerretailprice/CountCustomerRetailPriceByCondition.sql", param)
					.getSingleResult().intValue();

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 *
	 * @param conditions 検索条件のマップ
	 * @param sortColumn ソートカラム名
	 * @param sortOrderAsc 昇順にソートするか否か
	 * @param rowCount 取得件数
	 * @param offset 取得開始位置
	 * @return {@link CustomerRank}のリスト
	 * @throws ServiceException
	 * @see jp.co.arkinfosys.service.MasterSearch#findByConditionLimit(java.util.Map, java.lang.String, boolean, int, int)
	 */
	@Override
	public List<CustomerRetailPrice> findByConditionLimit(
			Map<String, Object> conditions, String sortColumn,
			boolean sortOrderAsc, int rowCount, int offset)
			throws ServiceException {
		if (conditions == null) {
			return new ArrayList<CustomerRetailPrice>();
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

			return this.selectBySqlFile(CustomerRetailPrice.class,
					"customerretailprice/FindCustomerRetailPriceByConditionLimit.sql", param)
					.getResultList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 *
	 * @param conditions 検索条件のマップ
	 * @param sortColumn ソートカラム名
	 * @param sortOrderAsc 昇順にソートするか否か
	 * @return {@link CustomerRank}のリスト
	 * @throws ServiceException
	 * @see jp.co.arkinfosys.service.MasterSearch#findByCondition(java.util.Map, java.lang.String, boolean)
	 */
	public List<CustomerRetailPrice> findByCondition(Map<String, Object> conditions,
			String sortColumn, boolean sortOrderAsc) throws ServiceException {
		return new ArrayList<CustomerRetailPrice>();
	}

	/**
	 * IDを指定して、マスタ情報を取得します.
	 * @param id ID
	 * @return マスタ情報
	 * @throws ServiceException
	 */
	public CustomerRetailPrice findById(String id) throws ServiceException {
		return new CustomerRetailPrice();
	}

	/**
	 * 空の検索条件マップを作成します.
	 * @param param 検索条件マップ
	 * @return 検索条件キーのみ設定した検索条件マップ
	 */
	private Map<String, Object> setEmptyCondition(Map<String, Object> param) {
		param.put(Param.APPLY_DATE, null);
		param.put(Param.CUSTOMER_CODE, null);
		param.put(Param.PRODUCT_CODE, null);
		param.put(Param.SORT_COLUMN_CUSTOMER, null);
		param.put(Param.SORT_ORDER, null);
		return param;
	}

	/**
	 * 検索条件マップを設定します。
	 * @param conditions 検索条件値のマップ
	 * @param sortColumn ソートカラム名
	 * @param sortOrderAsc 昇順か否か
	 * @param param 検索条件マップ
	 */
	private void setCondition(Map<String, Object> conditions,
			String sortColumn, boolean sortOrderAsc, Map<String, Object> param) {
		//適用年月日
		if (conditions.containsKey(Param.APPLY_DATE)) {
			if (StringUtil.hasLength((String) conditions.get(Param.APPLY_DATE))) {
				param.put(Param.APPLY_DATE, (String) conditions
						.get(Param.APPLY_DATE));
			}
		}

		//顧客コード
		if (conditions.containsKey(Param.CUSTOMER_CODE)) {
			param.put(Param.CUSTOMER_CODE, super
					.createPartialSearchCondition((String) conditions
							.get(Param.CUSTOMER_CODE)));
		}

		//商品コード
		if (conditions.containsKey(Param.PRODUCT_CODE)) {
			param.put(Param.PRODUCT_CODE, super
					.createPartialSearchCondition((String) conditions
							.get(Param.PRODUCT_CODE)));

		}

		//ソートカラムを設定する
		param.put(Param.SORT_COLUMN_CUSTOMER, COLUMN_CUSTOMER_CODE);

		//ソートオーダーを設定する
		if (sortOrderAsc) {
			param.put(Param.SORT_ORDER, Constants.SQL.ASC);
		} else {
			param.put(Param.SORT_ORDER, Constants.SQL.DESC);
		}
	}

	/**
	 * マスタを登録します.
	 * @param dto マスタDTO
	 * @throws Exception
	 */
	public void insertRecord(CustomerRetailPriceDto dto) throws Exception {

	}

	/**
	 * マスタを更新します.
	 * @param dto マスタDTO
	 * @throws Exception
	 */
	public void updateRecord(CustomerRetailPriceDto dto) throws Exception {

	}

	/**
	 * マスタを削除します.
	 * @param dto マスタDTO
	 * @throws Exception
	 */
	public void deleteRecord(CustomerRetailPriceDto dto) throws Exception {

	}

	/**
	 *
	 * @return {APPLY_DATE,CUSTOMER_CODE,PRODUCT_CODE}
	 */
	protected String[] getKeyColumnNames() {
		return new String[] {"APPLY_DATE", "CUSTOMER_CODE", "PRODUCT_CODE"};
	}

	protected String getTableName() {
		return CustomerRetailPrice.TABLE_NAME;
	}
}
