# FP-TeamE //
Ryan Sims, Brian Ringer, Adam Null, Amber King
//
This currently requires mysql-connector-java-8.0 external library //
This also requires an SQL Server hosted on the local host with 3 tables (books, circulation, users) //
  the books table requires columns `barcode_number`, `Call_Number`, `ISBN`, `Type`, `Title`, `Author`, `Available`//
    `barcode_number` is auto assigned, `Available` defaults to true//
  the circulation table requires columns `trans_id`, `user_id`, `book_id`, `checkout_date`, `due_date`, `checkin_date`//
    `trans_id` is auto increment, `checkout_date` and `due_date` is automatically assigned, `checkin_date` remains null until book is returned//
  the users table requires columns `ID`, `Username`, `Password`, `Type`//
    `ID` is auto incremented, `Type` defaults to Patron if not specified//
