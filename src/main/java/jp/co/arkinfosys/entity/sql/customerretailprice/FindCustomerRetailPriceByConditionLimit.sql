SELECT
	PRICE.APPLY_DATE,
	PRICE.CUSTOMER_CODE,
	CST.CUSTOMER_NAME,
	PRICE.PRODUCT_CODE,
	PRO.PRODUCT_NAME,
    PRICE.RETAIL_PRICE,
    (SELECT CATEGORY_CODE_NAME
       FROM CATEGORY_TRN_/*$domainId*/
      WHERE CATEGORY_ID = 39
        AND CATEGORY_CODE = PRO.TAX_CATEGORY) AS TAX_CATEGORY_NAME
FROM
	CUSTOMER_RETAIL_PRICE_/*$domainId*/ PRICE
		LEFT OUTER JOIN CUSTOMER_MST_/*$domainId*/ CST
			ON PRICE.CUSTOMER_CODE = CST.CUSTOMER_CODE
		LEFT OUTER JOIN PRODUCT_MST_/*$domainId*/ PRO
			ON PRICE.PRODUCT_CODE = PRO.PRODUCT_CODE
/*BEGIN*/
WHERE
	/*IF applyDate != null*/
	PRICE.APPLY_DATE = /*applyDate*/
	/*END*/
	/*IF customerCodeFrom != null*/
	AND PRICE.CUSTOMER_CODE >= /*customerCodeFrom*/
	/*END*/
	/*IF customerCodeTo != null*/
	AND PRICE.CUSTOMER_CODE <= /*customerCodeTo*/
	/*END*/
	/*IF productCodeFrom != null*/
	AND PRICE.PRODUCT_CODE >= /*productCodeFrom*/
	/*END*/
	/*IF productCodeTo != null*/
	AND PRICE.PRODUCT_CODE <= /*productCodeTo*/
	/*END*/
/*END*/
/*BEGIN*/
ORDER BY
	/*IF sortColumnCustomerRetailPrice != null */
	/*$sortColumnCustomerRetailPrice*/
	/*END*/
	/*IF sortOrder != null*/
	/*$sortOrder*/,
	/*END*/
	PRICE.CUSTOMER_CODE,
	PRICE.PRODUCT_CODE
/*END*/

/*BEGIN*/
/*IF rowCount != null*/
LIMIT /*rowCount*/
/*IF offsetRow != null*/
OFFSET /*offsetRow*/
/*END*/
/*END*/
/*END*/