
public class Messages {
	final String CreateTableSuccess = "'%s' table is created"; 
	final String DuplicateColumnDefError = "Create table has failed: column definition is duplicated"; 
	final String DuplicatePrimaryKeyDefError = "Create table has failed: primary key definition is duplicated";
	final String ReferenceTypeError = "Create table has failed: foreign key references wrong type";
	final String ReferenceNonPrimaryKeyError = "Create table has failed: foreign key references non primary key column";
	final String ReferenceColumnExistenceError = "Create table has failed: foreign key references non existing column";
	final String ReferenceTableExistenceError = "Create table has failed: foreign key references non existing table";
	final String NonExistingColumnDefError = "Create table has failed: '%s' does not exists in column definition";
	final String TableExistenceError = "Create table has failed: table with the same name already exists";
	final String DropSuccess = "'%s' table is dropped";
	final String DropReferencedTableError = "Drop table has failed: '%s' is referenced by other table";
	final String ShowTablesNoTable = "There is no table";
	final String NoSuchTable = "No such table"; 
	final String CharLengthError = "Char length should be over 0";
}
