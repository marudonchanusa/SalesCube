package jp.co.arkinfosys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class CustomerRetailPrice extends AuditInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "CUSTOMER_RETAIL_PRICE";

	public String customerRetailPriceId;

	public Date applyDate;

	public String customerCode;

	public String productCode;

	public BigDecimal retailPrice;

}
