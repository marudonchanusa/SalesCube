UPDATE CUSTOMER_RETAIL_PRICE_/*$domainId*/ SET
	APPLY_DATE=/*applyDate*/,
	CUSTOMER_CODE=/*customerCode*/,
	PRODUCT_CODE=/*productCode*/,
	RETAIL_PRICE=/*retailPrice*/,
	UPD_FUNC=/*updFunc*/,
	UPD_DATETM=now(),
	UPD_USER=/*updUser*/
WHERE
	CUSTOMER_RETAIL_PRICE_ID=/*customerRetailPriceId*/
