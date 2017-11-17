SELECT
	SL.SALES_LINE_ID
	,SL.STATUS
	,SL.SALES_SLIP_ID
	,SL.LINE_NO
	,SL.RO_LINE_ID
	,SL.SALES_DETAIL_CATEGORY
	,SL.PRODUCT_CODE
	,SL.CUSTOMER_PCODE
	,SL.PRODUCT_ABSTRACT
	,SL.QUANTITY
	,SL.DELIVERY_PROCESS_CATEGORY
	,SL.UNIT_PRICE
	,SL.UNIT_CATEGORY
	,SL.UNIT_NAME
	,SL.PACK_QUANTITY
	,SL.UNIT_RETAIL_PRICE
	,SL.RETAIL_PRICE
	,SL.UNIT_COST
	,SL.COST
	,SL.TAX_CATEGORY
	,SL.CTAX_RATE
	,SL.CTAX_PRICE
	,SL.GM
	,SL.REMARKS
	,SL.EAD_REMARKS
	,SL.PRODUCT_REMARKS
	,SL.RACK_CODE_SRC
	,SL.CRE_FUNC
	,SL.CRE_DATETM
	,SL.CRE_USER
	,SL.UPD_FUNC
	,SL.UPD_DATETM
	,SL.UPD_USER
	,S.SALES_DATE
FROM
    SALES_SLIP_TRN_/*$domainId*/ S
	LEFT OUTER JOIN SALES_LINE_TRN_/*$domainId*/ SL
		ON S.SALES_SLIP_ID = SL.SALES_SLIP_ID
WHERE
	S.BILL_ID = /*billId*/0
ORDER BY
	SL.SALES_SLIP_ID ASC
	,SL.LINE_NO ASC
