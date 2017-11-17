SELECT
	DISTINCT
	LINE.HIST_ID,
	LINE.ACTION_TYPE,
	LINE.ACTION_FUNC,
	LINE.REC_DATETM,
	LINE.REC_USER,
	LINE.PAYMENT_LINE_ID,
	LINE.STATUS,
	LINE.PAYMENT_SLIP_ID,
	LINE.LINE_NO,
	LINE.PAYMENT_CATEGORY,
	LINE.PRODUCT_CODE,
	LINE.PRODUCT_ABSTRACT,
	LINE.SUPPLIER_DATE,
	LINE.QUANTITY,
	LINE.UNIT_PRICE,
	LINE.PRICE,
	LINE.DOL_UNIT_PRICE,
	LINE.DOL_PRICE,
	LINE.RATE,
	LINE.CTAX_RATE,
	LINE.CTAX_PRICE,
	LINE.PO_LINE_ID,
	LINE.SUPPLIER_LINE_ID,
	LINE.REMARKS,
	LINE.CRE_FUNC,
	LINE.CRE_DATETM,
	LINE.CRE_USER,
	LINE.UPD_FUNC,
	LINE.UPD_DATETM,
	LINE.UPD_USER
FROM
	PAYMENT_SLIP_TRN_HIST_/*$domainId*/ SLIP
	INNER JOIN PAYMENT_LINE_TRN_HIST_/*$domainId*/ LINE ON SLIP.PAYMENT_SLIP_ID = LINE.PAYMENT_SLIP_ID
/*BEGIN*/
WHERE
	/*IF actionType != null */
	AND SLIP.ACTION_TYPE = /*actionType*/'S'
	/*END*/
	/*IF recDateFrom != null */
	AND CAST(LINE.REC_DATETM AS DATE) >= CAST(/*recDateFrom*/'2010/01/01' AS DATE)
	/*END*/
	/*IF recDateTo != null */
	AND CAST(LINE.REC_DATETM AS DATE) <= CAST(/*recDateTo*/'2010/01/01' AS DATE)
	/*END*/
	/*IF supplierCodeFrom != null */
	AND SLIP.SUPPLIER_CODE >= /*supplierCodeFrom*/'S'
	/*END*/
	/*IF supplierCodeTo != null */
	AND SLIP.SUPPLIER_CODE <= /*supplierCodeTo*/'S'
	/*END*/
	/*IF productCodeFrom != null */
	AND LINE.PRODUCT_CODE >= /*productCodeFrom*/'S'
	/*END*/
	/*IF productCodeTo != null */
	AND LINE.PRODUCT_CODE <= /*productCodeTo*/'S'
	/*END*/
	/*IF paymentDateFrom != null */
	AND SLIP.PAYMENT_DATE >= CAST(/*paymentDateFrom*/'2010/01/01' AS DATE)
	/*END*/
	/*IF paymentDateTo != null */
	AND SLIP.PAYMENT_DATE <= CAST(/*paymentDateTo*/'2010/01/01' AS DATE)
	/*END*/
/*END*/
ORDER BY LINE.HIST_ID
