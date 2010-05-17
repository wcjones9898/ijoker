/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/hipisy/workspace/IJoker/src/cn/edu/xmu/software/ijoker/service/IPlayService.aidl
 */
package cn.edu.xmu.software.ijoker.service;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
import cn.edu.xmu.software.ijoker.entity.Joke;
public interface IPlayService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements cn.edu.xmu.software.ijoker.service.IPlayService
{
private static final java.lang.String DESCRIPTOR = "cn.edu.xmu.software.ijoker.service.IPlayService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IPlayService interface,
 * generating a proxy if needed.
 */
public static cn.edu.xmu.software.ijoker.service.IPlayService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof cn.edu.xmu.software.ijoker.service.IPlayService))) {
return ((cn.edu.xmu.software.ijoker.service.IPlayService)iin);
}
return new cn.edu.xmu.software.ijoker.service.IPlayService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_play:
{
data.enforceInterface(DESCRIPTOR);
cn.edu.xmu.software.ijoker.entity.Joke _arg0;
if ((0!=data.readInt())) {
_arg0 = cn.edu.xmu.software.ijoker.entity.Joke.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.play(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_pause:
{
data.enforceInterface(DESCRIPTOR);
this.pause();
reply.writeNoException();
return true;
}
case TRANSACTION_stop:
{
data.enforceInterface(DESCRIPTOR);
this.stop();
reply.writeNoException();
return true;
}
case TRANSACTION_getJokePlaying:
{
data.enforceInterface(DESCRIPTOR);
cn.edu.xmu.software.ijoker.entity.Joke _result = this.getJokePlaying();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_isPlaying:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isPlaying();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements cn.edu.xmu.software.ijoker.service.IPlayService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void play(cn.edu.xmu.software.ijoker.entity.Joke joke) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((joke!=null)) {
_data.writeInt(1);
joke.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_play, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void pause() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_pause, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void stop() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stop, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public cn.edu.xmu.software.ijoker.entity.Joke getJokePlaying() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
cn.edu.xmu.software.ijoker.entity.Joke _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getJokePlaying, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = cn.edu.xmu.software.ijoker.entity.Joke.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public boolean isPlaying() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isPlaying, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_play = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_pause = (IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_stop = (IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getJokePlaying = (IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_isPlaying = (IBinder.FIRST_CALL_TRANSACTION + 4);
}
public void play(cn.edu.xmu.software.ijoker.entity.Joke joke) throws android.os.RemoteException;
public void pause() throws android.os.RemoteException;
public void stop() throws android.os.RemoteException;
public cn.edu.xmu.software.ijoker.entity.Joke getJokePlaying() throws android.os.RemoteException;
public boolean isPlaying() throws android.os.RemoteException;
}
