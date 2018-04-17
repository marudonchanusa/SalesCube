/**
 *
 */
package jp.co.arkinfosys.dto.master;

/**
 * 顧客別単価マスタ情報を管理するDTOクラスです.
 *
 * @author K.Yoshida
 *
 */
public class CustomerRetailPriceDto implements MasterEditDto {

	public String customerRetailPriceId;

	public String applyDate;

	public String customerCode;

	public String customerName;

	public String productCode;

	public String productName;

	public String retailPrice;

	public String taxCategoryName;

	public String updDatetm;

	public String[] getKeys() {
		return new String[] { this.customerRetailPriceId };
	}
}
