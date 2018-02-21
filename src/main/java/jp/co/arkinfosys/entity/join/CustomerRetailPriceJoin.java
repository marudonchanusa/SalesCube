package jp.co.arkinfosys.entity.join;

import jp.co.arkinfosys.entity.CustomerRetailPrice;


/**
 * 顧客別単価マスタと顧客マスタと商品マスタのリレーションエンティティクラスです。
 *
 * @author K.Yoshida
 *
 */
public class CustomerRetailPriceJoin extends CustomerRetailPrice {

	private static final long serialVersionUID = 1L;

	/**
	 * 顧客名
	 */
	public String customerName;

	/**
	 * 商品名
	 */
	public String productName;

	/**
	 * 課税区分
	 */
	public String taxCategoryName;
}
