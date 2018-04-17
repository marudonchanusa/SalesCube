SELECT
	PRICE.CUSTOMER_RETAIL_PRICE_ID,
	PRICE.APPLY_DATE,
	PRICE.CUSTOMER_CODE,
	CST.CUSTOMER_NAME,
	PRICE.PRODUCT_CODE,
	PRO.PRODUCT_NAME,
    PRICE.RETAIL_PRICE,
    (SELECT CATEGORY_CODE_NAME
       FROM CATEGORY_TRN_/*$domainId*/
      WHERE CATEGORY_ID = 39
        AND CATEGORY_CODE = PRO.TAX_CATEGORY) AS TAX_CATEGORY_NAME,
	PRICE.CRE_FUNC,
	PRICE.CRE_DATETM,
	PRICE.CRE_USER,
	PRICE.UPD_FUNC,
	PRICE.UPD_DATETM,
	PRICE.UPD_USER
FROM
	CUSTOMER_RETAIL_PRICE_/*$domainId*/ PRICE
		LEFT OUTER JOIN CUSTOMER_MST_/*$domainId*/ CST
			ON PRICE.CUSTOMER_CODE = CST.CUSTOMER_CODE
		LEFT OUTER JOIN PRODUCT_MST_/*$domainId*/ PRO
			ON PRICE.PRODUCT_CODE = PRO.PRODUCT_CODE
WHERE
	PRICE.CUSTOMER_RETAIL_PRICE_ID = /*customerRetailPriceId*/