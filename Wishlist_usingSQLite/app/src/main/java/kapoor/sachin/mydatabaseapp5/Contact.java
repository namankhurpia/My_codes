package kapoor.sachin.mydatabaseapp5;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samsung1 on 20-07-2016.
 */

public class Contact implements Parcelable
{
    private int id;
    private String name;
    private String phNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhNum() {
        return phNum;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(phNum);

    }
    public Contact(Parcel in)
    {
        id=in.readInt();
        name=in.readString();
        phNum=in.readString();

    }
    public Contact()
    {

    }
    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
    public Contact createFromParcel(Parcel pc) {
        return new Contact(pc);
    }
    public Contact[] newArray(int size) {
        return new Contact[size];
    }
};

    @Override
    public String toString() {
        return "id=" + id +"\nname=" + name + "\nphNum=" + phNum ;
    }
}

