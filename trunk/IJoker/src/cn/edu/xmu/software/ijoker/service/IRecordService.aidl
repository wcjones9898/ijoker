package cn.edu.xmu.software.ijoker.service;
interface IRecordService{
	void startRecorder();
	void stopRecorder();
	int getRecorderState();
	String getCurrentRecord();
	void startPlayer();
	void stopPlayer();
	int getPlayerState();
	}