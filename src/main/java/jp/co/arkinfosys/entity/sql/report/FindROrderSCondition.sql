SELECT
	DISTINCT
	SLIP.HIST_ID,
	SLIP.ACTION_TYPE,
	SLIP.ACTION_FUNC,
	SLIP.REC_DATETM,
	SLIP.REC_USER,
	SLIP.RO_SLIP_ID,
	SLIP.STATUS,
	SLIP.RO_ANNUAL,
	SLIP.RO_MONTHLY,
	SLIP.RO_YM,
	SLIP.RO_DATE,
	SLIP.SHIP_DATE,
	SLIP.DELIVERY_DATE,
	SLIP.RECEPT_NO,
	SLIP.CUSTOMER_SLIP_NO,
	SLIP.SALES_CM_CATEGORY,
	SLIP.CUTOFF_GROUP,
	SLIP.PAYBACK_CYCLE_CATEGORY,
	SLIP.USER_ID,
	SLIP.USER_NAME,
	SLIP.REMARKS,
	SLIP.CUSTOMER_CODE,
	SLIP.CUSTOMER_NAME,
	SLIP.CUSTOMER_REMARKS,
	SLIP.CUSTOMER_COMMENT_DATA,
	SLIP.DELIVERY_CODE,
	SLIP.DELIVERY_NAME,
	SLIP.DELIVERY_KANA,
	SLIP.DELIVERY_OFFICE_NAME,
	SLIP.DELIVERY_OFFICE_KANA,
	SLIP.DELIVERY_DEPT_NAME,
	SLIP.DELIVERY_ZIP_CODE,
	SLIP.DELIVERY_ADDRESS_1,
	SLIP.DELIVERY_ADDRESS_2,
	SLIP.DELIVERY_PC_NAME,
	SLIP.DELIVERY_PC_KANA,
	SLIP.DELIVERY_PC_PRE_CATEGORY,
	SLIP.DELIVERY_PC_PRE,
	SLIP.DELIVERY_TEL,
	SLIP.DELIVERY_FAX,
	SLIP.DELIVERY_EMAIL,
	SLIP.DELIVERY_URL,
	SLIP.ESTIMATE_SHEET_ID,
	SLIP.TAX_SHIFT_CATEGORY,
	SLIP.TAX_FRACT_CATEGORY,
	SLIP.PRICE_FRACT_CATEGORY,
	SLIP.CTAX_PRICE_TOTAL,
	SLIP.COST_TOTAL,
	SLIP.RETAIL_PRICE_TOTAL,
	SLIP.PRICE_TOTAL,
	SLIP.PRINT_COUNT,
	SLIP.COD_SC,
    SLIP.DC_CATEGORY,
    SLIP.DC_NAME,
    SLIP.DC_TIMEZONE_CATEGORY,
    SLIP.DC_TIMEZONE,
	SLIP.CRE_FUNC,
	SLIP.CRE_DATETM,
	SLIP.CRE_USER,
	SLIP.UPD_FUNC,
	SLIP.UPD_DATETM,
	SLIP.UPD_USER
FROM
	RO_SLIP_TRN_HIST_/*$domainId*/ SLIP
	INNER JOIN RO_LINE_TRN_HIST_/*$domainId*/ LINE ON SLIP.RO_SLIP_ID = LINE.RO_SLIP_ID
/*BEGIN*/
WHERE
	/*IF actionType != null */
	AND SLIP.ACTION_TYPE = /*actionType*/'S'
	/*END*/
	/*IF recDateFrom != null */
	AND CAST(SLIP.REC_DATETM AS DATE) >= CAST(/*recDateFrom*/'2010/01/01' AS DATE)
	/*END*/
	/*IF recDateTo != null */
	AND CAST(SLIP.REC_DATETM AS DATE) <= CAST(/*recDateTo*/'2010/01/01' AS DATE)
	/*END*/
	/*IF customerCodeFrom != null */
	AND SLIP.CUSTOMER_CODE >= /*customerCodeFrom*/'S'
	/*END*/
	/*IF customerCodeTo != null */
	AND SLIP.CUSTOMER_CODE <= /*customerCodeTo*/'S'
	/*END*/
	/*IF shipDateFrom != null */
	AND SLIP.SHIP_DATE >= CAST(/*shipDateFrom*/'2010/01/01' AS DATE)
	/*END*/
	/*IF shipDateTo != null */
	AND SLIP.SHIP_DATE <= CAST(/*shipDateTo*/'2010/01/01' AS DATE)
	/*END*/
	/*IF productCodeFrom != null */
	AND LINE.PRODUCT_CODE >= /*productCodeFrom*/'S'
	/*END*/
	/*IF productCodeTo != null */
	AND LINE.PRODUCT_CODE <= /*productCodeTo*/'S'
	/*END*/
/*END*/
ORDER BY SLIP.HIST_ID
