SELECT
	COUNT(*)
	FROM
		CUSTOMER_RETAIL_PRICE_/*$domainId*/
/*BEGIN*/
WHERE
	/*IF applyDate != null*/
	APPLY_DATE = /*applyDate*/
	/*END*/
	/*IF customerCodeFrom != null*/
	AND CUSTOMER_CODE >= /*customerCodeFrom*/
	/*END*/
	/*IF customerCodeTo != null*/
	AND CUSTOMER_CODE <= /*customerCodeTo*/
	/*END*/
	/*IF productCodeFrom != null*/
	AND PRODUCT_CODE >= /*productCodeFrom*/
	/*END*/
	/*IF productCodeTo != null*/
	AND PRODUCT_CODE <= /*productCodeTo*/
	/*END*/
/*END*/