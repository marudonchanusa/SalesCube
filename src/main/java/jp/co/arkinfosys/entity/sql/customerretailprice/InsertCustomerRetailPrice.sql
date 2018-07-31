INSERT INTO CUSTOMER_RETAIL_PRICE_/*$domainId*/
(
	CUSTOMER_RETAIL_PRICE_ID,
	APPLY_DATE,
	CUSTOMER_CODE,
	PRODUCT_CODE,
	RETAIL_PRICE,
	CRE_FUNC,
	CRE_DATETM,
	CRE_USER,
	UPD_FUNC,
	UPD_DATETM,
	UPD_USER
	
)
VALUES
(
	/*customerRetailPriceId*/,
	/*applyDate*/,
	/*customerCode*/,
	/*productCode*/,
	/*retailPrice*/,
	/*creFunc*/,
	now(),
	/*creUser*/,
	/*updFunc*/,
	now(),
	/*updUser*/
)


