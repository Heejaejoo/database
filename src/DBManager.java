
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.util.ArrayList;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.bind.serial.SerialBinding;

import com.sleepycat.je.Cursor;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;
import java.io.Serializable;

public class DBManager implements Serializable{
	Environment myDbEnvironment = null;
	Database myDatabase = null;
	Database myClassDb = null;
	Cursor cursor = null;
	EnvironmentConfig envConfig = new EnvironmentConfig();
	DatabaseConfig dbConfig =  new DatabaseConfig();
	DatabaseEntry key;
	DatabaseEntry data;
	EntryBinding<Table> dataBinding;
	public DBManager(){
		this.envConfig.setAllowCreate(true);
		dbConfig.setAllowCreate(true);
		dbConfig.setSortedDuplicates(true);
		this.myDbEnvironment = new Environment(new File("db/"), envConfig);
		this.myDatabase = myDbEnvironment.openDatabase(null, "myDb", this.dbConfig);
	    this.dbConfig.setSortedDuplicates(false);
		this.myClassDb = myDbEnvironment.openDatabase(null, "classDb", this.dbConfig);
		StoredClassCatalog classCatalog = new StoredClassCatalog(myClassDb);
		dataBinding = new SerialBinding<Table>(classCatalog, Table.class);
	}
	
	public int put(Table tb) throws MyException{
		try{
			cursor = this.myDatabase.openCursor(null, null);
			key = new DatabaseEntry(tb.getName().getBytes("UTF-8"));
			data = new DatabaseEntry();
		    dataBinding.objectToEntry(tb, data);
			DatabaseEntry emptyData = new DatabaseEntry();
			if(cursor.getSearchKey(key, emptyData, LockMode.DEFAULT) == OperationStatus.NOTFOUND){
				cursor.put(key, data);
				cursor.close();
				return 1;
			}else{
				cursor.close();
				throw new MyException(Messages.TableExistenceError);
			}
			
		}catch (DatabaseException de){
			
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}finally {
			close();
		}
		return -1;
	}
	
	public Table get(String tableName) throws Exception{
		try{
			cursor = this.myDatabase.openCursor(null, null);
			key = new DatabaseEntry(tableName.getBytes("UTF-8"));
			data = new DatabaseEntry();
			if (cursor.getSearchKey(key, data, LockMode.DEFAULT) == OperationStatus.SUCCESS){
			    Table retrievedData = (Table) dataBinding.entryToObject(data);
			    cursor.close();
			    return retrievedData;
			}else{
				cursor.close();
				throw new MyException(Messages.ReferenceTableExistenceError);
			}
		}catch (DatabaseException de){
			
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}finally{
			cursor.close();
		}

		return null;
	}
	
	public ArrayList<Table> getAll() throws Exception, MyException{
		ArrayList<Table> res = new ArrayList<Table>();
		try{
			cursor = this.myDatabase.openCursor(null, null);
			key = new DatabaseEntry();
			data = new DatabaseEntry();
			if(cursor.getFirst(key, data, LockMode.DEFAULT) != OperationStatus.SUCCESS){
				throw new MyException(Messages.ShowTablesNoTable);
			};
		
			do{
				Table retrievedData = (Table) dataBinding.entryToObject(data);
				res.add(retrievedData);
			}while(cursor.getNext(key, data, LockMode.DEFAULT) == OperationStatus.SUCCESS);
			cursor.close();
			return res;
		}catch (DatabaseException de){
		}finally{
			close();
		}
		return null;
	}
	
	private void close(){
		if(this.myDatabase != null){
			this.myDatabase.close();
			this.myClassDb.close();
		}
		if(this.myDbEnvironment != null){
			this.myDbEnvironment.close();
		}
	}
}
