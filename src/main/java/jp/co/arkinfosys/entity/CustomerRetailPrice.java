package jp.co.arkinfosys.entity;

import java.io.Serializable;

public class CustomerRetailPrice extends AuditInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "CUSTOMER_RETAIL_PRICE";

	public String applyDate;

	public String customerCode;

	public String productCode;

	public String retailPrice;

}
