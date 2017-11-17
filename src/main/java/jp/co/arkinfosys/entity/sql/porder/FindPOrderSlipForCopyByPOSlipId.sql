SELECT
	NULL					AS PO_SLIP_ID
	,0						AS STATUS
	,NULL					AS PO_DATE
	,NULL					AS PO_ANNUAL
	,NULL					AS PO_MONTHLY
	,NULL					AS PO_YM
	,NULL					AS DELIVERY_DATE
	,NULL					AS USER_ID
	,NULL					AS USER_NAME
	,SLIP.REMARKS			AS REMARKS
	,SLIP.SUPPLIER_CODE		AS SUPPLIER_CODE
	,SUPP.SUPPLIER_NAME		AS SUPPLIER_NAME
	,SUPP.SUPPLIER_KANA		AS SUPPLIER_KANA
	,SUPP.SUPPLIER_DEPT_NAME	AS SUPPLIER_DEPT_NAME
	,SUPP.SUPPLIER_ZIP_CODE		AS SUPPLIER_ZIP_CODE
	,SUPP.SUPPLIER_ADDRESS_1	AS SUPPLIER_ADDRESS_1
	,SUPP.SUPPLIER_ADDRESS_2	AS SUPPLIER_ADDRESS_2
	,SUPP.SUPPLIER_PC_NAME		AS SUPPLIER_PC_NAME
	,SUPP.SUPPLIER_PC_KANA		AS SUPPLIER_PC_KANA
	,SUPP.SUPPLIER_PC_PRE_CATEGORY	AS SUPPLIER_PC_PRE_CATEGORY
	,PRECAT.CATEGORY_CODE_NAME	AS SUPPLIER_PC_PRE
	,SUPP.SUPPLIER_PC_POST		AS SUPPLIER_PC_POST
	,SUPP.SUPPLIER_TEL			AS SUPPLIER_TEL
	,SUPP.SUPPLIER_FAX			AS SUPPLIER_FAX
	,SUPP.SUPPLIER_EMAIL		AS SUPPLIER_EMAIL
	,SUPP.SUPPLIER_URL			AS SUPPLIER_URL
	,SUPP.SUPPLIER_CM_CATEGORY	AS SUPPLIER_CM_CATEGORY
	,SUPP.SUPPLIER_ABBR			AS SUPPLIER_ABBR
	,SUPP.SUPPLIER_DEPT_NAME	AS SUPPLIER_DEPT_NAME
	,SLIP.TRANSPORT_CATEGORY	AS TRANSPORT_CATEGORY
	,SLIP.TAX_SHIFT_CATEGORY	AS TAX_SHIFT_CATEGORY
	,LINE1.CTAX_RATE			AS SUPPLIER_TAX_RATE
	,SUPP.TAX_FRACT_CATEGORY	AS TAX_FRACT_CATEGORY
	,SUPP.PRICE_FRACT_CATEGORY	AS PRICE_FRACT_CATEGORY
	,SLIP.RATE_ID				AS RATE_ID
	,LINE1.RATE					AS SUPPLIER_RATE
	,NULL						AS PRICE_TOTAL
	,NULL						AS CTAX_TOTAL
	,NULL						AS CTAX_TOTAL
	,NULL						AS FE_PRICE_TOTAL
	,0							AS PRINT_COUNT
	,NULL						AS CRE_FUNC
	,NULL						AS CRE_DATETM
	,NULL						AS CRE_USER
	,NULL						AS UPD_FUNC
	,NULL						AS UPD_DATETM
	,NULL						AS UPD_USER

	,RATEMST.SIGN AS C_UNIT_SIGN
FROM
		PO_SLIP_TRN_/*$domainId*/ SLIP
		INNER JOIN(
			SELECT
				LINE.PO_SLIP_ID		AS PO_SLIP_ID
				,LINE.RATE			AS RATE
				,LINE.CTAX_RATE		AS CTAX_RATE
			FROM PO_LINE_TRN_/*$domainId*/ LINE
			WHERE LINE.PO_SLIP_ID =/*poSlipId*/
			ORDER BY LINE_NO ASC LIMIT 1
		) LINE1
			ON LINE1.PO_SLIP_ID = SLIP.PO_SLIP_ID
		LEFT OUTER JOIN SUPPLIER_MST_/*$domainId*/ SUPP
			ON SLIP.SUPPLIER_CODE = SUPP.SUPPLIER_CODE
		LEFT OUTER JOIN RATE_MST_/*$domainId*/ RATEMST
			ON SLIP.RATE_ID = RATEMST.RATE_ID
		LEFT OUTER JOIN CATEGORY_TRN_/*$domainId*/ PRECAT
			ON PRECAT.CATEGORY_ID = /*preTypeCategoryId*/
			AND PRECAT.CATEGORY_CODE = SUPP.SUPPLIER_PC_PRE_CATEGORY
	WHERE
		SLIP.PO_SLIP_ID=/*poSlipId*/
