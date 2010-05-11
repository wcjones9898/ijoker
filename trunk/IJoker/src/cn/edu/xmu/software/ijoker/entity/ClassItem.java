package cn.edu.xmu.software.ijoker.entity;

import java.util.Set;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ClassItem entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ClassItem implements Parcelable {

	// Fields

	/**
	 * 
	 */
	private Integer id;
	private String className;
	private Integer classLevel;
	private String classId;

	// Constructors

	/** default constructor */
	public ClassItem() {
	}

	/** minimal constructor */
	public ClassItem(String className, Integer classLevel, String classId) {
		this.className = className;
		this.classLevel = classLevel;
		this.classId = classId;
	}

	/** full constructor */
	public ClassItem(String className, Integer classLevel, String classId,
			Set classAndJokeFiles) {
		this.className = className;
		this.classLevel = classLevel;
		this.classId = classId;

	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getClassLevel() {
		return this.classLevel;
	}

	public void setClassLevel(Integer classLevel) {
		this.classLevel = classLevel;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public static final Parcelable.Creator<ClassItem> CREATOR = new Parcelable.Creator<ClassItem>() {

		public ClassItem[] newArray(int size) {
			return new ClassItem[size];
		}

		public ClassItem createFromParcel(Parcel source) {
			return new ClassItem(source);
		}
	};

	public ClassItem(Parcel in) {
		readFromParcel(in);
	}

	public void readFromParcel(Parcel in) {
		this.id = in.readInt();
		this.classId = in.readString();
		this.classLevel = in.readInt();
		this.className = in.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(classId);
		dest.writeInt(classLevel);
		dest.writeString(className);
	}
}