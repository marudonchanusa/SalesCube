UPDATE
	PO_SLIP_TRN_/*$domainId*/
SET
	PRINT_COUNT = PRINT_COUNT + 1
WHERE
	PO_SLIP_ID = /*poSlipId*/
