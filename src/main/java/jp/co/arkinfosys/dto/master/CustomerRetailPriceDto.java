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

	public String applyDate;

	public String customerCode;

	public String productCode;

	public String retailPrice;

	public String[] getKeys() {
		return new String[] { this.applyDate, this.customerCode, this.productCode };
	}
}
