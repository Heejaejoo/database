
public class Messages {
	final static String CreateTableSuccess = "'%s' table is created"; 
	final static String DuplicateColumnDefError = "Create table has failed: column definition is duplicated"; 
	final static String DuplicatePrimaryKeyDefError = "Create table has failed: primary key definition is duplicated";
	final static String ReferenceTypeError = "Create table has failed: foreign key references wrong type";
	final static String ReferenceNonPrimaryKeyError = "Create table has failed: foreign key references non primary key column";
	final static String ReferenceColumnExistenceError = "Create table has failed: foreign key references non existing column";
	final static String ReferenceTableExistenceError = "Create table has failed: foreign key references non existing table";
	final static String NonExistingColumnDefError = "Create table has failed: '%s' does not exists in column definition";
	final static String TableExistenceError = "Create table has failed: table with the same name already exists";
	final static String DropSuccess = "'%s' table is dropped";
	final static String DropReferencedTableError = "Drop table has failed: '%s' is referenced by other table";
	final static String ShowTablesNoTable = "There is no table";
	final static String NoSuchTable = "No such table"; 
	final static String CharLengthError = "Char length should be over 0";
	// my specific error
	final static String PrimaryKeyNullError = "Primary key must not be null";
	final static String RefNumberNotMatchError = "the number of referenced attributes must be same as the number of referencing attributes";
	
	//error messages for inserting
	final static String InsertResult = "The row is inserted";
	final static String InsertDuplicatePrimaryKeyError = "Insertion has failed: Primary key duplication";
	final static String InsertReferentialIntegrityError = "Insertion has failed: Referential integrity violation";
	final static String InsertTypeMismatchError = "Insertion has failed: Types are not matched";
	
	//column name needed
	final static String InsertColumnExistenceError = "Insertion has failed: '%s' does not exist";
	final static String InsertColumnNonNullableError = "Insertion has failed: '%s' is not nullable";
	
	//Where clause
	final static String WhereIncomparableError = "Where clause try to compare incomparable values";
	final static String WhereTableNotSpecified = "Where clause try to reference tables which are not specified";
	final static String WhereColumnNotExist = "Where clause try to reference non existing column";
	final static String WhereAmbiguousReference = "Where clause contains ambiguous reference";
	final static String DeleteResult = "%d row(s) are deleted";
	final static String DeleteReferentialIntegrityPassed = "%d row(s) are not deleted due to referential integrity";
	final static String SelectTableExistenceError = "Selection has failed: '%s' does not exist";
	final static String SelectColumnResolveError = "Selection has failed: fail to resolve '%s'";
	

}
