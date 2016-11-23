
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
	EnvironmentConfig envConfig;
	DatabaseConfig dbConfig;
	DatabaseEntry key;
	DatabaseEntry data;
	EntryBinding<Table> dataBinding;
	StoredClassCatalog classCatalog;
	
	private static DBManager instance;
	private DBManager(){
		this.envConfig = new EnvironmentConfig();
		this.dbConfig =  new DatabaseConfig();
		this.envConfig.setAllowCreate(true);
		this.dbConfig.setAllowCreate(true);
		this.dbConfig.setSortedDuplicates(true);
		this.myDbEnvironment = new Environment(new File("db/"), envConfig);
		this.myDatabase = myDbEnvironment.openDatabase(null, "myDb", this.dbConfig);
	    this.dbConfig.setSortedDuplicates(false);
		this.myClassDb = myDbEnvironment.openDatabase(null, "classDb", this.dbConfig);
		this.classCatalog = new StoredClassCatalog(myClassDb);
		this.dataBinding = new SerialBinding<Table>(classCatalog, Table.class);
		this.cursor = this.myDatabase.openCursor(null, null);

	}
	
	//use singleton pattern;
	public static DBManager dbman() {
	    if(instance == null)
	    	instance = new DBManager();
	    return instance;
	 }
	
	public void put(Table tb) throws MyException{
		try{
			key = new DatabaseEntry(tb.getName().getBytes("UTF-8"));
			data = new DatabaseEntry();
		    dataBinding.objectToEntry(tb, data);
			DatabaseEntry emptyData = new DatabaseEntry();
			if(cursor.getSearchKey(key, emptyData, LockMode.DEFAULT) == OperationStatus.NOTFOUND){
				cursor.put(key, data);
			}else{
				throw new MyException(Messages.TableExistenceError);
			}
			
		}catch (DatabaseException de){
			
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
	
	public Table get(String tableName, int num) throws Exception{
		Table retrievedData = null;
		try{
			key = new DatabaseEntry(tableName.getBytes("UTF-8"));
			data = new DatabaseEntry();
			if (cursor.getSearchKey(key, data, LockMode.DEFAULT) == OperationStatus.SUCCESS){
			    retrievedData = (Table) dataBinding.entryToObject(data);
			}else{
				if(num==2){
					throw new MyException(Messages.NoSuchTable);
				}
				throw new MyException(Messages.ReferenceTableExistenceError);
			}
		}catch (DatabaseException de){
			
		}catch (UnsupportedEncodingException e){
			//e.printStackTrace();
		}
		return retrievedData;
	}
	
	public int delete(String tableName) throws Exception{
		int success = 0;
		try{
			key = new DatabaseEntry(tableName.getBytes("UTF-8"));
			data = new DatabaseEntry();
			if (cursor.getSearchKey(key, data, LockMode.DEFAULT) == OperationStatus.SUCCESS){
			    cursor.delete();
			    success = 1;
			}else{
				throw new MyException(Messages.NoSuchTable);
			}
		}catch (DatabaseException de){
			
		}catch (UnsupportedEncodingException e){
			//e.printStackTrace();
		}
		return success;
		
	}
	
	public ArrayList<Table> getAll(int num) throws Exception, MyException{
		ArrayList<Table> res = new ArrayList<Table>();
		try{
			key = new DatabaseEntry();
			data = new DatabaseEntry();
			if(cursor.getFirst(key, data, LockMode.DEFAULT) != OperationStatus.SUCCESS){
				throw new MyException(Messages.ShowTablesNoTable);
			};
			do{
				Table retrievedData = (Table) dataBinding.entryToObject(data);
				res.add(retrievedData);
			}while(cursor.getNext(key, data, LockMode.DEFAULT) == OperationStatus.SUCCESS);
		}catch (DatabaseException de){
		}
		return res;
	}
	
	public void close(){
		if(this.cursor != null){
			this.cursor.close();
		}
		if(this.myDatabase != null){
			this.myDatabase.close();
			this.myClassDb.close();
		}
		if(this.myDbEnvironment != null){
			this.myDbEnvironment.close();
		}
	}
}
