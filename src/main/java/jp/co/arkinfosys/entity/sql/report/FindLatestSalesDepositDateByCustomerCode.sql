SELECT
	(
		SELECT MAX(T1.SALES_DATE)
		FROM SALES_SLIP_TRN_/*$domainId*/ T1
		WHERE
			T1.CUSTOMER_CODE=CUST.CUSTOMER_CODE
			AND T1.SALES_DATE <= /*dateTo*/
	) LATEST_SALES_DATE,
	(
		SELECT MAX(T2.DEPOSIT_DATE)
		FROM DEPOSIT_SLIP_TRN_/*$domainId*/ T2
		WHERE
			T2.CUSTOMER_CODE=CUST.CUSTOMER_CODE
			AND T2.DEPOSIT_DATE <= /*dateTo*/
	) LATEST_DEPOSIT_DATE
FROM
	CUSTOMER_MST_/*$domainId*/ CUST
WHERE
	CUSTOMER_CODE = /*customerCode*/
