package kapoor.sachin.mydatabaseapp5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import static android.R.attr.name;

/**
 * Created by samsung1 on 19-07-2016.
 */
public class MyDBHelper extends SQLiteOpenHelper implements Serializable
{
    private transient Context ct;
    private static final String DB_NAME="Contact_Details";
    private static final String TABLE_NAME="Contacts";
    public MyDBHelper(Context ct)
    {
        super(ct,DB_NAME,null,1);
        this.ct=ct;
        Toast.makeText(ct,"Constructor Called", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TABLE_NAME+" (_id integer,name text,phno text)";
        db.execSQL(query);
        Toast.makeText(ct,"Table created", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="drop table "+TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
        Toast.makeText(ct,"Table dropped and recreated", Toast.LENGTH_LONG).show();
    }


    public boolean addContact(Contact obj)
    {

        boolean result=true;
        try {
            SQLiteDatabase sql = super.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("_id", obj.getId());
            cv.put("name", obj.getName());
            cv.put("phno", obj.getPhNum());
            sql.insert(TABLE_NAME, null, cv);
            sql.close();
        }
        catch (Exception ex)
        {
            result=false;
        }
        finally {
            return result;
        }
    }
    public ArrayList<Contact> getAllRecords()
    {
        SQLiteDatabase sql=super.getReadableDatabase();
        Cursor cr=sql.rawQuery("Select * from "+TABLE_NAME,null);
        ArrayList<Contact> contactList=new ArrayList<>();
        while(cr.moveToNext())
        {
            Contact contact=new Contact();

            contact.setId(cr.getInt(0));
            contact.setName(cr.getString(1));
            contact.setPhNum(cr.getString(2));
            contactList.add(contact);

        }
        sql.close();
        return contactList;
    }
    public Cursor getCursor(){
        SQLiteDatabase sql=super.getReadableDatabase();
        Cursor cr=sql.rawQuery("Select * from "+TABLE_NAME,null);
        return cr;

    }
    public String [] getAllColums(){
        String [] colNames=new String[]{"_id","name","phno"};
        return colNames;
    }
    public Contact findContact(int id)
    {
        SQLiteDatabase sql=super.getReadableDatabase();
        String []idarr=new String[]{String.valueOf(id)};
        Cursor cr=sql.rawQuery("Select * from "+TABLE_NAME+" where _id=?",idarr);
        Contact contact=null;

        if(cr.moveToNext())
        {
            contact=new Contact();

            contact.setId(cr.getInt(0));
            contact.setName(cr.getString(1));
            contact.setPhNum(cr.getString(2));

        }

        return contact;
    }

    public boolean updateContact(Contact obj){
        ContentValues cv=new ContentValues();
        cv.put("name",obj.getName());
        cv.put("phno",obj.getPhNum());
        String []idarr=new String[]{String.valueOf(obj.getId())};
        SQLiteDatabase db=super.getWritableDatabase();
        int res=db.update(TABLE_NAME,cv,"_id=?",idarr);
        return(res!=0);


    }
    public boolean deleteContact(int id){

        String []idarr=new String[]{String.valueOf(id)};
        SQLiteDatabase db=super.getWritableDatabase();
        int res=db.delete(TABLE_NAME,"_id=?",idarr);
        return(res!=0);


    }

}
